package sample;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import sample.file.MessagesFile;
import sample.utills.StringSimilarity;

import java.io.*;
import java.util.ArrayList;


public class SimilarityGenerator {

    public void generateFiles(String files) {
    	System.out.println("Generate similarity files \n");
        StringSimilarity stringSimilarity = new StringSimilarity();
        MessagesFile messagesFile = new MessagesFile();

        ArrayList<ArrayList<String>> lists = new ArrayList<ArrayList<String>>();
        ArrayList<String> filenames = new ArrayList<String>();


        File folder = new File(files);
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                ArrayList<String> list = new ArrayList<String>();
                filenames.add(file.getName());
                try {
                    // read the json file
                    FileReader reader = new FileReader(file);

                    JSONParser jsonParser = new JSONParser();
                    JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
                    JSONArray words = (JSONArray) jsonObject.get("words");

                    // take the elements of the json array
                    for(int i=0; i < words.size(); i++){
                        JSONObject jobj = (JSONObject) words.get(i);
                        list.add((String)jobj.get("word"));
                    }
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (org.json.simple.parser.ParseException ex) {
                    ex.printStackTrace();
                } catch (NullPointerException ex) {
                    ex.printStackTrace();
                }
                lists.add(list);
            }
        }
        ArrayList<String> reversedStringList = new ArrayList<String>();
        // Get the actual similarity
        for (int i = 0; i < lists.size(); i++)  {
            String strline = "";
            for (int j = 0; j < lists.size(); j++)  {
                String s1 = "";
                String s2 = "";

                int maxstr = 100;
                int cnt = 0;
                for (String s : lists.get(i)) {
                    s1 = s1 + s + " ";
                    if(cnt++ > maxstr)
                        break;
                }

                cnt = 0;
                for (String s : lists.get(j)) {
                    s2 = s2 + s + " ";
                    if(cnt++ > maxstr)
                        break;
                }

                //double sim = stringSimilarity.similarity(s1, s2);
                double sim = stringSimilarity.similarity_common_words(s1, s2);
                if(i == j) {
                    sim = 0.0;
                }
                //System.out.println("Sim: " + sim);
                strline = strline + sim + ", ";
            }
            reversedStringList.add(strline);
        }

        messagesFile.createSimilarityFile("similatity", reversedStringList);
       
        messagesFile.createSimilarityFile("simnames", filenames);
        
        messagesFile.createSimilarityJsonFile("similatity", getJson(reversedStringList, filenames));
    }
    
    
    private JSONObject getJson(ArrayList<String> similarityRatio, ArrayList<String> filenames) {
		
    	JSONObject mainJson = new JSONObject();
    	JSONArray mainJsonArray = new JSONArray();
    	int nameCount = 0;
    	
    	for(String value : similarityRatio) {
//    		System.out.println(value);
    		String[] ratioArray = value.split(", ");
    		JSONObject eachJson = new JSONObject();
    		JSONArray ratioJsonArray = new JSONArray();
    		
    		String name = filenames.get(nameCount);
    		eachJson.put("name", name);
    		
    		for (String eachRatio : ratioArray) {
    			ratioJsonArray.add(eachRatio);
    		}
    		eachJson.put("ratioArray", ratioJsonArray);
    		
    		mainJsonArray.add(eachJson);
    		nameCount++;
    	}
    	mainJson.put("nodes", mainJsonArray);
    	
    	System.out.println("mainJson="+mainJson);
    		
    	return mainJson;
    	
    }


}
