package de.officeryoda.api.sdui.response.data.news;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Date;

@Data
public class User {
    private int id;
    @SerializedName("school_id")
    private int schoolId;
    private String type;
    private String title;
    private String state;
    @SerializedName("expire_at")
    private Date expireAt;
    private String locale;
    private Shortcut shortcut;
    @SerializedName("shortcut_id")
    private int shortcutId;
    private String grade;
    @SerializedName("grade_id")
    private String gradeId;
    private Meta meta;

    @Data
    public static class Shortcut {
        private int id;
        @SerializedName("school_id")
        private int schoolId;
        private String shortcut;
        private String name;
        private String description;
        private Meta meta;

        @Data
        public static class Meta {
            @SerializedName("displayname")
            private String displayName;
        }
    }

    @Data
    public static class Meta {
        @SerializedName("displayname")
        private String displayName;
        private String subtitle;
        private String type;
        private String uri;
        private String salutation;
        @SerializedName("days_until_deletion")
        private int daysUntilDeletion;
        @SerializedName("is_signed")
        private boolean isSigned;
        @SerializedName("is_paused")
        private boolean isPaused;
        @SerializedName("deleted_at")
        private Date deletedAt;
    }
}