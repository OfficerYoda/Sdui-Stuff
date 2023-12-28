package de.officeryoda.api.sdui.Responses;

import com.google.gson.annotations.SerializedName;
import de.officeryoda.api.sdui.Responses.ResponseInformation.UserData;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@lombok.Data
public class UserInformation extends SduiBaseInformation {

    @SerializedName("data")
    private UserData data;

}
