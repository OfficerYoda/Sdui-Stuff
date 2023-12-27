package de.officeryoda.sdui.Responses.ResponseInformation;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Bookable {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("shortcut")
    private String shortcut;
}
