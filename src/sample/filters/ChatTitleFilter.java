package sample.filters;


public class ChatTitleFilter {

    private static final String RUSSIAN_1 = "Aizhan";
    private static final String RUSSIAN_2 = "Rusnak";
    private static final String RUSSIAN_3 = "Sidukov";
    private static final String RUSSIAN_4 = "Lebedynskiy";
    private static final String RUSSIAN_5 = "Karina";
    private static final String RUSSIAN_6 = "Maria";
    private static final String RUSSIAN_7 = "Mika";
    private static final String RUSSIAN_8 = "Nikita";
    private static final String RUSSIAN_9 = "Oleg";
    private static final String RUSSIAN_10 = "Tamara";
    private static final String RUSSIAN_11 = "Stepanov";
    private static final String RUSSIAN_12 = "Dubinska";
    private static final String RUSSIAN_13 = "Selezneva";
    private static final String RUSSIAN_14 = "Artem";
    private static final String RUSSIAN_15 = "Dana";
    private static final String RUSSIAN_16 = "Dasha";
    private static final String RUSSIAN_17 = "Diana";
    private static final String RUSSIAN_18 = "Dias";
    private static final String RUSSIAN_19 = "Irina";
    private static final String RUSSIAN_20 = "Maria";
    private static final String RUSSIAN_21 = "Olesya";
    private static final String RUSSIAN_22 = "Roman";
    private static final String RUSSIAN_23 = "Slava";
    private static final String RUSSIAN_24 = "Veronika";
    private static final String RUSSIAN_25 = "Murad";
    private static final String RUSSIAN_26 = "Olha";

    private static String[] chatTitleArray = {
            RUSSIAN_1,
            RUSSIAN_2,
            RUSSIAN_3,
            RUSSIAN_4,
            RUSSIAN_5,
            RUSSIAN_6,
            RUSSIAN_7,
            RUSSIAN_8,
            RUSSIAN_9,
            RUSSIAN_10,
            RUSSIAN_11,
            RUSSIAN_12,
            RUSSIAN_13,
            RUSSIAN_14,
            RUSSIAN_15,
            RUSSIAN_16,
            RUSSIAN_17,
            RUSSIAN_18,
            RUSSIAN_19,
            RUSSIAN_20,
            RUSSIAN_21,
            RUSSIAN_22,
            RUSSIAN_23,
            RUSSIAN_24,
            RUSSIAN_25,
            RUSSIAN_26, "Valeria", "David", "Olga", "Luba", "Nastya", "Svitlana", "Yuliya", "Евгений", "Anar", "Даша", "Filipp", "Ginak", "Liem"};


    public static boolean isBinaryChat(String chatTitle, int messageSize) {

        String[] users = chatTitle.split(", ");
//        System.out.println("isBinaryChat=" + chatTitle + " users.length="+users.length);

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
