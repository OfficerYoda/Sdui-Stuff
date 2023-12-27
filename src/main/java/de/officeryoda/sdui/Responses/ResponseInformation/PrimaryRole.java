package de.officeryoda.sdui.Responses.ResponseInformation;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Date;

@Data
public class PrimaryRole {

    @SerializedName("translated_description")
    private String translatedDescription;

    @SerializedName("updated_at")
    private Date updatedAt;

    @SerializedName("created_at")
    private Date createdAt;

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("key")
    private String key;

    @SerializedName("description")
    private String description;

}
