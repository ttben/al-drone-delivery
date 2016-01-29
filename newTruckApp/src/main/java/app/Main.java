package app;

import app.action.*;
import app.demonstrator.DemonstratorSpy;
import app.shipper.BasicShipper;
import app.shipper.CompositeShipper;

import java.awt.*;
import java.io.Console;
import java.util.Scanner;

public class Main {

	Main() {

		DemonstratorSpy output = new DemonstratorSpy();

		CompositeShipper truck = new CompositeShipper("Truck", output);
		//	Building shippers
		BasicShipper droneA = new Drone("DroneA", output);
		BasicShipper droneB = new HumanShipper("DroneB", output);

		//	Build nodes that schedules action's execution
		Node truckGo1Node = new Node(new GoToDropPoint(truck, new Dimension(80, 20)));
		Node truckGo2Node = new Node(new GoToDropPoint(truck, new Dimension(40, 22)));
		Node truckGo3Node = new Node(new GoToDropPoint(truck ,new Dimension(20, 80)));

		Node droneASendNode = new Node(new SendDrone(truck, droneA));
		Node droneAGoToShippingPositionNode = new Node(new GoToShippingPosition(droneA, new Dimension(20, 30)));
		Node droneAPickNode = new Node(new Pick(droneA));
		Node droneAGoToTruck = new Node(new GoToShippingPosition(droneA, new Dimension(40, 22)));
		Node droneACollect = new Node(new CollectDrone(truck, droneA));

		Node droneBSendNode = new Node(new SendDrone(truck, droneB));
		Node droneBGoToShippingPositionNode = new Node(new GoToShippingPosition(droneB, new Dimension(60, 15)));
		Node droneBDropNode = new Node(new Drop(droneB));
		Node droneBGoToTruck = new Node(new GoToShippingPosition(droneB, new Dimension(40, 22)));
		Node droneBCollect = new Node(new CollectDrone(truck, droneB));

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
		waitForDemo();
		truck.endAction();	// finish goto
		waitForDemo();
		truck.endAction();	// finish first send
		waitForDemo();
		truck.endAction();  //  finish 2nd send

		waitForDemo();
		droneA.endAction(); // end of goto location
		waitForDemo();
		droneA.endAction();	// end of pick
		waitForDemo();
		droneA.endAction();	// end of goto meeting point

		waitForDemo();
		System.out.println("==== TRUCK REACHES MEETING POINT ====");
		truck.endAction(); // end of truck go to meeting point. Should queue CollectA in truck

		waitForDemo();
		droneB.endAction(); // end of goto location
		waitForDemo();
		droneB.endAction();    // end of drop
		waitForDemo();
		droneB.endAction();    // end of goto meeting point. Should queue CollectB in truck

		waitForDemo();

		truck.endAction(); // end of collect A
		waitForDemo();
		truck.endAction(); // end of collect B

		waitForDemo();
		truck.endAction(); // end of goto away
		waitForDemo();
	}

	public static void main(String[] args) {
		new Main();
	}

	private void waitForDemo(){
		System.out.println();

		// To allow control
		// new Scanner(System.in).nextLine();

		try {
			Thread.currentThread().sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
