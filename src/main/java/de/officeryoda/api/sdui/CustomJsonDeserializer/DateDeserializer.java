package de.officeryoda.api.sdui.CustomJsonDeserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;

public class DateDeserializer implements JsonDeserializer<Date> {
    private static final DateTimeFormatter DATE_FORMATTER;

    static {
        DATE_FORMATTER = new DateTimeFormatterBuilder()
                .appendOptional(DateTimeFormatter.ISO_OFFSET_DATE_TIME)             // 2023-12-21T13:46:17+01:00
                .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) // 2023-09-11 00:00:00
                .toFormatter();
    }

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String dateString = json.getAsString();
        try {
            LocalDateTime localDateTime = LocalDateTime.parse(dateString, DATE_FORMATTER);
            Instant instant = localDateTime.atZone(java.time.ZoneId.systemDefault()).toInstant();
            return Date.from(instant);
        } catch (Exception e) {
            throw new JsonParseException("Unable to parse date: " + dateString);
        }
    }
}