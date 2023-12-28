package de.officeryoda.api.sdui.Responses;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class SduiBaseInformation {

    @SerializedName("status")
    private String status;

    @SerializedName("meta")
    private Meta meta;

    @Data
    public static class Meta {

        @SerializedName("warnings")
        private List<String> warnings;

        @SerializedName("errors")
        private List<String> errors;

        @SerializedName("success")
        private List<String> success;

        public boolean isEmpty() {
            return warnings.isEmpty() && errors.isEmpty() && success.isEmpty();
        }
    }
}
