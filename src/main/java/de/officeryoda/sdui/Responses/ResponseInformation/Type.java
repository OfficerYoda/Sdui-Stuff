package de.officeryoda.sdui.Responses.ResponseInformation;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Type {

    @SerializedName("key")
    private String key;

    @SerializedName("name")
    private String name;

    @SerializedName("level")
    private String level;

}
