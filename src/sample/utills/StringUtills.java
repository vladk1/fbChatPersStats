package sample.utills;

public class StringUtills {

    public static String beautifyTitle(String chatTitle) {
        chatTitle = chatTitle.replaceAll("[^a-zA-Z]+", "");
        return chatTitle.trim();
    }

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }



}
