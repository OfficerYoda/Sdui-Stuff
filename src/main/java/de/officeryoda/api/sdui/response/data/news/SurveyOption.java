package de.officeryoda.api.sdui.response.data.news;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class SurveyOption {
    private String uuid;
    private String name;
    @SerializedName("is_chosen")
    private boolean isChosen;
}