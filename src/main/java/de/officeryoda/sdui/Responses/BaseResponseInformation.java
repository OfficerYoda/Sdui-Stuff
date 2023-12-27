package de.officeryoda.sdui.Responses;

import com.google.gson.annotations.SerializedName;
import de.officeryoda.sdui.Responses.ResponseInformation.ResponseMeta;
import lombok.Data;

@Data
public class BaseResponseInformation {

    @SerializedName("status")
    private String status;

    @SerializedName("meta")
    private ResponseMeta meta;

}
