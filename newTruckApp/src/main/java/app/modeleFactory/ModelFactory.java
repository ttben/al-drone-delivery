package app.modeleFactory;

import app.Node;
import app.action.Action;
import app.action.ActionFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;


/**
 * @author Etienne Strobbe
 */
public class ModelFactory {

    private static ActionFactory actionFactory = new ActionFactory();

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

    public static Node parseJson(String json) throws org.json.simple.parser.ParseException, NoActionDefinedException, NodeNotDefinedException {
        Map<String, Node> tempHashMapOfNodes = new HashMap<>();
        JSONParser parser =new JSONParser();
        String s = getFile(json);
        Object obj=parser.parse(s);


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
            //listNodes.add("node"+i);
            //System.out.println("params : " + nodei.get("params"));
            // building node
            String actionString = (String) nodei.get("action");
            if (actionString == null) {
                throw new NoActionDefinedException();
            }
            Action nodeAction = actionFactory.buildAction(actionString, null);
            Node nodeToCreate = new Node(nodeAction);
            tempHashMapOfNodes.put("node" + i, nodeToCreate);
        }

        /*
        Parsing graph
         */

        JSONObject graph = (JSONObject)root.get("graph");
        String idRoot = (String)graph.get("root");
        Node rootNode = tempHashMapOfNodes.get(idRoot);
        if (rootNode == null) {
            throw new NodeNotDefinedException();
        }

        JSONObject content = (JSONObject)graph.get("content");

        for (String nodeName : tempHashMapOfNodes.keySet()) {
            JSONObject nodei = (JSONObject) content.get(nodeName);
            if (nodei != null){
                Node currentNode = tempHashMapOfNodes.get(nodeName);
                if (currentNode == null) {
                    throw new NodeNotDefinedException();
                }
                System.out.println("DO something with that graph thing (" + nodeName + ")");
                JSONArray nextes = (JSONArray) nodei.get("next");
                System.out.println(nextes);
                Iterator<String> nextIterator = nextes.iterator();
                while (nextIterator.hasNext()) {
                    String nextNodeName = nextIterator.next();
                    Node nextNode = tempHashMapOfNodes.get(nextNodeName);
                    if (nextNode == null) {
                        throw new NodeNotDefinedException();
                    }
                    nextNode.addDependency(currentNode);
                }
            }

        }
        return rootNode;

    }

    public static void main(String[] args) {
        try {
            Node root = parseJson("template.json");
            System.out.println(1);
        } catch (org.json.simple.parser.ParseException | NoActionDefinedException | NodeNotDefinedException e) {
            e.printStackTrace();
        }
    }


}
