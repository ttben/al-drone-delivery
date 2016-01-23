package app;

import app.action.*;
import app.shipper.BasicShipper;
import app.shipper.CompositeShipper;
import app.shipper.Shipper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Benjamin on 16/01/2016.
 */
public class Main {
	Map<Shipper, Node> currentNodeForEachShipper = new HashMap<>();


	Main() {

		CompositeShipper truck = new CompositeShipper("Truck");
		//	Building shippers
		BasicShipper droneA = new Drone("DroneA");
		BasicShipper droneB = new Drone("DroneB");

		//	Builder different output
		Output commandLine = new CommandLine();
		Output droneAPI = new DroneAPI();

		Node.currentNodeForEachDrone = currentNodeForEachShipper;

		//	Building list of actions with target
/*		Action truckGo1 = new GoToDropPoint(truck);

		Action droneASend = new SendDrone(truck);
		Action droneAGoToShippingPosition = new GoToShippingPosition(droneA);
		Action droneAPick = new Pick(droneA);

		Action droneBPick = new Pick(droneB);
		Action droneBSend = new SendDrone(truck);
		Action droneBGoToShippingPosition = new GoToShippingPosition(droneB);
		Action droneBDrop = new Drop(droneB);
		*/

		//	Build nodes that schedules action's execution
		Node truckGo1Node = new Node(new GoToDropPoint(truck));
		Node truckGo2Node = new Node(new GoToDropPoint(truck));

		Node droneASendNode = new Node(new SendDrone(truck, droneA));
		Node droneAGoToShippingPositionNode = new Node(new GoToShippingPosition(droneA));
		Node droneAPickNode = new Node(new Pick(droneA));
		Node droneAGoToTruck = new Node(new GoToShippingPosition(droneA));
		Node droneACollect = new Node(new CollectDrone(truck, droneA));

		Node droneBPickNode = new Node(new Pick(droneB));
		Node droneBSendNode = new Node(new SendDrone(truck, droneB));
		Node droneBGoToShippingPositionNode = new Node(new GoToShippingPosition(droneB));
		Node droneBDropNode = new Node(new Drop(droneB));
		Node droneBGoToTruck = new Node(new GoToShippingPosition(droneB));
		Node droneBCollect = new Node(new CollectDrone(truck, droneB));

		//	Bind action to output by O/O
		droneBPickNode.addObserver(droneAPI);
		droneBGoToShippingPositionNode.addObserver(droneAPI);

		droneAPickNode.addObserver(commandLine);
		droneAGoToShippingPositionNode.addObserver(commandLine);

		// Build action dependency graph
		//
		droneASendNode.addDependency(truckGo1Node);
		droneBSendNode.addDependency(truckGo1Node);

		droneAGoToShippingPositionNode.addDependency(droneASendNode);
		droneBGoToShippingPositionNode.addDependency(droneBSendNode);

		droneAPickNode.addDependency(droneAGoToShippingPositionNode);
		droneBDropNode.addDependency(droneBGoToShippingPositionNode);

		droneAGoToTruck.addDependency(droneAPickNode);
		droneBGoToTruck.addDependency(droneBDropNode);

		droneACollect.addDependency(droneAGoToTruck);
		droneACollect.addDependency(truckGo2Node);

		droneBCollect.addDependency(droneBGoToTruck);
		droneBCollect.addDependency(truckGo2Node);

		/*Node lastActionForDroneA = new Node(currentNodeForEachShipper, droneAPick, null);
		Node firstActionForDroneA = new Node(currentNodeForEachShipper, droneAGoToShippingPosition, lastActionForDroneA);
		*/

		Node root = truckGo1Node;
		truck.setActiveNode(root);
		root.start();

		//	Fake drone msg reception
		truck.endAction();	// finish goto
		truck.endAction();	// finish first send
		truck.endAction();  //  finish 2nd send

		System.out.printf("\nTree : \t\t\t%s\n\n", currentNodeForEachShipper);
//		makeShipperFinishTask(droneB);
//		makeShipperFinishTask(droneA);
//		makeShipperFinishTask(droneB);
//		makeShipperFinishTask(droneA);

	}

	public static void main(String[] args) {
		new Main();
	}
}
