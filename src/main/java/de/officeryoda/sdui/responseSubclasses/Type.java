package de.officeryoda.sdui.unnecessaryResponseClasses;

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
