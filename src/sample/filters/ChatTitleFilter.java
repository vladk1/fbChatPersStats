package sample.filters;


public class ChatTitleFilter {

    private static String[] chatTitleArray = { };

    public static boolean isBinaryChat(String chatTitle, int messageSize) {
       String[] users = chatTitle.split(", ");
       for (String name : chatTitleArray) {
           if (chatTitle.contains(name)) {
               return false;
           }
       }

        if (messageSize < 50) {
            return false;
        }

        if (users.length == 2) {
            return true;
        } else {
            return false;
        }
    }

}
