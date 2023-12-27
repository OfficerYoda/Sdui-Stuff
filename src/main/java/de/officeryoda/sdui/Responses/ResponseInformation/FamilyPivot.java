package de.officeryoda.sdui.Responses.ResponseInformation;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Date;

@Data
public abstract class FamilyPivot {

    @SerializedName("user_id")
    private int userId;

    @SerializedName("parent_id")
    private int parentId;

    @SerializedName("relation")
    private String relation;

    @SerializedName("created_at")
    private Date createdAt;

    @SerializedName("is_accessible")
    private int isAccessible;


}
