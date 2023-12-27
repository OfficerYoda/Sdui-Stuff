package de.officeryoda.sdui;

import de.officeryoda.sdui.Responses.ParentInformation;
import de.officeryoda.sdui.Responses.UserInformation;

public class Main {

    public static void main(String[] args) {
        SduiApiHandler sduiApiHandler = new SduiApiHandler();
        UserInformation userInfo = sduiApiHandler.getUserInformation();
        ParentInformation parentInfo = sduiApiHandler.getParentInformation();

        System.out.println("userInfo = " + userInfo);
        System.out.println("parentInfo = " + parentInfo);
    }
}
