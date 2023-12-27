package de.officeryoda.sdui;

import de.officeryoda.sdui.Responses.ResponseInformation.Lesson;
import de.officeryoda.sdui.Responses.ParentInformation;
import de.officeryoda.sdui.Responses.ResponseInformation.TimeTableData;
import de.officeryoda.sdui.Responses.TimeTableInformation;
import de.officeryoda.sdui.Responses.UserInformation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        SduiApiHandler sduiApiHandler = new SduiApiHandler();
        UserInformation userInfo = sduiApiHandler.getUserInformation();
        ParentInformation parentInfo = sduiApiHandler.getParentInformation();
        TimeTableInformation timetable = sduiApiHandler.getTimetable();

        System.out.println("userInfo = " + userInfo);
        System.out.println("parentInfo = " + parentInfo);
        System.out.println("timetable = " + timetable.hashCode() + " (hash because -toString() is to long)");
//        System.out.println("timetable = " + timetable.toString());
    }

    public static void writeToFile(String input, int id) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("toLongForConsole" + id  + ".json"))) {
            writer.write(input);
            System.out.println("Data has been written to the file successfully.");
        } catch(IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
    public static void writeToFile(String input) {
        writeToFile(input, 0);
    }
}
