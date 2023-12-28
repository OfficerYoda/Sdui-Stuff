package de.officeryoda.api.sdui.response.data.user;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Date;

@Data
public class Role {
    @SerializedName("updated_at")
    private Date updatedAt;
    @SerializedName("created_at")
    private Date createdAt;
    private int id;
    private String name;
    private String key;
    private String description;
}
