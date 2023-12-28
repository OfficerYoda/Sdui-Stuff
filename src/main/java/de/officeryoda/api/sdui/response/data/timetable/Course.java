package de.officeryoda.api.sdui.response.data.timetable;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Course {
    private Meta meta;
    private Subject subject;
    private int id;
    private String name;
    private String description;
    @SerializedName("subject_id")
    private int subjectId;

    @Data
    public static class Meta {
        @SerializedName("displayname")
        private String displayName;
        private String shortname;
        private String color;
        private String name;
        private String description;
    }
}
