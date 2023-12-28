package de.officeryoda.api.sdui.response.data.timetable;

import com.google.gson.annotations.SerializedName;
import de.officeryoda.api.sdui.response.data.timetable.Lesson;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Timetable {
    private List<Lesson> lessons;
    @SerializedName("last_updated_at")
    private Date lastUpdatedAt;
}
