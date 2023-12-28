package de.officeryoda.api.sdui.response.data.news;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class NewsItem {
    private int id;
    private String title;
    private String content;
    @SerializedName("has_translations")
    private boolean hasTranslations;
    @SerializedName("survey_uuid")
    private String surveyUuid;
    @SerializedName("is_confirmable")
    private boolean isConfirmable;
    @SerializedName("is_public")
    private boolean isPublic;
    @SerializedName("is_official")
    private int isOfficial;
    @SerializedName("is_pinned")
    private boolean isPinned;
    @SerializedName("publish_at")
    private Date publishAt;
    @SerializedName("has_emergency_sms")
    private boolean hasEmergencySms;
    @SerializedName("content_rendered")
    private String contentRendered;
    @SerializedName("user")
    private User creator;
    private List<Channel> channels;
    @SerializedName("channel_pivot")
    private List<ChannelPivot> channelPivot;
    private Survey survey;
    @SerializedName("updated_at")
    private Date updatedAt;
    @SerializedName("created_at")
    private Date createdAt;
    private List<Attachment> attachments;
    private Preview preview;
    private Meta meta;

    @Data
    public static class Meta {
        private String uri;
        @SerializedName("confirm_uri")
        private boolean confirmUri;
        @SerializedName("is_confirmed")
        private boolean isConfirmed;
        private String csv;
        private String xls;
    }
}