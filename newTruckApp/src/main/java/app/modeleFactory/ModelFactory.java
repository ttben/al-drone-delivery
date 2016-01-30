package app.modeleFactory;

import app.*;
import app.action.Action;
import app.action.ActionFactory;
import app.demonstrator.DemonstratorSpy;
import app.modeleFactory.exceptions.*;
import app.output.CommandLine;
import app.output.DroneAPI;
import app.output.Output;
import app.shipper.*;
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
	private static Map<String, Shipper> shipperMap = new HashMap<>();

	private static String getFile(String fileName) {

		StringBuilder result = new StringBuilder("");

		//Get file from resources folder
		ClassLoader classLoader = ModelFactory.class.getClassLoader();
		File file = null;
		try {
			file = new File(classLoader.getResource(fileName).getFile());
		} catch (Exception e) {
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

	static Map<String, Shipper> buildShippers(String filePathToJsonDescription) throws Exception {
		DemonstratorSpy output = new DemonstratorSpy();
		JSONParser parser = new JSONParser();
		String s = getFile(filePathToJsonDescription);
		Object obj = parser.parse(s);
		Map<String, Shipper> result = new HashMap<>();

		JSONObject root = (JSONObject) obj;

		JSONArray shippers = (JSONArray) root.get("shippers");

		if (shippers == null) {
			throw new ShippersRootElementNotFoundException();
		}

		Iterator shipperIterator = shippers.iterator();

		while (shipperIterator.hasNext()) {
			JSONObject shipperJSON = (JSONObject) shipperIterator.next();
			String name = (String) shipperJSON.get("name");
			if (name == null) {
				throw new NoNameDefinedException();
			}
			String type = (String) shipperJSON.get("type");
			if (type == null) {
				throw new NoTypeDefinedException();
			}

			switch (type) {
				case "composite":
					result.put(name, new CompositeShipper(name, output));
					break;
				case "drone":
					result.put(name, new Drone(name, output));
					break;
				case "human":
					result.put(name, new HumanShipper(name, output));
					break;
				default:
					throw new ShipperTypeNotDefinedException();
			}
		}
		return result;
	}

	public static Node parseJson(String filePathToJsonDescription) throws Exception {
		Map<String, Node> tempHashMapOfNodes;
		JSONParser parser = new JSONParser();
		String s = getFile(filePathToJsonDescription);
		Object obj = parser.parse(s);


		JSONObject root = (JSONObject) obj;
		/*
        Parsing nodes
         */
		JSONObject nodes = (JSONObject) root.get("nodes");
		tempHashMapOfNodes = buildNodes(nodes);

        /*
        Parsing graph
         */

		JSONObject graph = (JSONObject) root.get("graph");
		Node rootNode = buildDependencies(graph, tempHashMapOfNodes);
		return rootNode;

	}


	/**
	 * Build all nodes object necessary (including action and shipper associated)
	 *
	 * @param nodes the JSONObject describing all the nodes
	 * @return
	 */
	static Map<String, Node> buildNodes(JSONObject nodes) throws Exception {
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
			Map<String, Object> paramMap = new HashMap<>();
			String shipperName = (String) paramsString.get(0);
			paramMap.put("shipper", shipperMap.get(shipperName));
			if (paramsString.size() > 1) {
				String secondParamName = (String) paramsString.get(1);
				Object secondParam = shipperMap.get(secondParamName);
				paramMap.put("second", secondParam);
			}

			Action nodeAction = actionFactory.buildAction(actionString, paramMap);
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

	static Node buildAll(String pathToShipperDescription, String pathToGraphDescription) throws Exception {
		shipperMap = buildShippers(pathToShipperDescription);

		//	Build nodes that schedules action's execution
		Node root = parseJson(pathToGraphDescription);
 		return root;
	}

	public static void main(String[] args) {
		try {

			//	Building shippers
			shipperMap = buildShippers("shippers.json");
			CompositeShipper truck = (CompositeShipper) shipperMap.get("Truck");
			BasicShipper droneA = (BasicShipper) shipperMap.get("DroneA");
			BasicShipper droneB = (BasicShipper) shipperMap.get("DroneB");

			//	Builder different output
			Output commandLine = new CommandLine();
			Output droneAPI = new DroneAPI();


			//	Build nodes that schedules action's execution
			Node root = parseJson("template_main.json");

			//truck.setCurrentAction(root);
			root.queueAction();

			//	Fake drone msg reception
			System.out.println();
			truck.endAction();    // finish goto
			System.out.println();
			truck.endAction();    // finish first send
			System.out.println();
			truck.endAction();  //  finish 2nd send

            System.out.println();
            droneA.endAction(); // end of goto location
            System.out.println();
            droneA.endAction();    // end of pick
            System.out.println();
            droneA.endAction();    // end of goto meeting point

            System.out.println();
            System.out.println("==== TRUCK REACHES MEETING POINT ====");
            truck.endAction(); // end of truck go to meeting point. Should queue CollectA in truck

            System.out.println();
            droneB.endAction(); // end of goto location
            System.out.println();
            droneB.endAction();    // end of drop
            System.out.println();
            droneB.endAction();    // end of goto meeting point. Should queue CollectB in truck

            truck.endAction(); // end of collect A
            truck.endAction(); // end of collect B

            System.out.println();
            truck.endAction(); // end of goto away

		} catch (org.json.simple.parser.ParseException | NoActionDefinedException | NodeNotDefinedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
