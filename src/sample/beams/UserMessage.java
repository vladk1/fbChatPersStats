package sample.beams;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class UserMessage {

    private final String message;
    private final String author;
    private final String reciever;
    private final String date;

    public UserMessage(String message, String author, String reciever, String date) {
        this.message = message;
        this.author = author;
        this.reciever = reciever;
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public String getAuthor() {
        return author;
    }

    public String getReplier() {
        return reciever;
    }

    public String getDate() {
        return date;
    }

    public Date getDate2() {
        Date d = null;
        List<SimpleDateFormat> formatter = new ArrayList<SimpleDateFormat>();

        formatter.add(new SimpleDateFormat("EEEE, MMM dd, yyyy"));
        formatter.add(new SimpleDateFormat("EEEE, dd MMM yyyy"));

        for(SimpleDateFormat format : formatter){
            try {
                d = format.parse(date);
                break;
            } catch (ParseException e) {
                //e.printStackTrace();
            }
        }
        return d;
    }


}
