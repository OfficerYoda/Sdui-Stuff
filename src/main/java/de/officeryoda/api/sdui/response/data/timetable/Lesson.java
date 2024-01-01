package de.officeryoda.api.sdui.response.data.timetable;

import com.google.gson.annotations.SerializedName;
import de.officeryoda.api.sdui.SduiApiUtil;
import lombok.Data;

import java.time.LocalDateTime;
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
     * Represents the start time in <b>seconds</b> since the Unix Epoch (January 1, 1970, 00:00:00 UTC).
     */
    @SerializedName("begins_at")
    private int beginsAt;
    /**
     * Represents the end time in <b>seconds</b> since the Unix Epoch (January 1, 1970, 00:00:00 UTC).
     */
    @SerializedName("ends_at")
    private int endsAt;
    private String comment;
    private Course course;
    private Meta meta;

    /**
     * This method will return {@link Lesson#beginsAt} as {@link LocalDateTime}.
     *
     * @return The {@link LocalDateTime} of the moment the lesson starts.
     */
    public LocalDateTime getStartTime() {
        return SduiApiUtil.secondsToLocalDateTime(this.beginsAt);
    }


    /**
     * This method will return {@link Lesson#endsAt} as {@link LocalDateTime}.
     *
     * @return The {@link LocalDateTime} of the moment the lesson ends.
     */
    public LocalDateTime getEndTime() {
        return SduiApiUtil.secondsToLocalDateTime(this.endsAt);
    }

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


