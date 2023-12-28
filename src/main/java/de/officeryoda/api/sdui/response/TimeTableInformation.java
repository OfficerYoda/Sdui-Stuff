package de.officeryoda.api.sdui.response;

import com.google.gson.annotations.SerializedName;
import de.officeryoda.api.sdui.response.data.timetable.Timetable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TimeTableInformation extends SduiBaseInformation {
    private Timetable data;
}
