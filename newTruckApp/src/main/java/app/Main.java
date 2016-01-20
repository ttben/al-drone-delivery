package app;

import app.action.*;
import app.demonstrator.DemonstratorSpy;
import app.shipper.BasicShipper;
import app.shipper.Shipper;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Benjamin on 16/01/2016.
 */
public class Main {
	Map<Shipper, Node> currentNodeForEachDrone = new HashMap<>();

	Main() {

		//	Building shippers
		BasicShipper droneA = new Drone("DroneA");
		BasicShipper droneB = new Drone("DroneB");

		//	Builder different output
		Output commandLine = new CommandLine();
		Output droneAPI = new DroneAPI();
		Output demonstratorOutput = new DemonstratorSpy();

		//	Building list of actions with target
		Action droneBPick = new Pick(droneB);
		Action droneBGoToShippingPosition = new GoToShippingPosition(droneB, new Dimension(20, 20));
		Action droneADrop = new Drop(droneA);
		Action droneAGoToShippingPosition = new GoToShippingPosition(droneA, new Dimension(50, 40));


		//	Bind action to output by O/O
		droneBPick.addObserver(droneAPI);
		droneBGoToShippingPosition.addObserver(droneAPI);

		droneADrop.addObserver(commandLine);
		droneAGoToShippingPosition.addObserver(commandLine);

		droneBPick.addObserver(demonstratorOutput);
		droneBGoToShippingPosition.addObserver(demonstratorOutput);
		droneADrop.addObserver(demonstratorOutput);
		droneAGoToShippingPosition.addObserver(demonstratorOutput);

		//	Build nodes that schedules action's execution
		Node lastActionOfDroneB = new Node(currentNodeForEachDrone, droneBPick, null);
		Node firstActionOfDroneB = new Node(currentNodeForEachDrone,droneBGoToShippingPosition, lastActionOfDroneB);

		Node lastActionForDroneA = new Node(currentNodeForEachDrone, droneADrop, null);
		Node firstActionForDroneA = new Node(currentNodeForEachDrone, droneAGoToShippingPosition, lastActionForDroneA);

		System.out.printf("\nTree : \t\t\t%s\n\n", currentNodeForEachDrone);

		List<Node> nodes = new ArrayList<>();
		nodes.add(firstActionForDroneA);
		nodes.add(firstActionOfDroneB);

		ForkNode forkNode = new ForkNode(nodes);
		forkNode.execute();

		/*
		//	Start process
		firstActionForDroneA.execute();
		firstActionOfDroneB.execute();
*/
		//	Fake drone msg reception
		droneHasFinishedTask(droneB);
		droneHasFinishedTask(droneA);
		droneHasFinishedTask(droneB);
		droneHasFinishedTask(droneA);

	}

	private void droneHasFinishedTask(Shipper a) {
		currentNodeForEachDrone.get(a).hasFinished();
	}

	public static void main(String[] args) {
		new Main();
	}
}
