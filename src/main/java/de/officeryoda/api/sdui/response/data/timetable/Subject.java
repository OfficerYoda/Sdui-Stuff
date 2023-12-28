package de.officeryoda.api.sdui.response.data.timetable;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Subject {
    private String color;
    private Meta meta;
    private int id;
    private String shortcut;
    private String name;

    @Data
    public static class Meta {
        @SerializedName("displayname")
        private String displayName;
    }
}
