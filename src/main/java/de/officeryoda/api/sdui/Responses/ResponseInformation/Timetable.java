package de.officeryoda.api.sdui.Responses.ResponseInformation;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Timetable {
    @SerializedName("lessons")
    private List<Lesson> lessons;

    @SerializedName("last_updated_at")
    private Date lastUpdatedAt;
}
