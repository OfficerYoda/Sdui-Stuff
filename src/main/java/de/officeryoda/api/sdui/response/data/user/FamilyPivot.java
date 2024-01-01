package de.officeryoda.api.sdui.response.data.user;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FamilyPivot {
    @SerializedName("user_id")
    private int userId;
    @SerializedName("parent_id")
    private int parentId;
    private String relation;
    @SerializedName("created_at")
    private LocalDateTime createdAt;
    @SerializedName("is_accessible")
    private int isAccessible;
}
