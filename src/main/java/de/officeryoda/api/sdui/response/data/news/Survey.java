package de.officeryoda.api.sdui.response.data.news;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Survey {
    @SerializedName("meta")
    private Meta meta;
    private String question;
    private int id;
    private String uuid;
    @SerializedName("is_multi_answerable")
    private boolean isMultiAnswerable;
    @SerializedName("is_anonymous")
    private boolean isAnonymous;
    @SerializedName("is_freetext")
    private boolean isFreeText;
    @SerializedName("results_visibility")
    private String resultsVisibility;
    @SerializedName("has_translations")
    private boolean hasTranslations;
    @SerializedName("created_at")
    private LocalDateTime createdAt;
    @SerializedName("updated_at")
    private LocalDateTime updatedAt;
    @SerializedName("expires_at")
    private LocalDateTime expiresAt;
    @SerializedName("ended_at")
    private LocalDateTime endedAt;
    @SerializedName("deleted_at")
    private LocalDateTime deletedAt;
    private User user;

    @Data
    public static class Meta {
        @SerializedName("is_over")
        private boolean isOver;
        private List<SurveyOption> options;
        @SerializedName("is_user_voted")
        private boolean isUserVoted;
        private String csv;
        private String xls;
        private List<String> languages;
    }
}