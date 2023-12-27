package de.officeryoda.sdui.Responses;

import com.google.gson.annotations.SerializedName;
import de.officeryoda.sdui.Responses.ResponseInformation.TimeTableData;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TimeTableInformation extends BaseResponseInformation {

    @SerializedName("data")
    private TimeTableData data;

}
