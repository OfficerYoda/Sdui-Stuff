package de.officeryoda.sdui.responseSubclasses;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

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
    private Object migrateAt; // Replace Object with the actual data type


    @Data
    public static class Meta {
        @SerializedName("displayname")
        private String displayName;

        @SerializedName("users_count")
        private Integer usersCount; // Assuming users_count is of type Integer
    }
}
