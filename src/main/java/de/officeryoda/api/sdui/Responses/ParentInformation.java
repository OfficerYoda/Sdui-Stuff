package de.officeryoda.api.sdui.Responses;

import com.google.gson.annotations.SerializedName;
import de.officeryoda.api.sdui.Responses.ResponseInformation.UserData;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@lombok.Data
public class ParentInformation extends SduiBaseInformation {

    @SerializedName("data")
    private List<UserData> data;

}
