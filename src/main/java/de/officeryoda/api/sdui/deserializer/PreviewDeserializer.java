package de.officeryoda.api.sdui.deserializer;

import com.google.gson.*;
import de.officeryoda.api.sdui.response.data.news.Preview;

import java.lang.reflect.Type;

public class PreviewDeserializer implements JsonDeserializer<Preview> {

    @Override
    public Preview deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json.isJsonPrimitive() && json.getAsJsonPrimitive().isString() && json.getAsString().isEmpty()) {
            return null; // Return null if the entire preview object is an empty string
        }

        JsonObject jsonObject = json.getAsJsonObject();

        Preview preview = new Preview();
        preview.setId(jsonObject.get("id").getAsInt());
        preview.setUuid(getStringOrNull(jsonObject, "uuid"));
        preview.setUserId(jsonObject.get("user_id").getAsInt());
        preview.setSourceId(jsonObject.get("source_id").getAsInt());
        preview.setSourceType(getStringOrNull(jsonObject, "source_type"));
        preview.setName(getStringOrNull(jsonObject, "name"));
        preview.setType(getStringOrNull(jsonObject, "type"));
        preview.setExtension(getStringOrNull(jsonObject, "extension"));
        preview.setSize(jsonObject.get("size").getAsInt());
        preview.setCreatedAt(getStringOrNull(jsonObject, "created_at"));
        preview.setUpdatedAt(getStringOrNull(jsonObject, "updated_at"));
        preview.setFileType(getStringOrNull(jsonObject, "file_type"));

        // Deserialize Meta
        JsonObject metaObject = jsonObject.getAsJsonObject("meta");
        if (metaObject != null) {
            Preview.Meta meta = new Preview.Meta();
            meta.setUri(getStringOrNull(metaObject, "uri"));
            meta.setDownloadUri(getStringOrNull(metaObject, "download_uri"));
            meta.setTempUri(getStringOrNull(metaObject, "temp_uri"));
            preview.setMeta(meta);
        }

        return preview;
    }

    private String getStringOrNull(JsonObject jsonObject, String memberName) {
        JsonElement element = jsonObject.get(memberName);
        return element.isJsonNull() ? null : element.getAsString();
    }
}
