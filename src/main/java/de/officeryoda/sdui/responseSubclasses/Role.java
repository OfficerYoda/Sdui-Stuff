package de.officeryoda.sdui.unnecessaryResponseClasses;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Role {

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("key")
    private String key;

    @SerializedName("description")
    private String description;

}
