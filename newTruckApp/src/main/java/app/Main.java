package app;

import app.action.*;
import app.shipper.BasicShipper;
import app.shipper.CompositeShipper;
import app.shipper.Shipper;

import java.util.HashMap;
import java.util.Map;

public class Main {

	Main() {

		CompositeShipper truck = new CompositeShipper("Truck");
		//	Building shippers
		BasicShipper droneA = new Drone("DroneA");
		BasicShipper droneB = new Drone("DroneB");

		//	Builder different output
		Output commandLine = new CommandLine();
		Output droneAPI = new DroneAPI();

		//	Build nodes that schedules action's execution
		Node truckGo1Node = new Node(new GoToDropPoint(truck));
		Node truckGo2Node = new Node(new GoToDropPoint(truck));
		Node truckGo3Node = new Node(new GoToDropPoint(truck));

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
		truckGo2Node.addDependency(truckGo1Node);

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

		truckGo3Node.addDependency(droneACollect);
		truckGo3Node.addDependency(droneBCollect);

		/*Node lastActionForDroneA = new Node(currentNodeForEachShipper, droneAPick, null);
		Node firstActionForDroneA = new Node(currentNodeForEachShipper, droneAGoToShippingPosition, lastActionForDroneA);
		*/

		Node root = truckGo1Node;
		//truck.setCurrentAction(root);
		root.queueAction();

		//	Fake drone msg reception
		System.out.println();
		truck.endAction();	// finish goto
		System.out.println();
		truck.endAction();	// finish first send
		System.out.println();
		truck.endAction();  //  finish 2nd send

		System.out.println();
		droneA.endAction(); // end of goto location
		System.out.println();
		droneA.endAction();	// end of pick
		System.out.println();
		droneA.endAction();	// end of goto meeting point

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
	}

	public static void main(String[] args) {
		new Main();
	}
}
