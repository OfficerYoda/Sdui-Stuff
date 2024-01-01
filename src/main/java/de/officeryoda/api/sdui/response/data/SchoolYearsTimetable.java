package de.officeryoda.api.sdui.response.data;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class SchoolYearsTimetable {
    private String provider;
    private int id;
    @SerializedName("process_started_at")
    private LocalDateTime processStartedAt;
    @SerializedName("processed_at")
    private LocalDateTime processedAt;
    @SerializedName("last_updated_at")
    private LocalDateTime lastUpdatedAt;
    @SerializedName("active_schoolyear_id")
    private int activeSchoolYearId;
    @SerializedName("schoolyears")
    private List<SchoolYear> schoolYears;
    private Meta meta;

    public SchoolYear getActiveSchoolYear() {
        for(SchoolYear sy : schoolYears) {
            if(sy.getId() == getActiveSchoolYearId()) {
                return sy;
            }
        }
        return null;
    }

    @Data
    public static class Meta {
        private String state;
    }
}
