package sample;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import sample.beams.Chat;
import sample.file.MessagesFile;

import java.util.*;

public class TFIDFGenerator {

    private static final Double MIN_TFIDF_VALUE = 0.001;
    private static final int TOP_NUMBER = 50;
    HashMap<String, Double> idfHashMap = new HashMap<String, Double>();


    public void generateFiles(ArrayList<Chat> chatArray) {
        MessagesFile messagesFile = new MessagesFile();

        for (Chat eachChat : chatArray) {
            Deque<String> wordQueue = new ArrayDeque<String>();
            Deque<Double> ratioQueue = new ArrayDeque<Double>();

            HashMap<String, Integer> chatWordFreq = eachChat.getWordFrequencyMap();
            LinkedHashMap sortedChatTFIDFHashMap = iterateThroughWords(chatWordFreq, chatArray);

            Set keySet = sortedChatTFIDFHashMap.keySet();
            int counter = 0;
            Iterator iterator = keySet.iterator();
            while ((counter < sortedChatTFIDFHashMap.size()) && iterator.hasNext()) {
                String key = (String)iterator.next();
                Double value = (Double) sortedChatTFIDFHashMap.get(key);
                if (value > MIN_TFIDF_VALUE) {

                    wordQueue.add(key);
                    ratioQueue.add((Double) sortedChatTFIDFHashMap.get(key));
//                    fileText.add(key + "        " + sortedChatTFIDFHashMap.get(key));
                }
                counter++;
            }
            ArrayList<String> reversedStringList = new ArrayList<String>();
            JSONArray reversedJsonChatList = new JSONArray();
            while (wordQueue.size() > 0) {
                String word = wordQueue.removeLast();
                Double ratio = ratioQueue.removeLast();
                JSONObject jsonChat = new JSONObject();
                jsonChat.put("word", word);
                jsonChat.put("ratio", ratio);

                reversedJsonChatList.add(jsonChat);
                reversedStringList.add(word+"   "+ratio);
            }

            // todo reversedfileText to JSON
            JSONObject outputObject = new JSONObject();
            outputObject.put("words", reversedJsonChatList);
            messagesFile.createTfidfJsonFile(eachChat.getTitle(), outputObject);
            messagesFile.createTfidfFile(eachChat.getTitle(), reversedStringList);
        }

    }




    public void generateTotalTopTagTable(LinkedHashMap sortedTotalWordsHashMap) {
        MessagesFile messagesFile = new MessagesFile();
        Deque<String> wordsQueue = new ArrayDeque<String>();
        Deque<Integer> wordsFreqQueue = new ArrayDeque<Integer>();

        Set keySet = sortedTotalWordsHashMap.keySet();
        int counter = 0;
        Iterator iterator = keySet.iterator();
        while ((counter < sortedTotalWordsHashMap.size()) && iterator.hasNext()) {
            String key = (String)iterator.next();
            Integer value = (Integer) sortedTotalWordsHashMap.get(key);
            if (value > MIN_TFIDF_VALUE) {
                wordsQueue.add(key);
                wordsFreqQueue.add((Integer)sortedTotalWordsHashMap.get(key));
            }
            counter++;
        }
        ArrayList<String> reversedfileText = new ArrayList<String>();
        JSONArray jsonArray = new JSONArray();
        int reversedCounter = 0;
        while ((reversedCounter < TOP_NUMBER) && (wordsQueue.size()>0) ){
            String word = wordsQueue.removeLast();
            Integer freq = wordsFreqQueue.removeLast();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("word", word);
            jsonObject.put("freq", freq);

            jsonArray.add(jsonObject);
            reversedfileText.add(word+"    "+freq);
            reversedCounter++;
        }

        // todo reversedfileText to JSON
        messagesFile.createTopTfidfFile("topTags", reversedfileText);
        JSONObject outputJsonObject = new JSONObject();
        outputJsonObject.put("toptags", jsonArray);
        messagesFile.createTopTfidfJsonFile("topTags", outputJsonObject);
    }

    private LinkedHashMap iterateThroughWords(HashMap<String, Integer> chatWordFreq, ArrayList<Chat> chatArray) {

        HashMap<String, Double> ifidfHashMap = new HashMap<String, Double>();

        for (String eachWord : chatWordFreq.keySet()) {
            double tf = getTF(eachWord, chatWordFreq);
            double idf = getIDF(eachWord, chatArray);
            ifidfHashMap.put(eachWord, tf*idf);
        }

        return FormatTranslator.sortHashMapByValuesD(ifidfHashMap);
    }

    private Double getIDF(String word, ArrayList<Chat> chatArray) {
        if (idfHashMap.containsKey(word)) {
            double idf = idfHashMap.get(word);
            return idf;
        } else {
            int wordSpottedDocuments = 0;
            for (Chat eachChat : chatArray) {
                HashMap<String, Integer> chatWordFreq = eachChat.getWordFrequencyMap();
                if (chatWordFreq.containsKey(word)) {
                    wordSpottedDocuments++;
                }
            }
            double idf = Math.log(chatArray.size() / wordSpottedDocuments);

            idfHashMap.put(word, idf);
            return idf;
        }
    }


    private double getTF(String word, HashMap<String, Integer> chatWordFreq) {
        double wordFreqInChat = chatWordFreq.get(word);
        double totalWordsNum = chatWordFreq.size();
        double result = wordFreqInChat / totalWordsNum;
        return result;
    }
}
