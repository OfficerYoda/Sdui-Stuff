package de.officeryoda.api.sdui.response.data.user;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Date;

@Data
public class Grade {
    private int id;
    private String shortcut;
    @SerializedName("school_id")
    private int schoolId;
    @SerializedName("schoolyear_id")
    private int schoolyearId;
    private Meta meta;
    private String name;
    private String description;
    private String level;
    @SerializedName("future_shortcut")
    private String futureShortcut;
    @SerializedName("bookable_id")
    private int bookableId;
    @SerializedName("migrate_at")
    private Date migrateAt;

    @Data
    public static class Meta {
        @SerializedName("displayname")
        private String displayName;
        @SerializedName("users_count")
        private int usersCount;
    }
}
