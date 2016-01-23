package app.modeleFactory;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * @author Etienne Strobbe
 */
public class ModelFactory {

    private static String getFile(String fileName) {

        StringBuilder result = new StringBuilder("");

        //Get file from resources folder
        ClassLoader classLoader = ModelFactory.class.getClassLoader();
        File file = null;
        try {
            file = new File(classLoader.getResource(fileName).getFile());
        }
        catch (Exception e){
            e.printStackTrace();
        }

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                result.append(line).append("\n");
            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();

    }

    public static void parseJson(String json) throws org.json.simple.parser.ParseException {
        JSONParser parser =new JSONParser();
        String s = getFile(json);
        System.out.println(s);
        Object obj=parser.parse(s);
        List<String> listNodes = new ArrayList<>();

        JSONObject root = (JSONObject)obj;

        /*
        Parsing nodes
         */
        JSONObject nodes = (JSONObject)root.get("nodes");
        for(int i=1; ; i++){
            JSONObject nodei = (JSONObject) nodes.get("node" + i);
            if (nodei == null) {
                break;
            }
            listNodes.add("node"+i);
            System.out.println("Node :" + "node" + i);
            System.out.println("action : " + nodei.get("action"));
            System.out.println("params : " + nodei.get("params"));
            System.out.println("TODO : build node"+i);
        }

        /*
        Parsing graph
         */

        JSONObject graph = (JSONObject)root.get("graph");
        String idRoot = (String)graph.get("root");
        JSONObject content = (JSONObject)graph.get("content");
        for (int i=0 ; i<listNodes.size(); i++){
            JSONObject nodei = (JSONObject)content.get(listNodes.get(i));
            if (nodei != null){
                System.out.println("DO something with that graph thing ("+listNodes.get((i))+")");
            }

        }

    }

    public static void main(String[] args) {
        try {
            parseJson("template.json");
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
    }


}
