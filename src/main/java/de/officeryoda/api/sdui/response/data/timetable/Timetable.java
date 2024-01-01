package de.officeryoda.api.sdui.response.data.timetable;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Timetable {
    private List<Lesson> lessons;
    @SerializedName("last_updated_at")
    private LocalDateTime lastUpdatedAt;
}
