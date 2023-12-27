package de.officeryoda.sdui.responseSubclasses;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class School {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("name_alias")
    private String nameAlias;

    @SerializedName("slink")
    private String slink;

    @SerializedName("state")
    private String state;

    @SerializedName("uuid")
    private String uuid;

    @SerializedName("url")
    private String url;

    @SerializedName("street")
    private String street;

    @SerializedName("is_beta")
    private boolean isBeta;

    @SerializedName("is_partner")
    private boolean isPartner;

    @SerializedName("shortcut")
    private String shortcut;

    @SerializedName("locale")
    private String locale;

    @SerializedName("environment")
    private String environment;

    @SerializedName("app_branding")
    private String appBranding;

    @SerializedName("old_id")
    private int oldId;

    @SerializedName("type")
    private Type type;

    @SerializedName("meta")
    private Meta meta;

    // @SerializedName("auth_driver") // Uncomment if you have a field named "auth_driver"
    // private AuthDriver authDriver;

    @SerializedName("timetable")
    private Timetable timetable;

    // @SerializedName("properties") // Uncomment if you have a field named "properties"
    // private Properties properties;

    @Data
    public static class Meta {
        @SerializedName("subtitle")
        private String subtitle;

        @SerializedName("uri")
        private String uri;

        @SerializedName("logo_uri")
        private String logoUri;

        @SerializedName("sdui_logo")
        private String sduiLogo;

        @SerializedName("footer_menu")
        private List<String> footerMenu;

        @SerializedName("news_channel_types")
        private List<String> newsChannelTypes;
    }

}
