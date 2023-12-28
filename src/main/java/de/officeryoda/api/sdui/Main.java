package de.officeryoda.api.sdui;

import de.officeryoda.api.sdui.Responses.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        SduiApiHandler sduiApiHandler = new SduiApiHandler();

        UserInformation userInfo = sduiApiHandler.getUserInformation();
        ParentInformation parentInfo = sduiApiHandler.getParentInformation();
        TimeTableInformation timetable = sduiApiHandler.getTimetable();
        NewsInformation news = sduiApiHandler.getNews();


        System.out.println("userInfo = " + userInfo);
        System.out.println("parentInfo = " + parentInfo);
        System.out.println("timetable = " + timetable.hashCode() + " (hash because -toString() is to long)");
        System.out.println("news = " + news.hashCode() + " (hash because -toString() is possibly multiline)");
    }

    public static void writeToFile(String input, String id) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("toLongForConsole" + (id.isEmpty() ? "" : "-" + id) + ".json"))) {
            writer.write(input);
            System.out.println("Data has been written to the file successfully (" + id + ").");
        } catch(IOException e) {
            System.err.println("An error occurred while writing to the file: (" + id + ")" + e.getMessage());
        }
    }
}
