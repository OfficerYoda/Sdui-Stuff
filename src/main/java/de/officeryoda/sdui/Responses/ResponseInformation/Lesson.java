package de.officeryoda.sdui.Responses.ResponseInformation;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
public class Lesson {
    @SerializedName("bookables")
    private List<Bookable> bookables;

    @SerializedName("grades")
    private List<Bookable> grades;

    @SerializedName("teachers")
    private List<Bookable> teachers;

    @SerializedName("kind")
    private String kind;

    @SerializedName("referenced_target_lessons")
    private List<Lesson> referencedTargetLessons;

    @SerializedName("id")
    private int id;

    /**
     * Represents the start time in seconds since the Unix Epoch (January 1, 1970, 00:00:00 UTC).
     * -- GETTER --
     *  Gets the start time in seconds since the Unix Epoch (January 1, 1970, 00:00:00 UTC).
     *
     * @return The start time in seconds.
     */
    @Getter
    @SerializedName("begins_at")
    private int beginsAt;

    /**
     * Represents the end time in seconds since the Unix Epoch (January 1, 1970, 00:00:00 UTC).
     * -- GETTER --
     *  Gets the end time in seconds since the Unix Epoch (January 1, 1970, 00:00:00 UTC).
     *
     * @return The end time in seconds.
     */
    @Getter
    @SerializedName("ends_at")
    private int endsAt;

    @SerializedName("comment")
    private String comment;

    @SerializedName("course")
    private Course course;

    @SerializedName("meta")
    private Meta meta;

    @Data
    public static class Meta {
        @SerializedName("displayname_hour")
        private String displaynameHour;

        @SerializedName("moved_comment")
        private String movedComment;

        @SerializedName("displayname")
        private String displayname;

        @SerializedName("shortname")
        private String shortname;

        @SerializedName("displayname_kind")
        private String displaynameKind;
    }
}


