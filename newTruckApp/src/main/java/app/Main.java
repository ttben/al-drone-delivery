package app;

import app.action.*;
import app.shipper.BasicShipper;
import app.shipper.Shipper;

import java.util.HashMap;
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

		//	Building list of actions with target
		Action droneBPick = new Pick(droneB);
		Action droneBGoToShippingPosition = new GoToShippingPosition(droneB);
		Action droneADrop = new Drop(droneA);
		Action droneAGoToShippingPosition = new GoToShippingPosition(droneA);


		//	Bind action to output by O/O
		droneBPick.addObserver(droneAPI);
		droneBGoToShippingPosition.addObserver(droneAPI);

		droneADrop.addObserver(commandLine);
		droneAGoToShippingPosition.addObserver(commandLine);


		//	Build nodes that schedules action's execution
		Node lastActionOfDroneB = new Node(currentNodeForEachDrone, droneBPick, null);
		Node firstActionOfDroneB = new Node(currentNodeForEachDrone,droneBGoToShippingPosition, lastActionOfDroneB);

		Node lastActionForDroneA = new Node(currentNodeForEachDrone, droneADrop, null);
		Node firstActionForDroneA = new Node(currentNodeForEachDrone, droneAGoToShippingPosition, lastActionForDroneA);

		System.out.printf("\nTree : \t\t\t%s\n\n", currentNodeForEachDrone);


		//	Start process
		firstActionForDroneA.execute();
		firstActionOfDroneB.execute();

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
