package de.officeryoda.api.google.calendar;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.batch.BatchRequest;
import com.google.api.client.googleapis.batch.json.JsonBatchCallback;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.Events;
import de.officeryoda.api.sdui.response.data.timetable.Bookable;
import de.officeryoda.api.sdui.response.data.timetable.Course;
import de.officeryoda.api.sdui.response.data.timetable.Lesson;

import java.io.*;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class GoogleCalendarHandler {

    private static final String SDUI_CALENDAR_ID = "9ca7bfdd746dabb4a2477541e2d2d8796626e8fca1b220e0e36d21d9c9fdadb1@group.calendar.google.com";
    private static Calendar service;

    private final String APPLICATION_NAME;
    private final JsonFactory JSON_FACTORY;
    private final String TOKENS_DIRECTORY_PATH;

    {
        APPLICATION_NAME = "YOUR_PROJECT_ID"; // Set to your project ID or any suitable name
        JSON_FACTORY = JacksonFactory.getDefaultInstance();
        TOKENS_DIRECTORY_PATH = "tokens";

        // Load client secrets from file
        try {
            InputStream in = new FileInputStream(System.getProperty("user.dir") + "/tokens/client-secrets.json");
            GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

            // Initialize the Calendar service
            service = getCalendarService();
        } catch(IOException e) {
            throw new RuntimeException("Error loading client secrets.", e);
        }
    }
//
//    public GoogleCalendarHandler() {
//        // Initialize the Calendar service
//        service = getCalendarService();
//    }

    public Event createLesson(Event event, LocalDateTime startTime, LocalDateTime endTime) {
        setEventTime(event, startTime, endTime);

        // Set reminders
        final Event.Reminders noReminder = new Event.Reminders().setUseDefault(false);
        event.setReminders(noReminder);

        // Insert the event into the calendar
        try {
            System.out.println("Creating Lesson: " + event.getSummary());
            return service.events().insert(SDUI_CALENDAR_ID, event).execute();
        } catch(IOException e) {
            System.err.println("Failed to create Event:\n" + e);
            return null;
        }
    }

    private Event convertLessonToEvent(Lesson lesson) {
        // get the data
        Course course;
        String displayName = "No name given";
        if((course = lesson.getCourse()) != null) {
            Course.Meta meta = course.getMeta();
            displayName = meta.getDisplayName();
//        System.out.println("getSubject: " + meta.getColor()); // maybe for matching color
        } else if(lesson.getKind() != null) {
            displayName = lesson.getMeta().getDisplayNameKind();
        }

        if(displayName.equals("No name given")) {
            System.out.println(lesson);
            System.out.println("Lesson has no name");
            throw new NullPointerException();
        }

        List<Bookable> teachers = lesson.getTeachers();
        String teacher = "";
        if(!teachers.isEmpty()) teacher = teachers.get(0).getName();

        String comment = lesson.getComment();

        // create event
        Event event = new Event()
                .setSummary(displayName)
                .setDescription("Teacher: " + teacher + (comment.isBlank() ? "" : "Comment: " + comment));

        setEventTime(event, lesson.getStartTime(), lesson.getEndTime());

        return event;
    }

    public List<Event> createLessons(List<Lesson> lessons, int maxBatchSize) {
        // create events
        List<Event> events = lessons.stream().map(this::convertLessonToEvent).toList();

        // Split the events into batches
        List<List<Event>> eventBatches = splitIntoBatches(events, maxBatchSize);

        for(List<Event> eventBatch : eventBatches) {
            // Create a batch request
            BatchRequest batch = service.batch();

            for(Event event : eventBatch) {
                try {
                    // Add each event creation to the batch request
                    service.events().insert(SDUI_CALENDAR_ID, event).queue(batch, new JsonBatchCallback<Event>() {
                        @Override
                        public void onSuccess(Event event, HttpHeaders httpHeaders) throws IOException {
                            System.out.println("Event created: " + event.getSummary());
                        }

                        @Override
                        public void onFailure(GoogleJsonError googleJsonError, HttpHeaders httpHeaders) {
                            System.err.println("Failed to create event: " + event.getSummary() + "\n" + httpHeaders + "\n" + googleJsonError);
                        }
                    });
                } catch(IOException e) {
                    throw new RuntimeException(e);
                }
            }

            try {
                // Execute the batch request
                System.out.println("Executing Batch (" + batch.size() + ")...");
                batch.execute();
                System.out.println("Batch executed successfully.");
            } catch(IOException e) {
                throw new RuntimeException(e);
            }
        }

        return null;
    }

    public void deleteEvents(List<Event> events, int maxBatchSize) {
        // Split the events into batches

        List<List<Event>> eventBatches = splitIntoBatches(events, maxBatchSize);

        eventBatches.parallelStream().forEach(eventBatch -> {
            // Create a batch request
            BatchRequest batch = service.batch();

            eventBatch.parallelStream().forEach(event -> {
                try {
                    // Add each event deletion to the batch request
                    service.events().delete(SDUI_CALENDAR_ID, event.getId()).queue(batch, new JsonBatchCallback<Void>() {
                        @Override
                        public void onSuccess(Void aVoid, HttpHeaders httpHeaders) {
//                            System.out.println("Event deleted: " + event.getSummary());
                        }

                        @Override
                        public void onFailure(GoogleJsonError googleJsonError, HttpHeaders httpHeaders) {
                            System.err.println("Failed to delete event: " + event.getSummary());
                        }
                    });
                } catch(IOException e) {
                    throw new RuntimeException(e);
                }
            });

            try {
                // Execute the batch request
                System.out.println("Executing Batch (" + batch.size() + ")...");
                batch.execute();
                System.out.println("Batch executed successfully.");
            } catch(IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void deleteEvent(Event event) {
        try {
            service.events().delete(SDUI_CALENDAR_ID, event.getId()).execute();
            System.out.println("Event deleted: " + event.getSummary());
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void setEventTime(Event event, LocalDateTime startTime, LocalDateTime endTime) {
        DateTime startDateTime = new DateTime(startTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        EventDateTime start = new EventDateTime()
                .setDateTime(startDateTime)
                .setTimeZone("CET");
        event.setStart(start);

        DateTime endDateTime = new DateTime(endTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        EventDateTime end = new EventDateTime()
                .setDateTime(endDateTime)
                .setTimeZone("CET");
        event.setEnd(end);
    }

    public List<Event> getEventsInRange(LocalDateTime startTime, LocalDateTime endTime) {
        DateTime startDateTime = new DateTime(Date.from(startTime.atZone(ZoneId.systemDefault()).toInstant()));
        DateTime endDateTime = new DateTime(Date.from(endTime.atZone(ZoneId.systemDefault()).toInstant()));

        Events events = null;
        try {
            events = service.events().list(SDUI_CALENDAR_ID)
                    .setTimeMin(startDateTime)
                    .setTimeMax(endDateTime)
                    .execute();
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

        return events.getItems();
    }

    private List<List<Event>> splitIntoBatches(List<Event> events, int batchSize) {
        List<List<Event>> batches = new ArrayList<>();
        for(int i = 0; i < events.size(); i += batchSize) {
            int end = Math.min(i + batchSize, events.size());
            batches.add(new ArrayList<>(events.subList(i, end)));
        }
        return batches;
    }

    private Calendar getCalendarService() {
        try {
            final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();


            // Load client secrets
            InputStream in = new FileInputStream(System.getProperty("user.dir") + "/tokens/client-secrets.json");
            GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

            // Build flow and trigger user authorization request.
            GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                    httpTransport, JSON_FACTORY, clientSecrets,
                    Collections.singletonList(CalendarScopes.CALENDAR))
                    .setDataStoreFactory(new FileDataStoreFactory(new File(TOKENS_DIRECTORY_PATH)))
                    .setAccessType("offline")
                    .build();

            Credential credential = new AuthorizationCodeInstalledApp(
                    flow, new LocalServerReceiver()).authorize("user");

            // Return the Calendar service
            return new Calendar.Builder(httpTransport, JSON_FACTORY, credential)
                    .setApplicationName(APPLICATION_NAME)
//                    .setHttpRequestInitializer(request -> request.setConnectTimeout(42000).setReadTimeout(42000)) // set timeout to 42s
                    .build();
        } catch(GeneralSecurityException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
