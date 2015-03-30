package sample;


import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.InvalidFormatException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class OpenNLP {

    private FileInputStream is = null;
    private TokenizerME tokenizer = null;
    private TokenizerModel model = null;

    public OpenNLP() {
        try {
          is = new FileInputStream("en-token.bin");
          model = new TokenizerModel(is);
          tokenizer = new TokenizerME(model);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeTokinizerIs() {
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object[] tokenize(String sentence) {
       ArrayList<String> outputArray = new ArrayList<String>();
       String tokens[] = tokenizer.tokenize(sentence);
       for (String wordToken : tokens) {
         outputArray.add(wordToken.toLowerCase());
       }
       return outputArray.toArray();
    }


}
