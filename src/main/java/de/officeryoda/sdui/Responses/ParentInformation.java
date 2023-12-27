package de.officeryoda.sdui.Responses;

import com.google.gson.annotations.SerializedName;
import de.officeryoda.sdui.Responses.ResponseInformation.Data;
import de.officeryoda.sdui.Responses.ResponseInformation.Meta;

import java.util.List;

@lombok.Data
public class ParentInformation {

    @SerializedName("data")
    private List<Data> data;

    @SerializedName("status")
    private String status;

    @SerializedName("meta")
    private Meta meta;

}
