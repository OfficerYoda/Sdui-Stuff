package de.officeryoda.api.sdui.response.data.news;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class ChannelPivot {
    @SerializedName("news_id")
    private int newsId;
    @SerializedName("channel_id")
    private int channelId;
}
