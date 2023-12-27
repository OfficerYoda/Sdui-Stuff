package de.officeryoda.sdui.responseSubclasses;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

@lombok.Data
public class Data {

    @SerializedName("uuid")
    private String uuid;

    @SerializedName("firstname")
    private String firstname;

    @SerializedName("lastname")
    private String lastname;

    @SerializedName("email")
    private String email;

    @SerializedName("is_ghost")
    private boolean isGhost;

    @SerializedName("is_trackable")
    private boolean isTrackable;

    @SerializedName("dob")
    private String dob;

    @SerializedName("external_source_id")
    private String externalSourceId;

    @SerializedName("tfa_mode")
    private String tfaMode;

    @SerializedName("permissions")
    private List<Object> permissions; // Replace Object with the actual data type

    @SerializedName("school")
    private School school;

    @SerializedName("bookable")
    private Object bookable; // Replace Object with the actual data type

    // @SerializedName("can") // Uncomment if you have a field named "can"
    // private Can can;

    @SerializedName("child_pivot")
    private List<Object> childPivot; // Replace Object with the actual data type

    @SerializedName("parent_pivot")
    private List<ParentPivot> parentPivot;

    @SerializedName("password_changed_at")
    private Date passwordChangedAt;

    @SerializedName("loggedin_at")
    private Date loggedinAt;

    // @SerializedName("properties") // Uncomment if you have a field named "properties"
    // private Properties properties;

    @SerializedName("grade")
    private Grade grade;

    @SerializedName("grade_id")
    private int gradeId;

    @SerializedName("roles")
    private List<Role> roles;

    @SerializedName("primary_role")
    private PrimaryRole primaryRole;

    @SerializedName("id")
    private int id;

    @SerializedName("school_id")
    private int schoolId;

    @SerializedName("username")
    private String username;

    @SerializedName("type")
    private String type;

    @SerializedName("title")
    private String title;

    @SerializedName("sex")
    private String sex;

    @SerializedName("state")
    private String state;

    @SerializedName("expire_at")
    private Object expireAt; // Replace Object with the actual data type

    @SerializedName("locale")
    private String locale;

    @SerializedName("shortcut")
    private Object shortcut; // Replace Object with the actual data type

    @SerializedName("shortcut_id")
    private Object shortcutId; // Replace Object with the actual data type

    @SerializedName("meta")
    private Meta meta;


    @lombok.Data
    public static class Meta {
        @SerializedName("displayname")
        private String displayName;

        @SerializedName("subtitle")
        private String subtitle;

        @SerializedName("type")
        private String type;

        @SerializedName("uri")
        private String uri;

        @SerializedName("avatar_uri")
        private String avatarUri;

        @SerializedName("salutation")
        private String salutation;

        @SerializedName("days_until_deletion")
        private int daysUntilDeletion;

        @SerializedName("is_signed")
        private boolean isSigned;

        @SerializedName("is_paused")
        private boolean isPaused;

        @SerializedName("archived_at")
        private String archivedAt;

        @SerializedName("deleted_at")
        private String deletedAt;

        @SerializedName("is_trackable_classbook_user")
        private boolean isTrackableClassbookUser;

        @SerializedName("is_trackable")
        private boolean isTrackable;

        @SerializedName("calendar_notification_count")
        private int calendarNotificationCount;
    }

}
