package de.officeryoda.api.sdui.Responses.ResponseInformation;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SchoolYear {

    @SerializedName("id")
    private int id;

    @SerializedName("timetable_id")
    private int timetableId;

    @SerializedName("key")
    private String key;

    @SerializedName("begins_at")
    private Date beginsAt;

    @SerializedName("ends_at")
    private Date endsAt;

    @SerializedName("created_at")
    private Date createdAt;

    @SerializedName("updated_at")
    private Date updatedAt;

    @SerializedName("pending_upload_group_id")
    private int pendingUploadGroupId;

    @SerializedName("meta")
    private Meta meta;

    @Data
    public static class Meta {
        @SerializedName("displayname")
        private String displayName;

        @SerializedName("is_active")
        private boolean isActive;

        @SerializedName("shortname")
        private String shortname;

        @SerializedName("files")
        private List<String> files;

        @SerializedName("grades_count")
        private Integer gradesCount;

        @SerializedName("courses_count")
        private Integer coursesCount;

        @SerializedName("subjects_count")
        private Integer subjectsCount;

        @SerializedName("bookables_count")
        private Integer bookablesCount;
    }

}