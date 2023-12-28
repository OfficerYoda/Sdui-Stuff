package de.officeryoda.api.sdui.Responses.ResponseInformation;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Date;

@Data
public class Channel {
    private int id;
    private String name;
    private String description;
    @SerializedName("description_members")
    private String descriptionMembers;
    private String subtitle;
    private String type;
    private String uuid;
    @SerializedName("user_id")
    private String userId;
    @SerializedName("school_id")
    private int schoolId;
    @SerializedName("chat_id")
    private int chatId;
    @SerializedName("cloud_id")
    private int cloudId;
    @SerializedName("calendar_id")
    private int calendarId;
    private String target;
    @SerializedName("intern_id")
    private String internId;
    private Object avatar;
    private Object icon;
    private Object color;
    @SerializedName("is_leavable")
    private boolean isLeavable;
    @SerializedName("is_public")
    private boolean isPublic;
    @SerializedName("is_disabled")
    private boolean isDisabled;
    @SerializedName("is_twoway")
    private boolean isTwoway;
    @SerializedName("is_hidden_memberlist")
    private boolean isHiddenMemberlist;
    @SerializedName("twoway_expires_at")
    private Date twowayExpiresAt;
    @SerializedName("activity_at")
    private Date activityAt;
    @SerializedName("expires_at")
    private Date expiresAt;
    @SerializedName("expiration_reason")
    private Object expirationReason;
    @SerializedName("trashed_at")
    private Date trashedAt;
    @SerializedName("created_at")
    private Date createdAt;
    @SerializedName("updated_at")
    private Date updatedAt;
    private Object group;
    @SerializedName("disabled_by_id")
    private Object disabledById;
    @SerializedName("content_move_decision_made_at")
    private Date contentMoveDecisionMadeAt;
    private ChannelPivot pivot;
    private School school;
    private Meta meta;

    public static class Meta {
        @SerializedName("is_official")
        private int isOfficial;
        private String subtitle;
        @SerializedName("displayname")
        private String displayName;
        private String shortcut;
    }
}
