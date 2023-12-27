package de.officeryoda.sdui.Responses;

import com.google.gson.annotations.SerializedName;
import de.officeryoda.sdui.Responses.ResponseInformation.Data;
import de.officeryoda.sdui.Responses.ResponseInformation.Meta;

@lombok.Data
public class UserInformation {

    @SerializedName("data")
    private Data data;

    @SerializedName("status")
    private String status;

    @SerializedName("meta")
    private Meta meta;

}
