package de.officeryoda.sdui.unnecessaryResponseClasses;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class ParentPivot {

    @SerializedName("user_id")
    private int userId;

    @SerializedName("parent_id")
    private int parentId;

    @SerializedName("relation")
    private String relation;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("is_accessible")
    private int isAccessible;

}
