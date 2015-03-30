package sample.file;

import sample.utills.StringUtills;

public class FilePathDir {

//    private static final String FILE_PATH = "/Users/Fen/Documents/JavaThings/facebook-vladkolesnyk/html/messages.htm";
    private static final String FB_FILE_PATH = "src/files/fbmessages/messages.htm";


    // getting loaded message file path from dialog title
    public static String getChatTfidfFilePath(String chatTitle) {
        String fileSeparator = System.getProperty("file.separator");
        String goodTitle = StringUtills.beautifyTitle(chatTitle);
        String relativePath = "src"+fileSeparator+"files"+fileSeparator+"tfidf"+fileSeparator+"texts"+fileSeparator+goodTitle+".txt";
        return relativePath;
    }

    // getting loaded message file path from dialog title
    public static String getChatTfidfJsonFilePath(String chatTitle) {
        String fileSeparator = System.getProperty("file.separator");
        String goodTitle = StringUtills.beautifyTitle(chatTitle);
        String relativePath = "src" + fileSeparator + "files" + fileSeparator + "tfidf" + fileSeparator + "jsons"+fileSeparator+goodTitle+".json";
        return relativePath;
    }

    public static String getTopTfidfFilePath(String chatTitle) {
        String fileSeparator = System.getProperty("file.separator");
        String goodTitle = StringUtills.beautifyTitle(chatTitle);
        String relativePath = "src"+fileSeparator+"files"+fileSeparator+"tags"+fileSeparator+goodTitle+".txt";
        return relativePath;
    }

    public static String getTopTfidfJsonFilePath(String chatTitle) {
        String fileSeparator = System.getProperty("file.separator");
        String goodTitle = StringUtills.beautifyTitle(chatTitle);
        String relativePath = "src"+fileSeparator+"files"+fileSeparator+"tags"+fileSeparator+goodTitle+".json";
        return relativePath;
    }

    public static String getChatDateFrequencyFilePath(String chatTitle) {
        String fileSeparator = System.getProperty("file.separator");
        String goodTitle = StringUtills.beautifyTitle(chatTitle);
        String relativePath = "src"+fileSeparator+"files"+fileSeparator+"datef"+fileSeparator+"texts"+fileSeparator+goodTitle+".txt";
        return relativePath;
    }

    public static String getChatDateFrequencyJsonFilePath(String chatTitle) {
        String fileSeparator = System.getProperty("file.separator");
        String goodTitle = StringUtills.beautifyTitle(chatTitle);
        String relativePath = "src"+fileSeparator+"files"+fileSeparator+"datef"+fileSeparator+"jsons"+fileSeparator+goodTitle+".json";
        return relativePath;
    }

    public static String getSimilarityFilePath(String chatTitle) {
        String fileSeparator = System.getProperty("file.separator");
        String goodTitle = StringUtills.beautifyTitle(chatTitle);
        String relativePath = "src"+fileSeparator+"files"+fileSeparator+"similarity"+fileSeparator+"texts"+fileSeparator+goodTitle+".csv";
        return relativePath;
    }
    
    public static String getSimilarityJsonFilePath(String chatTitle) {
        String fileSeparator = System.getProperty("file.separator");
        String goodTitle = StringUtills.beautifyTitle(chatTitle);
        String relativePath = "src"+fileSeparator+"files"+fileSeparator+"similarity"+fileSeparator+"jsons"+fileSeparator+goodTitle+".json";
        return relativePath;
    }






    // getting FB htm file of all messages
    public static String getFBMessagesFilePath() {
        return FB_FILE_PATH;
    }
}
