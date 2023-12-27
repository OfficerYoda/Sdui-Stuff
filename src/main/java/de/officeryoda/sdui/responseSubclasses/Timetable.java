package de.officeryoda.sdui.unnecessaryResponseClasses;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class Timetable {

    @SerializedName("provider")
    private String provider;

    @SerializedName("id")
    private int id;

    @SerializedName("process_started_at")
    private int processStartedAt;

    @SerializedName("processed_at")
    private int processedAt;

    @SerializedName("last_updated_at")
    private int lastUpdatedAt;

    @SerializedName("active_schoolyear_id")
    private int activeSchoolyearId;

    @SerializedName("schoolyears")
    private List<SchoolYear> schoolyears;

    @SerializedName("meta")
    private Meta meta;


    public SchoolYear getActiveSchoolYear() {
        for(SchoolYear sy : schoolyears) {
            if(sy.getId() == getActiveSchoolyearId()) {
                return sy;
            }
        }

        return null;
    }

    @Data
    public static class Meta {
        @SerializedName("state")
        private String state;
    }
}
