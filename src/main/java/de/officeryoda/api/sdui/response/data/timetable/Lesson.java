package de.officeryoda.api.sdui.response.data.timetable;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class Lesson {
    private List<Bookable> bookables;
    private List<Bookable> grades;
    private List<Bookable> teachers;
    private String kind;
    @SerializedName("referenced_target_lessons")
    private List<Lesson> referencedTargetLessons;
    private int id;
    /**
     * Represents the start time in seconds since the Unix Epoch (January 1, 1970, 00:00:00 UTC).
     */
    @SerializedName("begins_at")
    private int beginsAt;
    /**
     * Represents the end time in seconds since the Unix Epoch (January 1, 1970, 00:00:00 UTC).
     */
    @SerializedName("ends_at")
    private int endsAt;
    private String comment;
    private Course course;
    private Meta meta;

    @Data
    public static class Meta {
        @SerializedName("displayname_hour")
        private String displayNameHour;
        @SerializedName("moved_comment")
        private String movedComment;
        private String displayName;
        private String shortname;
        @SerializedName("displayname_kind")
        private String displayNameKind;
    }
}


