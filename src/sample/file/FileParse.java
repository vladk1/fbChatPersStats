package sample.file;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static sample.file.FilePathDir.getFBMessagesFilePath;

public class FileParse {
    private static final String Log = "FileParse/ ";

    private static final String BODY_TAG = "body";
    private static final String DIV_TAG = "div";
    private static final String CONTENTS_TAG = "contents";
    private static final String HTML_TAG = "html";



    public List<Node> getContentThread() {
        System.out.println(Log+"load file");
        Document parsedHtml = loadMainFBFile();

        if (parsedHtml != null) {
            List<Node> threads = extractContentThreadsFromBody(parsedHtml.childNodes());
            System.out.println(Log+"messages.htm content extracted\n");
            return threads;
        } else {
            System.out.println(Log+"messages.htm content NOT extracted\n");
            return null;
        }
    }


    // returns List with [user name tag; list of all chat]
    private List<Node> extractContentThreadsFromBody(List<Node> childNodes) {

        for (Node childNode : childNodes) {
            Element childElement = (Element) childNode;
            String currentNodeTag = childElement.tag().getName();

            if (currentNodeTag.equals(HTML_TAG)) {
                return extractContentThreadsFromBody(childElement.childNodes());

            } else if (currentNodeTag.equals(BODY_TAG)) {
                return extractContentThreadsFromBody(childElement.childNodes());

            } else if (currentNodeTag.equals(DIV_TAG)) {
                String elementAttr = childElement.attributes().get("class");

                if (elementAttr.equals(CONTENTS_TAG)) {
                    return childElement.childNodes();
                }

            }

        }
        return null;
    }

    private Document loadMainFBFile() {
        File input = new File(getFBMessagesFilePath());
        Document doc = null;
        try {
            doc = Jsoup.parse(input, "UTF-8");
            System.out.println(Log+"messages.htm loaded");
        } catch (IOException e) {
            System.out.println(Log+"messages.htm is not loaded");
            e.printStackTrace();
        }
        return doc;
    }
}
