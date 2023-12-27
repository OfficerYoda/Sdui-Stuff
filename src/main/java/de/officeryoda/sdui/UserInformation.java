package de.officeryoda.sdui;

import de.officeryoda.sdui.responseSubclasses.Data;
import de.officeryoda.sdui.responseSubclasses.Meta;

@lombok.Data
public class UserInformation {

    private Data data;
    private String status;
    private Meta meta;
}
