package de.officeryoda.api.sdui.Responses.ResponseInformation;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class PrimaryRole extends Role {

    @SerializedName("translated_description")
    private String translatedDescription;

}
