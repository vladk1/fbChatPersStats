package sample;


import sample.beams.UserMessage;
import sample.filters.WordFilter;

import java.util.HashMap;
import java.util.List;

// in first implementation of TFIDF let's (number of documents)==(number of sentences)
public class TFIDFCounter {

    private String Log = "TFIDF/";


    private HashMap<String, Integer> wordsInChatHashMap = new HashMap<String, Integer>();
    private HashMap<String, Integer> wordsInTotalHashMap = new HashMap<String, Integer>();


    public HashMap<String, Integer> getWordsInChatMap(String chatTitle, List<UserMessage> userMessages) {
        System.out.println(Log+" starting analysis..\n");
        wordsInChatHashMap = new HashMap<String, Integer>();

        OpenNLP openNLP = new OpenNLP();
        for (UserMessage eachUserMessage : userMessages) {
            applyCounterOnMessage(eachUserMessage.getMessage(), openNLP);
        }
        openNLP.closeTokinizerIs();

        return wordsInChatHashMap;

//        todo Change to Java8 count of diff words
//        tokens.stream().filter(x-> x=="vlad").count()
    }


    public HashMap<String, Integer> getTotalWordsHashMap() {
        return wordsInTotalHashMap;
    }

    private void applyCounterOnMessage(String message, OpenNLP openNLP) {


        Object[] words =  openNLP.tokenize(message);


        for (int i=0; i<words.length; i++) {

            String word = (String) words[i];
            if (WordFilter.appropriate(word)) {
                incrementWordCounterFor(word, wordsInChatHashMap);
                incrementWordCounterFor(word, wordsInTotalHashMap);
            }

        }

    }

    private void incrementWordCounterFor(String word, HashMap<String, Integer> wordsHashMap) {
        if (wordsHashMap.containsKey(word)) {
            wordsHashMap.put(word, wordsHashMap.get(word)+1);
        } else {
            wordsHashMap.put(word, 1);
        }
    }


}
