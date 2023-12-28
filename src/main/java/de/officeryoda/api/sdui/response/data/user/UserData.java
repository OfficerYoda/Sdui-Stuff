package de.officeryoda.api.sdui.response.data.user;

import com.google.gson.annotations.SerializedName;
import de.officeryoda.api.sdui.response.data.School;
import de.officeryoda.api.sdui.response.data.timetable.Bookable;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserData {
    private String uuid;
    @SerializedName("firstname")
    private String firstName;
    @SerializedName("lastname")
    private String lastName;
    private String email;
    @SerializedName("is_ghost")
    private boolean isGhost;
    @SerializedName("is_trackable")
    private boolean isTrackable;
    private String dob;
    @SerializedName("external_source_id")
    private String externalSourceId;
    @SerializedName("tfa_mode")
    private String tfaMode;
    private School school;
    private Bookable bookable;
    @SerializedName("child_pivot")
    private List<ChildPivot> childPivot;
    @SerializedName("parent_pivot")
    private List<ParentPivot> parentPivot;
    @SerializedName("password_changed_at")
    private Date passwordChangedAt;
    @SerializedName("loggedin_at")
    private Date loggedInAt;
    private Grade grade;
    @SerializedName("grade_id")
    private int gradeId;
    private List<Role> roles;
    @SerializedName("primary_role")
    private PrimaryRole primaryRole;
    private int id;
    @SerializedName("school_id")
    private int schoolId;
    private String username;
    private String type;
    private String title;
    private String sex;
    private String state;
    @SerializedName("expire_at")
    private Date expireAt;
    private String locale;
    @SerializedName("shortcut")
    private String shortcut;
    @SerializedName("shortcut_id")
    private int shortcutId;
    private Meta meta;

    @lombok.Data
    public static class Meta {
        @SerializedName("displayname")
        private String displayName;
        private String subtitle;
        private String type;
        private String uri;
        @SerializedName("avatar_uri")
        private String avatarUri;
        private String salutation;
        @SerializedName("days_until_deletion")
        private int daysUntilDeletion;
        @SerializedName("is_signed")
        private boolean isSigned;
        @SerializedName("is_paused")
        private boolean isPaused;
        @SerializedName("archived_at")
        private Date archivedAt;
        @SerializedName("deleted_at")
        private Date deletedAt;
        @SerializedName("is_trackable_classbook_user")
        private boolean isTrackableClassbookUser;
        @SerializedName("is_trackable")
        private boolean isTrackable;
        @SerializedName("calendar_notification_count")
        private int calendarNotificationCount;
    }
}
