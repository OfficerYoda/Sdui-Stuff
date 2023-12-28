package de.officeryoda.api.sdui.response.data;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class School {
    private int id;
    private String name;
    @SerializedName("name_alias")
    private String nameAlias;
    private String slink;
    private String state;
    private String uuid;
    private String url;
    private String street;
    @SerializedName("is_beta")
    private boolean isBeta;
    @SerializedName("is_partner")
    private boolean isPartner;
    private String shortcut;
    private String locale;
    private String environment;
    @SerializedName("app_branding")
    private String appBranding;
    @SerializedName("old_id")
    private int oldId;
    private Type type;
    private Meta meta;
    @SerializedName("timetable")
    private SchoolYearsTimetable schoolYearstimeTable;

    @Data
    public static class Meta {
        private String subtitle;
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

    @Data
    public static class Type {
        private String key;
        private String name;
        private String level;
    }
}
