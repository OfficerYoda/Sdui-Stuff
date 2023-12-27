package de.officeryoda.sdui.Responses.ResponseInformation;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Course {
    @SerializedName("meta")
    private Meta meta;

    @SerializedName("subject")
    private Subject subject;

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("subject_id")
    private int subjectId;

    @Data
    public static class Meta {
        @SerializedName("displayname")
        private String displayname;

        @SerializedName("shortname")
        private String shortname;

        @SerializedName("color")
        private String color;

        @SerializedName("name")
        private String name;

        @SerializedName("description")
        private String description;
    }
}
