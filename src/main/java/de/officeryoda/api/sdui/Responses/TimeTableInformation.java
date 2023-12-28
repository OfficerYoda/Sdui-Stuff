package de.officeryoda.api.sdui.Responses;

import com.google.gson.annotations.SerializedName;
import de.officeryoda.api.sdui.Responses.ResponseInformation.Timetable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TimeTableInformation extends SduiBaseInformation {

    @SerializedName("data")
    private Timetable data;

}
