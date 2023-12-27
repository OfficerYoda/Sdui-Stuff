package de.officeryoda.sdui.Responses.ResponseInformation;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Subject {
    @SerializedName("color")
    private String color;

    @SerializedName("meta")
    private Meta meta;

    @SerializedName("id")
    private int id;

    @SerializedName("shortcut")
    private String shortcut;

    @SerializedName("name")
    private String name;

    @Data
    public static class Meta {
        @SerializedName("displayname")
        private String displayName;
    }
}
