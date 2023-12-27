package de.officeryoda.sdui;

import de.officeryoda.sdui.responseSubclasses.Data;
import de.officeryoda.sdui.responseSubclasses.Meta;

@lombok.Data
public class SduiApiResponse {

    private Data data;
    private String status;
    private Meta meta;
}
