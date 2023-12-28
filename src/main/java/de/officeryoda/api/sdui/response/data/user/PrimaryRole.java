package de.officeryoda.api.sdui.response.data.user;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PrimaryRole extends Role {
    @SerializedName("translated_description")
    private String translatedDescription;
}
