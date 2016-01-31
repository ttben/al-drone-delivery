package app.modelFactory;

import app.Node;
import app.action.Action;
import app.modelFactory.exceptions.*;
import app.shipper.*;
import com.sun.javafx.sg.prism.NGShape;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**
 * @author Etienne Strobbe
 */
public class ModelFactory {

	private ActionFactory actionFactory;

	public ModelFactory() {
		actionFactory = new ActionFactory();
	}

	public ModelFactory(ActionFactory actionFactory) {
		this.actionFactory = actionFactory;
	}

	public Node parseJson(String pathToShipperJsonFile, String pathToNodeJsonFile) throws Exception {
		Map<String, Node> tempHashMapOfNodes;
		JSONParser parser = new JSONParser();

		Map<String, Shipper> shipperMap = buildShippers(pathToShipperJsonFile);

		String nodeJsonDescription = getFile(pathToNodeJsonFile);
		Object objNodeJson = parser.parse(nodeJsonDescription);

		JSONObject root = (JSONObject) objNodeJson;
		/*
		Parsing nodes
         */
		JSONObject nodes = (JSONObject) root.get("nodes");
		tempHashMapOfNodes = buildNodes(shipperMap, nodes);

        /*
        Parsing graph
         */
		JSONObject graph = (JSONObject) root.get("graph");
		Node rootNode = buildDependencies(graph, tempHashMapOfNodes);
		return rootNode;
	}

	public String getFile(String fileName) {

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

	public JSONObject getJSONFromFile(String filePathToJsonDescription) throws ParseException {
		JSONParser parser = new JSONParser();
		String s = getFile(filePathToJsonDescription);
		Object obj = parser.parse(s);
		JSONObject root = (JSONObject) obj;
		return root;
	}

	public Map<String, Shipper> buildShippers(String filePathToJsonDescription) throws Exception {
		JSONObject root = getJSONFromFile(filePathToJsonDescription);

		Map<String, Shipper> result = new HashMap<>();
		JSONArray shippers = (JSONArray) root.get("shippers");

		if (shippers == null) {
			throw new ShippersRootElementNotFoundException();
		}

		for (Object currentShipperObject : shippers) {
			JSONObject currentShipperJSONObject = (JSONObject) currentShipperObject;
			Shipper shipper = buildAShipper(currentShipperJSONObject);
			result.put(shipper.getName(), shipper);
		}

		return result;
	}

	public Shipper buildAShipper(JSONObject shipperJSON) throws ShipperTypeNotDefinedException, NoTypeDefinedException, NoNameDefinedException {
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
				return new CompositeShipper(name);
			case "drone":
				return new Drone(name);
			case "human":
				return new HumanShipper(name);
			default:
				throw new ShipperTypeNotDefinedException();
		}
	}

	/**
	 * Build all nodes object necessary (including action and shipper associated)
	 *
	 * @param nodes the JSONObject describing all the nodes
	 * @return
	 */
	public Map<String, Node> buildNodes(Map<String, Shipper> shipperMap, JSONObject nodes) throws Exception {
		Map<String, Node> result = new HashMap<>();

		for (int i = 1; ; i++) {
			JSONObject currentNode = (JSONObject) nodes.get("node" + i);
			if (currentNode == null) {
				break;
			}
			// building node
			String actionString = (String) currentNode.get("action");
			JSONObject paramJsonObject = (JSONObject) currentNode.get("params");
			if (actionString == null) {
				throw new NoActionDefinedException();
			}
			if (paramJsonObject == null) {
				throw new NoParamsDefinedException();
			}

			Action nodeAction = actionFactory.buildAction(shipperMap, actionString, paramJsonObject);

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
	public Node buildDependencies(JSONObject graph, Map<String, Node> nodes) throws Exception {
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

	public static void main(String[] args) {
		try {
			ModelFactory modelFactory = new ModelFactory();

			Map<String, Node> tempHashMapOfNodes;
			JSONParser parser = new JSONParser();

			String nodeJsonDescription = modelFactory.getFile("template_main.json");
			Object objNodeJson = parser.parse(nodeJsonDescription);
			JSONObject rootJsonObject = (JSONObject) objNodeJson;
			JSONObject nodes = (JSONObject) rootJsonObject.get("nodes");

			Map<String, Shipper> shipperMap = modelFactory.buildShippers("shippers.json");
			tempHashMapOfNodes = modelFactory.buildNodes(shipperMap, nodes);
			/*
			Parsing nodes
			 */


			//	Build nodes that schedules action's execution
			/*
			Parsing graph
			 */
			JSONObject graph = (JSONObject) rootJsonObject.get("graph");
			Node root = modelFactory.buildDependencies(graph, tempHashMapOfNodes);


			//	Building shippers
			CompositeShipper truck = (CompositeShipper) shipperMap.get("Truck");
			BasicShipper droneA = (BasicShipper) shipperMap.get("DroneA");
			BasicShipper droneB = (BasicShipper) shipperMap.get("DroneB");

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
