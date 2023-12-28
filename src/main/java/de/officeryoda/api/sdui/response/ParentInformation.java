package de.officeryoda.api.sdui.response;

import com.google.gson.annotations.SerializedName;
import de.officeryoda.api.sdui.response.data.user.UserData;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@lombok.Data
public class ParentInformation extends SduiBaseInformation {
    private List<UserData> data;
}
