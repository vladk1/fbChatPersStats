package sample;

import org.json.simple.JSONObject;

import java.io.*;
import java.util.ArrayList;

// populates files with messages
public class Populator {

    public static void populateFile(ArrayList<String> sentances, File messageFile) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(messageFile);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

            for (String sentence : sentances) {
                bw.write(sentence);
                bw.newLine();
            }

            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void populateJSONFile(JSONObject jsonObject, File messageFile) {
        try {
            FileWriter file = new FileWriter(messageFile.getPath());
            file.write(jsonObject.toJSONString());
            file.flush();
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
