package de.officeryoda.sdui.Responses.ResponseInformation;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TimeTableData {
    @SerializedName("lessons")
    private List<Lesson> lessons;

    @SerializedName("last_updated_at")
    private Date lastUpdatedAt;
}
