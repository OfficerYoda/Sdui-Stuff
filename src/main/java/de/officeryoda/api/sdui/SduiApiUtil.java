package de.officeryoda.api.sdui;

import de.officeryoda.api.sdui.response.data.timetable.Lesson;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SduiApiUtil {

    /**
     * Converts a time value in seconds to a LocalDateTime object.
     * Used in {@link Lesson#getStartTime()} and {@link Lesson#getEndTime()}
     *
     * @param seconds The time value in seconds.
     * @return A LocalDateTime object representing the converted time.
     */
    public static LocalDateTime secondsToLocalDateTime(long seconds) {
        // Convert seconds to milliseconds
        Instant instant = Instant.ofEpochSecond(seconds);

        // Convert Instant to LocalDateTime
        return LocalDateTime.ofInstant(instant, java.time.ZoneId.systemDefault());
    }


    /**
     * Formats the given {@code LocalDateTime} using the provided {@code DateTimeFormatter}
     * and returns the formatted string.
     *
     * @param dateTime   The LocalDateTime to be formatted.
     * @param formatter  The DateTimeFormatter to use for formatting.
     * @return           The formatted string representation of the LocalDateTime.
     */
    public static String formatLocalDateTimeToString(LocalDateTime dateTime, DateTimeFormatter formatter) {
        // Format the time as a string
        return dateTime.format(formatter);
    }

    /**
     * Formats the given {@code LocalDateTime} using a default pattern ("HH:mm")
     * and returns the formatted string.
     *
     * <p>Useful for easily obtaining the time of the start or end of a lesson in a readable format.
     *
     * @param dateTime   The LocalDateTime to be formatted.
     * @return           The formatted string representation of the LocalDateTime.
     */
    public static String formatLocalDateTimeToString(LocalDateTime dateTime) {
        // Format the time as a string using the default pattern
        return formatLocalDateTimeToString(dateTime, DateTimeFormatter.ofPattern("HH:mm"));
    }
}
