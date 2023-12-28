package de.officeryoda.api.sdui.Responses.ResponseInformation;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SchoolYearsTimetable {

    @SerializedName("provider")
    private String provider;

    @SerializedName("id")
    private int id;

    @SerializedName("process_started_at")
    private Date processStartedAt;

    @SerializedName("processed_at")
    private Date processedAt;

    @SerializedName("last_updated_at")
    private Date lastUpdatedAt;

    @SerializedName("active_schoolyear_id")
    private int activeSchoolYearId;

    @SerializedName("schoolyears")
    private List<SchoolYear> schoolYears;

    @SerializedName("meta")
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
        @SerializedName("state")
        private String state;
    }
}
