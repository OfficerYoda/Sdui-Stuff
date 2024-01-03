package de.officeryoda.api;

import com.google.api.services.calendar.model.Event;
import de.officeryoda.api.google.calendar.GoogleCalendarHandler;
import de.officeryoda.api.sdui.SduiApiHandler;
import de.officeryoda.api.sdui.SduiApiUtil;
import de.officeryoda.api.sdui.response.TimeTableInformation;
import de.officeryoda.api.sdui.response.UserInformation;
import de.officeryoda.api.sdui.response.data.timetable.Course;
import de.officeryoda.api.sdui.response.data.timetable.Lesson;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class Main {

    public static void main(String[] args) {
//        sduiApiTest();
        googleCalendarApiTest();
    }

    private static void googleCalendarApiTest() {
        GoogleCalendarHandler calendarHandler = new GoogleCalendarHandler();

        // Create an event
        Event event = new Event()
                .setSummary("Midnight snack")
                .setLocation("Living room")
                .setDescription("I am eating.");

        LocalDateTime startTime = LocalDateTime.of(2024, 1, 1, 2, 20);
        LocalDateTime endTime = startTime.plusMinutes(35);

        event = calendarHandler.createEventInCalendar(event, startTime, endTime);

        // Print the event link
        System.out.printf("Event created: %s\n", event.getHtmlLink());
    }

    private static void sduiApiTest() {
        SduiApiHandler sduiApiHandler = new SduiApiHandler();

        UserInformation userInfo = sduiApiHandler.fetchUserInformation();
//        ParentInformation parentInfo = sduiApiHandler.getParentInformation();
        TimeTableInformation timetable = sduiApiHandler.fetchTimetable();
//        NewsInformation news = sduiApiHandler.getNews();


        System.out.println("userInfo = " + userInfo);
//        System.out.println("parentInfo = " + parentInfo);
//        System.out.println("timetable = " + timetable.hashCode() + " (hash because .toString() is to long)");
//        System.out.println("news = " + news.hashCode() + " (hash because .toString() is possibly multiline)");

        List<Lesson> lessons = timetable.getData().getLessons();
        for(Lesson lesson : lessons) {
            Course course = lesson.getCourse();
            if(course == null) continue;
            System.out.println("Name:\t" + course.getMeta().getDisplayName()
                    + "\nDesc:\t" + course.getDescription()
                    + "\nSubject:\t" + course.getSubject()
                    + "\nTime:\t " + SduiApiUtil.formatLocalDateTimeToString(lesson.getStartTime())+ "-" + SduiApiUtil.formatLocalDateTimeToString(lesson.getEndTime()));

            System.out.println("===================");
        }
    }

    public static void writeToFile(String input, String id) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("toLongForConsole" + (id.isEmpty() ? "" : "-" + id) + ".json"))) {
            writer.write(input);
            System.out.println("Data has been written to the file successfully (" + id + ").");
        } catch(IOException e) {
            System.err.println("An error occurred while writing to the file: (" + id + ")" + e.getMessage());
        }
    }
}
