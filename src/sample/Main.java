package sample;


import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import sample.beams.Chat;
import sample.beams.UserMessage;
import sample.file.FileParse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

//import static com.apple.eio.FileManager.getResource;
import static sample.filters.ChatTitleFilter.*;

public class Main {

    private static String[] chatTitleArray = { };
    private TFIDFCounter tfidfCounter = new TFIDFCounter();


    public static void main(String[] args) {
        Main main = new Main();
        FileParse fileParse = new FileParse();

        System.out.println("Start analysis\n");

        List<Node> threads = fileParse.getContentThread();

        if (threads != null) {

            List<Node> chatThreads = new ArrayList<Node>();

            for (int i = 1; i < threads.size(); i++) {
                chatThreads.addAll(threads.get(i).childNodes());
            }

    //      Iterate through define chats
    //      ArrayList<Chat> chatArray = main.iterateThroughChatArray(chatTitleArray, chatThreads);

    //      Iterate through all dialogs
            ArrayList<Chat> chatArray = main.iterateIfidfThroughBinaryChats(chatThreads);

            main.generateTfIdf(chatArray);
            main.generateChatFrequencyGraph(chatArray);

            SimilarityGenerator similarityGenerator = new SimilarityGenerator();
            similarityGenerator.generateFiles("src/files/tfidf/jsons/");

        }
    }

    private void generateChatFrequencyGraph(ArrayList<Chat> chatArray) {
        ChatFrequencyGenerator chatFrequencyGenerator = new ChatFrequencyGenerator();
        chatFrequencyGenerator.generateFiles(chatArray);
    }


    private void generateTfIdf(ArrayList<Chat> chatArray) {
        TFIDFGenerator tfidfGenerator = new TFIDFGenerator();
        tfidfGenerator.generateFiles(chatArray);

        HashMap<String, Integer> totalWordsHashMap = tfidfCounter.getTotalWordsHashMap();
        LinkedHashMap sortedTotalWordsHashMap = FormatTranslator.sortHashMapByValuesI(totalWordsHashMap);
        tfidfGenerator.generateTotalTopTagTable(sortedTotalWordsHashMap);
    }


    // iterates through all dialogs
    private ArrayList<Chat> iterateIfidfThroughBinaryChats(List<Node> chatThreads) {
        ArrayList<Chat> chatArray = new ArrayList<Chat>();

        for (Node eachChatThreadNode : chatThreads) {
            List<Node> messageNodes = eachChatThreadNode.childNodes();
            TextNode chatThreadTitleTextNode = (TextNode) messageNodes.get(0);
            String eachBinaryChatTitle = chatThreadTitleTextNode.text();

            if (isBinaryChat(eachBinaryChatTitle, messageNodes.size())) {
                Chat newChat = createNewChat(eachBinaryChatTitle, messageNodes);
                chatArray.add(newChat);
            }

        }
        return chatArray;

    }

    // iterates through chatTitleArray
    private ArrayList<Chat> iterateThroughChatArray(String[] chatTitleArray, List<Node> chatThreads) {
        TFIDFGenerator tfidfGenerator = new TFIDFGenerator();
        ArrayList<Chat> chatArray = new ArrayList<Chat>();

        for (String eachChatTitle : chatTitleArray) {

            List<Node> chatMessages = extractChatListFromTitle(chatThreads, eachChatTitle);

            if (isBinaryChat(eachChatTitle, chatMessages.size())) {
                Chat newChat = createNewChat(eachChatTitle, chatMessages);
                chatArray.add(newChat);
            }
        }
        return chatArray;
//        tfidfGenerator.generateFiles(chatArray);
    }


    private List<Node> extractChatListFromTitle(List<Node> chatThreads, String chatSearchTitle) {
        for (Node eachChatThreadNode : chatThreads) {
            List<Node> messageNodes = eachChatThreadNode.childNodes();
            TextNode chatThreadTitleTextNode = (TextNode) messageNodes.get(0);
            String chatTitle = chatThreadTitleTextNode.text();
            if (chatTitle.equals(chatSearchTitle)) {
                System.out.println("extract chat with title " + chatTitle);
                return messageNodes;
            }
        }
        return null;
    }




    private Chat createNewChat(String chatTitle, List<Node> messageNodes) {
        System.out.println("New Chat " + chatTitle);
        Chat newChat = new Chat(chatTitle);
        ArrayList<UserMessage> userMessages = FormatTranslator.fbMessageThreadsToSentence(messageNodes);
        newChat.setUserMessages(userMessages);
        HashMap<String, Integer> chatWordFreq = tfidfCounter.getWordsInChatMap(chatTitle, userMessages);
        newChat.setWordFrequencyMap(chatWordFreq);
        return newChat;
    }



}
