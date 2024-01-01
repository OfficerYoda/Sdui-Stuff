package de.officeryoda.api.sdui.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class DateDeserializer implements JsonDeserializer<LocalDateTime> {
    private static final DateTimeFormatter DATE_FORMATTER;

    static {
        DATE_FORMATTER = new DateTimeFormatterBuilder()
                .appendOptional(DateTimeFormatter.ISO_OFFSET_DATE_TIME)             // 2023-12-21T13:46:17+01:00
                .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) // 2023-09-11 00:00:00
                .toFormatter();
    }

    @Override
    public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String dateString = json.getAsString();
        try {
            return LocalDateTime.parse(dateString, DATE_FORMATTER);
        } catch (Exception e) {
            throw new JsonParseException("Unable to parse date: " + dateString);
        }
    }
}