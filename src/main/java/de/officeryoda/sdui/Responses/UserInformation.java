package de.officeryoda.sdui.Responses;

import com.google.gson.annotations.SerializedName;
import de.officeryoda.sdui.Responses.ResponseInformation.UserData;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@lombok.Data
public class UserInformation extends BaseResponseInformation {

    @SerializedName("data")
    private UserData data;

}
