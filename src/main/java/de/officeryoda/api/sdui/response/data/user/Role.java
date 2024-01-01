package de.officeryoda.api.sdui.response.data.user;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Role {
    @SerializedName("updated_at")
    private LocalDateTime updatedAt;
    @SerializedName("created_at")
    private LocalDateTime createdAt;
    private int id;
    private String name;
    private String key;
    private String description;
}
