package sample;

import sample.beams.UserMessage;

import java.util.ArrayList;

// apply different NLP methods on filteredMessages
public class NLP {


    private ArrayList<String> filteredMessages = new ArrayList<String>();


    public ArrayList<String> applyCoreferenceResolution(ArrayList<UserMessage> userMessages) {
        for (UserMessage eachUserMessage : userMessages) {
            applyFilterOnMessage(eachUserMessage.getMessage(), eachUserMessage.getAuthor(), eachUserMessage.getReplier());
        }
        return filteredMessages;
    }


    private void applyFilterOnMessage(String message, String author, String replier) {

        String words[] = message.split(" ");
        String newMessage = filterWords(words, author, replier);

        String messages[] = newMessage.split(".");
        if (messages.length > 0) {
            for (String eachMessage : messages) {
                System.out.println("eachMessage=" + eachMessage);
                if (isAppropriate(eachMessage, author, replier)) {
                    filteredMessages.add(eachMessage);
                }
            }
        } else {
            if (isAppropriate(newMessage, author, replier)) {
                filteredMessages.add(newMessage);
            }
        }

    }

    private String filterWords(String[] words, String author, String replier) {
        String newMessage = "";
//
        for (int i=0; i<words.length; i++) {
            String word = words[i];


            String nextWord = "";
            if ((i+1) < words.length) {
                nextWord = words[i+1];
            }

            if ( (word.equals("I") && (nextWord.equals("am")) ) || (word.equals("i") && (nextWord.equals("am")) ) ) {
                words[i] = author;
                words[i+1] = "";
            }

            else if (word.equals("I") || word.equals("i") || word.equals("me")) {
                words[i] = author;
            }

            else if (word.equals("you")  && nextWord.equals("are")) {
                words[i] = replier;
                words[i+1] = "";
            }

            else if (word.equals("you")) {
                words[i] = replier;
            }


            newMessage += words[i];
            newMessage += " ";
        }

        return newMessage;
    }



    private boolean isAppropriateToCount(String word) {
        if (word.equals("")) {

        }
        return false;
    }



    private boolean isAppropriate(String newMessage, String user1, String user2) {
        if (isAboutPeople(newMessage, user1, user2) && !isQuestion(newMessage)) {
            return true;
        }
        return false;
    }


    private boolean isAboutPeople(String newMessage, String user1, String user2) {
        if (newMessage.contains(user1) || newMessage.contains(user2)) {
            return true;
        }
        return false;
    }


    private boolean isQuestion(String newMessage) {
        if (newMessage.contains("?")) {
            return true;
        }
        return false;
    }
}

// for counting words
//            if (wordsHashMap.containsKey(word)) {
//                wordsHashMap.put(word, wordsHashMap.get(word)+1);
//            } else {
//                wordsHashMap.put(word, 1);
//            }
