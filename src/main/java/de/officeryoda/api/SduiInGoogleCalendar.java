package de.officeryoda.api;

import com.google.api.services.calendar.model.Event;
import de.officeryoda.api.google.calendar.GoogleCalendarHandler;
import de.officeryoda.api.sdui.SduiApiHandler;
import de.officeryoda.api.sdui.response.TimeTableInformation;
import de.officeryoda.api.sdui.response.data.timetable.Course;
import de.officeryoda.api.sdui.response.data.timetable.Lesson;

import java.time.LocalDateTime;
import java.util.List;

public class SduiInGoogleCalendar {

    GoogleCalendarHandler calendarHandler;
    SduiApiHandler sduiHandler;

    public SduiInGoogleCalendar() {
        calendarHandler = new GoogleCalendarHandler();
        sduiHandler = new SduiApiHandler();

        LocalDateTime startDate = LocalDateTime.of(2024, 1, 9, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2024, 1, 9, 23, 59);
        TimeTableInformation timeTableInformation = sduiHandler.fetchTimetable(startDate, endDate);
        List<Lesson> lessons = timeTableInformation.getData().getLessons();

        System.out.println(lessons.size());
        for(Lesson lesson : lessons) {
            createCalendarLessonEvent(lesson);
        }
    }

    public static void main(String[] args) {
        new SduiInGoogleCalendar();
    }

    private void createCalendarLessonEvent(Lesson lesson) {
        Course course = lesson.getCourse();
        Course.Meta meta = course.getMeta();
//        System.out.println("getSubject: " + course.getMeta().getColor()); // maybe for matching color
        String displayName = meta.getDisplayName();
        String teacher = lesson.getTeachers().get(0).getName();
        String comment = lesson.getComment();

//        System.out.println("displayName = " + displayName);
//        System.out.println("teacher = " + teacher);
//        System.out.println("comment = " + comment);

        Event event = new Event()
                .setSummary(displayName)
                .setDescription("Teacher: " + teacher + (comment.isBlank() ? "" : "Comment: " + comment));

        calendarHandler.createEventInCalendar(event, lesson.getStartTime(), lesson.getEndTime());
    }
}
