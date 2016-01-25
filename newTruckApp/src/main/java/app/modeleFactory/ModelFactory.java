package app.modeleFactory;

import app.Node;
import app.action.Action;
import app.action.ActionFactory;
import app.shipper.Shipper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**
 * @author Etienne Strobbe
 */
public class ModelFactory {

    private static ActionFactory actionFactory = new ActionFactory();
    private static Map<String, Shipper> shipperMap = new HashMap<>();

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

    public static Node parseJson(String json) throws Exception {
        Map<String, Node> tempHashMapOfNodes = new HashMap<>();
        Map<String, Shipper> shipperList = new HashMap<>();
        JSONParser parser =new JSONParser();
        String s = getFile(json);
        Object obj=parser.parse(s);


        JSONObject root = (JSONObject)obj;
        /*
        Parsing nodes
         */
        JSONObject nodes = (JSONObject)root.get("nodes");
        tempHashMapOfNodes = buildNodes(nodes);

        /*
        Parsing graph
         */

        JSONObject graph = (JSONObject)root.get("graph");
        Node rootNode = buildDependencies(graph, tempHashMapOfNodes);
        return rootNode;

    }


    /**
     * Build all nodes object necessary (including action and shipper associated)
     *
     * @param nodes the JSONObject describing all the nodes
     * @return
     */
    private static Map<String, Node> buildNodes(JSONObject nodes) throws Exception {
        Map<String, Node> result = new HashMap<>();
        for (int i = 1; ; i++) {
            JSONObject nodei = (JSONObject) nodes.get("node" + i);
            if (nodei == null) {
                break;
            }
            // building node
            String actionString = (String) nodei.get("action");
            JSONArray paramsString = (JSONArray) nodei.get("params");
            if (actionString == null) {
                throw new NoActionDefinedException();
            }
            if (paramsString == null) {
                throw new NoParamsDefinedException();
            }
            /*
            Creating parameter to create the action (the shipper)
             */
            Map<String, Object> shipperMap = new HashMap<>();
            String shipperName = (String) paramsString.get(0);
            Shipper chipeur = arreteDeChiper(shipperName);
            shipperMap.put("shipper", chipeur);

            Action nodeAction = actionFactory.buildAction(actionString, shipperMap);
            Node nodeToCreate = new Node(nodeAction);
            result.put("node" + i, nodeToCreate);
        }
        return result;
    }


    /**
     * Build all dependencies between all nodes
     *
     * @param graph The JSONObject describing dependencies
     * @param nodes The HashMap containing raw nodes
     * @return the root of the graph
     * @throws Exception
     */
    private static Node buildDependencies(JSONObject graph, Map<String, Node> nodes) throws Exception {
        String idRoot = (String) graph.get("root");
        Node rootNode = nodes.get(idRoot);
        if (rootNode == null) {
            throw new NodeNotDefinedException();
        }

        JSONObject content = (JSONObject) graph.get("content");

        for (String nodeName : nodes.keySet()) {
            JSONObject nodei = (JSONObject) content.get(nodeName);
            if (nodei != null) {
                Node currentNode = nodes.get(nodeName);
                if (currentNode == null) {
                    throw new NodeNotDefinedException();
                }
                JSONArray dependencies = (JSONArray) nodei.get("dependencies");
                for (String previousNodeName : (Iterable<String>) dependencies) {
                    Node previous = nodes.get(previousNodeName);
                    if (previous == null) {
                        throw new NodeNotDefinedException();
                    }
                    currentNode.addDependency(previous);
                }
            }

        }
        return rootNode;
    }


    /**
     * Try to find an exiting shipper in the static map
     * if not, create one
     * Dora L'exploratrice, all right reserved
     *
     * @param shipperName
     * @return the shipper needed of course
     */
    private static Shipper arreteDeChiper(String shipperName) {
        //TODO define a JSON file containing needed information to construct shippers
        if (!shipperMap.containsKey(shipperName)) {
            shipperMap.put(shipperName, new Shipper(shipperName));
        }
        return shipperMap.get(shipperName);
    }

    public static void main(String[] args) {
        try {
            Node root = parseJson("template_main.json");
            System.out.println(1);
        } catch (org.json.simple.parser.ParseException | NoActionDefinedException | NodeNotDefinedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
