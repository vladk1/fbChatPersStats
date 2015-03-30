package sample.file;


import org.json.simple.JSONObject;
import sample.Populator;
import java.io.*;
import java.util.*;



public class MessagesFile {


    public void createTfidfJsonFile(String chatTitle, JSONObject jsonObject) {
        String filePath = FilePathDir.getChatTfidfJsonFilePath(chatTitle + "Tfidf");
        File messagesFile = createFile(filePath);
        Populator.populateJSONFile(jsonObject, messagesFile);
    }

    public void createTfidfFile(String chatTitle, ArrayList<String> sentences) {
        String filePath = FilePathDir.getChatTfidfFilePath(chatTitle + "Tfidf");
        File messagesFile = createFile(filePath);
        Populator.populateFile(sentences, messagesFile);
    }
    public void createTopTfidfJsonFile(String topTags, JSONObject jsonObject) {
        String filePath = FilePathDir.getTopTfidfJsonFilePath(topTags);
        File messagesFile = createFile(filePath);
        Populator.populateJSONFile(jsonObject, messagesFile);
    }

    public void createTopTfidfFile(String topTags, ArrayList<String> reversedfileText) {
        String filePath = FilePathDir.getTopTfidfFilePath(topTags);
        File messagesFile = createFile(filePath);
        Populator.populateFile(reversedfileText, messagesFile);
    }

    public void createDateFrequencyFile(String chatTitle, ArrayList<String> sentences) {
        String filePath = FilePathDir.getChatDateFrequencyFilePath(chatTitle + "DateF");
        File messagesFile = createFile(filePath);
        Populator.populateFile(sentences, messagesFile);
    }

    public void createDateFrequencyJsonFile(String chatTitle, JSONObject jsonObject) {
        String filePath = FilePathDir.getChatDateFrequencyJsonFilePath(chatTitle + "DateF");
        File messagesFile = createFile(filePath);
        Populator.populateJSONFile(jsonObject, messagesFile);
    }

    public void createSimilarityFile(String chatTitle, ArrayList<String> sentences) {
        String filePath = FilePathDir.getSimilarityFilePath(chatTitle);
        File messagesFile = createFile(filePath);
        Populator.populateFile(sentences, messagesFile);
    }
    
    public void createSimilarityJsonFile(String chatTitle, JSONObject jsonObject) {
        String filePath = FilePathDir.getSimilarityJsonFilePath(chatTitle);
        File messagesFile = createFile(filePath);
        Populator.populateJSONFile(jsonObject, messagesFile);
    }

    private File createFile(String filePath) {
        File file = null;
        try {

            file = new File(filePath);
            file.getParentFile().mkdirs();

            if (file.createNewFile()){
                System.out.println("New File is created!");
            } else {
                System.out.println("File already exists.");
            }

        } catch (IOException e) {
            System.out.println("Bad path. New File is not created!");
            e.printStackTrace();
        }
        return file;
    }

}
