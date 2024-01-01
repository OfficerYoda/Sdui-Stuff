package de.officeryoda.api.sdui.response.data.news;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Attachment {
    private int id;
    private String uuid;
    @SerializedName("user_id")
    private int userId;
    @SerializedName("source_id")
    private int sourceId;
    @SerializedName("source_type")
    private String sourceType;
    private String name;
    private String type;
    private String extension;
    private int size;
    @SerializedName("created_at")
    private LocalDateTime createdAt;
    @SerializedName("updated_at")
    private LocalDateTime updatedAt;
    @SerializedName("file_type")
    private String fileType;
    private Meta meta;

    @Data
    public static class Meta {
        private String uri;
        @SerializedName("download_uri")
        private String downloadUri;
        @SerializedName("temp_uri")
        private String tempUri;
    }
}