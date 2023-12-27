package de.officeryoda.sdui.Responses.ResponseInformation;

import com.google.gson.annotations.SerializedName;
import de.officeryoda.sdui.Responses.BaseResponseInformation;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Root extends BaseResponseInformation {

    @SerializedName("data")
    private TimeTableData data;

}

