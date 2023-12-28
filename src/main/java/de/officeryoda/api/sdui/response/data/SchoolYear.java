package de.officeryoda.api.sdui.response.data;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SchoolYear {
    private int id;
    @SerializedName("timetable_id")
    private int timetableId;
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
    private Meta meta;

    @Data
    public static class Meta {
        @SerializedName("displayname")
        private String displayName;
        @SerializedName("is_active")
        private boolean isActive;
        private String shortname;
        private List<String> files;
        @SerializedName("grades_count")
        private int gradesCount;
        @SerializedName("courses_count")
        private int coursesCount;
        @SerializedName("subjects_count")
        private int subjectsCount;
        @SerializedName("bookables_count")
        private int bookablesCount;
    }
}