package de.officeryoda.api;

import de.officeryoda.api.google.calendar.GoogleCalendarHandler;
import de.officeryoda.api.sdui.SduiApiHandler;
import de.officeryoda.api.sdui.SduiUtil;
import de.officeryoda.api.sdui.response.TimeTableInformation;
import de.officeryoda.api.sdui.response.data.timetable.Lesson;

import java.time.LocalDateTime;
import java.util.List;

public class SduiInGoogleCalendar {

    GoogleCalendarHandler calendarHandler;
    SduiApiHandler sduiHandler;

    public SduiInGoogleCalendar() {
        calendarHandler = new GoogleCalendarHandler();
        sduiHandler = new SduiApiHandler();

        LocalDateTime startDate = LocalDateTime.of(2024, 1, 7, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2024, 1, 10, 23, 59);

        long startTime = System.currentTimeMillis();

//        createCalendarLessonsInRange(startDate, endDate);
        calendarHandler.deleteEvents(calendarHandler.getEventsInRange(startDate, endDate), 7);
//        calendarHandler.createLesson(new Event().setSummary("Test"), startDate, startDate.plusMinutes(60));

        System.out.println("\ntime: " + (System.currentTimeMillis() - startTime) / 1000f + "s");
    }

    public static void main(String[] args) {
        new SduiInGoogleCalendar();
    }

    private void createCalendarLessonsInRange(LocalDateTime startTime, LocalDateTime endTime) {
        TimeTableInformation timeTableInformation = sduiHandler.fetchTimetable(startTime, endTime);
        List<Lesson> lessons = timeTableInformation.getData().getLessons();

        SduiUtil.sortLessons(lessons);
        System.out.println("Lessons: " + lessons.size());
        calendarHandler.createLessons(lessons, 50);
    }
}
