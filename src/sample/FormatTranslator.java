package sample;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import sample.beams.UserMessage;

import java.util.*;

// translates messages objects to different format
public class FormatTranslator {

    // translates fbMessages to UserMessage object
    public static ArrayList<UserMessage> fbMessageThreadsToSentence(List<Node> messageThreads) {
        ArrayList<UserMessage> outputUserMessagesArray = new ArrayList<UserMessage>();

        TextNode chatThreadTitleTextNode = (TextNode) messageThreads.get(0);
        String chatTitle = chatThreadTitleTextNode.text();
        String users[] = chatTitle.split(", ");
        String user1 = users[0];
        String user2 = users[1];

        for (int i=messageThreads.size()-1; i>1; i-=2) {
            Element messageMetaDataElement = (Element) messageThreads.get(i-1);
            Element messageTextElement = (Element) messageThreads.get(i);

            Element metadataElement = messageMetaDataElement.child(0);
            Element userElement = metadataElement.child(0);
            Element dateElement = metadataElement.child(1);

            TextNode userTextNode = (TextNode) userElement.childNode(0);
            String authorString = userTextNode.text();
            TextNode dateTextNode = (TextNode) dateElement.childNode(0);
            String dateString = dateTextNode.text();

            if (messageTextElement != null) {

                if (messageTextElement.childNodes().size() > 0) {

                    TextNode messageTextNode = (TextNode) messageTextElement.childNode(0);
                    String message = messageTextNode.text();
                    String replier = (authorString.equals(user1)) ? user2 : user1;

                    UserMessage userMessage = new UserMessage(message, authorString, replier, dateString);
                    outputUserMessagesArray.add(userMessage);
               }

            }

        }

        return outputUserMessagesArray;

    }



    public static LinkedHashMap sortHashMapByValuesD(HashMap passedMap) {
        List mapKeys = new ArrayList(passedMap.keySet());
        List mapValues = new ArrayList(passedMap.values());
        Collections.sort(mapValues);
        Collections.sort(mapKeys);

        LinkedHashMap sortedMap = new LinkedHashMap();
        LinkedHashMap descsortedMap = new LinkedHashMap();

        Iterator valueIt = mapValues.iterator();
        while (valueIt.hasNext()) {
            Object val = valueIt.next();
            Iterator keyIt = mapKeys.iterator();

            while (keyIt.hasNext()) {
                Object key = keyIt.next();
                String comp1 = passedMap.get(key).toString();
                String comp2 = val.toString();

                if (comp1.equals(comp2)){
                    passedMap.remove(key);
                    mapKeys.remove(key);
                    sortedMap.put(key, val);
                    break;
                }

            }

        }

        return sortedMap;
    }

    public static LinkedHashMap sortHashMapByValuesI(HashMap passedMap) {
        List mapKeys = new ArrayList(passedMap.keySet());
        List mapValues = new ArrayList(passedMap.values());
        Collections.sort(mapValues);
        Collections.sort(mapKeys);

        LinkedHashMap sortedMap = new LinkedHashMap();
        LinkedHashMap descsortedMap = new LinkedHashMap();

        Iterator valueIt = mapValues.iterator();
        while (valueIt.hasNext()) {
            Object val = valueIt.next();
            Iterator keyIt = mapKeys.iterator();

            while (keyIt.hasNext()) {
                Object key = keyIt.next();
                String comp1 = passedMap.get(key).toString();
                String comp2 = val.toString();

                if (comp1.equals(comp2)){
                    passedMap.remove(key);
                    mapKeys.remove(key);
                    sortedMap.put(key, val);
                    break;
                }

            }

        }

        return sortedMap;
    }

}
