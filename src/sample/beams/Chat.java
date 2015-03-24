package sample.beams;

import java.util.ArrayList;
import java.util.HashMap;

public class Chat {


    private final String title;
    private HashMap<String, Integer> wordsInChatHashMap;
    private ArrayList<UserMessage> userMessages;

    public Chat(String title) {
        this.title = title;
    }


    public void setWordFrequencyMap(HashMap<String, Integer> wordsInChatHashMap) {
        this.wordsInChatHashMap = wordsInChatHashMap;
    }

    public String getTitle() {
        return title;
    }

    public HashMap<String, Integer> getWordFrequencyMap() {
        return wordsInChatHashMap;
    }

    public void setUserMessages(ArrayList<UserMessage> userMessages) {
        this.userMessages = userMessages;
    }

    public ArrayList<UserMessage> getUserMessages() {
        return this.userMessages;
    }
}
