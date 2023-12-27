package de.officeryoda.sdui.unnecessaryResponseClasses;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import java.util.List;

@Data
public class Meta {

    @SerializedName("warnings")
    private List<String> warnings;

    @SerializedName("errors")
    private List<String> errors;

    @SerializedName("success")
    private List<String> success;

}
