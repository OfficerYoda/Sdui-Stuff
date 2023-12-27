package de.officeryoda.sdui.Responses.ResponseInformation;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Date;

@Data
public class Grade {

    @SerializedName("id")
    private int id;

    @SerializedName("shortcut")
    private String shortcut;

    @SerializedName("school_id")
    private int schoolId;

    @SerializedName("schoolyear_id")
    private int schoolyearId;

    @SerializedName("meta")
    private Meta meta;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("level")
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
        private Integer usersCount; // Assuming users_count is of type Integer
    }
}
