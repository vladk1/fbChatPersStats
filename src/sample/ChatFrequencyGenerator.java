package sample;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import sample.beams.Chat;
import sample.beams.UserMessage;
import sample.file.MessagesFile;

import java.util.*;

public class ChatFrequencyGenerator {

    public void generateFiles(ArrayList<Chat> chatArray) {
        MessagesFile messagesFile = new MessagesFile();
        for (Chat eachChat : chatArray) {
            Multimap<Date, String> multiMap = createFrequencyFileForChat(eachChat);

            ArrayList<String> reversedStringList = new ArrayList<String>();
            JSONObject outputObject = new JSONObject();

            // get all the set of keys
            Set<Date> keys = multiMap.keySet();
            // iterate through the key set and display key and values
            for (Date date : keys) {
                outputObject.put(date.getTime()/1000, multiMap.get(date).size());
                reversedStringList.add(date.toString() + "   " + multiMap.get(date).size());
            }

            messagesFile.createDateFrequencyJsonFile(eachChat.getTitle(), outputObject);
            messagesFile.createDateFrequencyFile(eachChat.getTitle(), reversedStringList);
        }
    }

    private Multimap<Date, String> createFrequencyFileForChat(Chat chat) {
        // create multimap to store key and values
        Multimap<Date, String> multiMap = ArrayListMultimap.create();

        ArrayList<UserMessage> userMessages = chat.getUserMessages();
        for (UserMessage userMessage : userMessages) {
            String date = userMessage.getDate();
            String author = userMessage.getAuthor();
            Date date2 = userMessage.getDate2();
            multiMap.put(date2, author);
        }

        return  multiMap;
    }

}
