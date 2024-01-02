package de.officeryoda.api.google.calendar;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.Collections;

public class GoogleCalendarHandler {

    private static final String APPLICATION_NAME;
    private static final JsonFactory JSON_FACTORY;
    private static final String TOKENS_DIRECTORY_PATH;
    private static final String CLIENT_ID;
    private static final String CLIENT_SECRET;
    private static final String REDIRECT_URIS;

    private static Calendar service;

    static {
        APPLICATION_NAME = "YOUR_PROJECT_ID"; // Set to your project ID or any suitable name
        JSON_FACTORY = JacksonFactory.getDefaultInstance();
        TOKENS_DIRECTORY_PATH = "tokens";

        // Load client secrets from file
        try {
            InputStream in = new FileInputStream(System.getProperty("user.dir") + "/tokens/client-secrets.json");
            GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

            CLIENT_ID = clientSecrets.getDetails().getClientId();
            CLIENT_SECRET = clientSecrets.getDetails().getClientSecret();
            REDIRECT_URIS = String.join(", ", clientSecrets.getDetails().getRedirectUris());

            // Initialize the Calendar service
            service = getCalendarService();
        } catch (IOException | GeneralSecurityException e) {
            e.printStackTrace();
            throw new RuntimeException("Error loading client secrets.", e);
        }
    }

    public static void main(String[] args) throws GeneralSecurityException, IOException {
        // Initialize the Calendar service
        service = getCalendarService();

        // Create an event
        Event event = createEvent();

        // Print the event link
        System.out.printf("Event created: %s\n", event.getHtmlLink());
    }

    private static Event createEvent() {
        // Create an event
        Event event = new Event()
                .setSummary("Midnight snack")
                .setLocation("Living room")
                .setDescription("I am eating.");

        DateTime startDateTime = new DateTime("2024-01-01T02:00:00.000Z");
        EventDateTime start = new EventDateTime()
                .setDateTime(startDateTime)
                .setTimeZone("CET");
        event.setStart(start);

        DateTime endDateTime = new DateTime("2024-01-01T02:30:00.000Z");
        EventDateTime end = new EventDateTime()
                .setDateTime(endDateTime)
                .setTimeZone("CET");
        event.setEnd(end);

        // Insert the event into the calendar
        try {
            String calendarId = "primary"; // Use "primary" for the default calendar of the authenticated user
            return service.events().insert(calendarId, event).execute();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Calendar getCalendarService() throws IOException, GeneralSecurityException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

        // Load client secrets
        InputStream in = new FileInputStream(System.getProperty("user.dir") + "/tokens/client-secrets.json");
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets,
                Collections.singletonList(CalendarScopes.CALENDAR))
                .setDataStoreFactory(new FileDataStoreFactory(new File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();

        Credential credential = new AuthorizationCodeInstalledApp(
                flow, new LocalServerReceiver()).authorize("user");

        // Return the Calendar service
        return new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }
}