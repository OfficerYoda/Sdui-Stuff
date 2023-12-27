package de.officeryoda.sdui.Responses;

import com.google.gson.annotations.SerializedName;
import de.officeryoda.sdui.Responses.ResponseInformation.UserData;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@lombok.Data
public class ParentInformation extends BaseResponseInformation {

    @SerializedName("data")
    private List<UserData> data;

}
