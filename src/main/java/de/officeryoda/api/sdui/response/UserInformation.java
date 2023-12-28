package de.officeryoda.api.sdui.response;

import com.google.gson.annotations.SerializedName;
import de.officeryoda.api.sdui.response.data.user.UserData;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@lombok.Data
public class UserInformation extends SduiBaseInformation {
    private UserData data;
}
