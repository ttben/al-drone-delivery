package app;

import app.action.*;
import app.demonstrator.DemonstratorSpy;
import app.shipper.*;

import java.awt.*;
import java.util.Arrays;
import java.util.Scanner;

public class DemoScenario {

	DemonstratorSpy output = new DemonstratorSpy();

	CompositeShipper truck;
	BasicShipper droneA;
	BasicShipper droneB;
	BasicShipper droneC;
	BasicShipper driver;

	DemoScenario() {

		output = new DemonstratorSpy();

		truck = new CompositeShipper("Truck");
		droneA = new Drone("DroneA");
		droneB = new Drone("DroneB");
		droneC = new Drone("DroneC");
		driver = new HumanShipper("Driver");

		// Positions
		Dimension dropPoint0 = new Dimension(150, 20);
		Dimension dropPoint1 = new Dimension(70, 20);
		Dimension dropPoint2 = new Dimension(45, 50);
		Dimension dropPoint3 = new Dimension(25, 60);
		Dimension dropPoint4 = new Dimension(30, 90);
		Dimension dropPoint5 = new Dimension(150, 90);
		Dimension house0 = new Dimension(80, 5);
		Dimension house1 = new Dimension(67, 10);
		Dimension house2 = new Dimension(50, 20);
		Dimension house3 = new Dimension(75, 30);
		Dimension house4 = new Dimension(35, 40);
		Dimension house5 = new Dimension(55, 65);
		Dimension house6 = new Dimension(35, 60);
		Dimension house7 = new Dimension(28, 52);
		Dimension house8 = new Dimension(34, 68);
		Dimension house9 = new Dimension(20, 90);

        output.init(Arrays.asList(house0, house1, house2, house3, house4, house5, house6, house7, house8, house9));

        // Truck Path
		Node truckGoDropPoint0 = new Node(new GoToDropPoint(truck, dropPoint0));
		Node truckGoDropPoint1 = new Node(new GoToDropPoint(truck, dropPoint1));
		Node truckGoDropPoint2 = new Node(new GoToDropPoint(truck, dropPoint2));
		Node truckGoDropPoint3 = new Node(new GoToDropPoint(truck, dropPoint3));
		Node truckGoDropPoint4 = new Node(new GoToDropPoint(truck, dropPoint4));
		Node truckGoDropPoint5 = new Node(new GoToDropPoint(truck, dropPoint5));

		// Truck actions
		Node truckSendDriverDropPoint1 = new Node(new SendDrone(truck, driver));
		Node truckSendDroneADropPoint1 = new Node(new SendDrone(truck, droneA));
		Node truckSendDroneBDropPoint1 = new Node(new SendDrone(truck, droneB));
		Node truckSendDroneCDropPoint1 = new Node(new SendDrone(truck, droneC));
		Node truckCollectDriverDropPoint1 = new Node(new Collect(truck, driver));
		Node truckCollectDroneADropPoint2 = new Node(new Collect(truck, droneA));
		Node truckCollectDroneBDropPoint2 = new Node(new Collect(truck, droneB));
		Node truckCollectDroneCDropPoint2 = new Node(new Collect(truck, droneC));
		Node truckSendDriverDropPoint2 = new Node(new SendDrone(truck, driver));
		Node truckSendDroneADropPoint2 = new Node(new SendDrone(truck, droneA));
		Node truckSendDroneBDropPoint2 = new Node(new SendDrone(truck, droneB));
		Node truckSendDroneCDropPoint2 = new Node(new SendDrone(truck, droneC));
		Node truckCollectDriverDropPoint2 = new Node(new Collect(truck, driver));
		Node truckCollectDroneADropPoint3 = new Node(new Collect(truck, droneA));
		Node truckCollectDroneBDropPoint3 = new Node(new Collect(truck, droneB));
		Node truckCollectDroneCDropPoint3 = new Node(new Collect(truck, droneC));
		Node truckSendDriverDropPoint4 = new Node(new SendDrone(truck, driver));
		Node truckCollectDriverDropPoint4 = new Node(new Collect(truck, driver));

		// DroneA actions
		Node droneAGoHouse0 = new Node(new GoToShippingPosition(droneA, house0));
		Node droneADropHouse0 = new Node(new Drop(droneA));
		Node droneAGoDropPoint2 = new Node(new GoToShippingPosition(droneA, dropPoint2));
		Node droneAGoHouse8 = new Node(new GoToShippingPosition(droneA, house8));
		Node droneADropHouse8 = new Node(new Drop(droneA));
		Node droneAGoDropPoint3 = new Node(new GoToShippingPosition(droneA, dropPoint3));

		// DroneB actions
		Node droneBGoHouse2 = new Node(new GoToShippingPosition(droneB, house2));
		Node droneBDropHouse2 = new Node(new Drop(droneB));
		Node droneBGoDropPoint2 = new Node(new GoToShippingPosition(droneB, dropPoint2));
		Node droneBGoHouse7 = new Node(new GoToShippingPosition(droneB, house7));
		Node droneBDropHouse7 = new Node(new Drop(droneB));
		Node droneBGoDropPoint3 = new Node(new GoToShippingPosition(droneB, dropPoint3));

		// DroneC actions
		Node droneCGoHouse1 = new Node(new GoToShippingPosition(droneC, house1));
		Node droneCDropHouse1 = new Node(new Drop(droneC));
		Node droneCGoDropPoint2 = new Node(new GoToShippingPosition(droneC, dropPoint2));
		Node droneCGoHouse5 = new Node(new GoToShippingPosition(droneC, house5));
		Node droneCPickHouse5 = new Node(new Pick(droneC));
		Node droneCGoHouse6 = new Node(new GoToShippingPosition(droneC, house6));
		Node droneCDropHouse6 = new Node(new Drop(droneC));
		Node droneCGoDropPoint3 = new Node(new GoToShippingPosition(droneC, dropPoint3));

		// Driver actions
		Node driverGoHouse3 = new Node(new GoToShippingPosition(driver, house3));
		Node driverDropHouse3 = new Node(new Drop(driver));
		Node driverGoDropPoint1 = new Node(new GoToShippingPosition(driver, dropPoint1));
		Node driverGoHouse4 = new Node(new GoToShippingPosition(driver, house4));
		Node driverDropHouse4 = new Node(new Drop(driver));
		Node driverGoDropPoint2 = new Node(new GoToShippingPosition(driver, dropPoint2));
		Node driverGoHouse9 = new Node(new GoToShippingPosition(driver, house9));
		Node driverDropHouse9 = new Node(new Drop(driver));
		Node driverGoDropPoint4 = new Node(new GoToShippingPosition(driver, dropPoint4));

		// Truck dependencies
		truckGoDropPoint1.addDependency(truckGoDropPoint0);
		truckSendDriverDropPoint1.addDependency(truckGoDropPoint1);
		truckSendDroneADropPoint1.addDependency(truckGoDropPoint1);
		truckSendDroneBDropPoint1.addDependency(truckGoDropPoint1);
		truckSendDroneCDropPoint1.addDependency(truckGoDropPoint1);
		truckGoDropPoint2.addDependency(truckCollectDriverDropPoint1);
		truckGoDropPoint2.addDependency(truckSendDroneADropPoint1);
		truckGoDropPoint2.addDependency(truckSendDroneBDropPoint1);
		truckGoDropPoint2.addDependency(truckSendDroneCDropPoint1);
		truckCollectDriverDropPoint1.addDependency(driverGoDropPoint1);
		truckCollectDroneADropPoint2.addDependency(truckGoDropPoint2);
        truckCollectDroneADropPoint2.addDependency(droneAGoDropPoint2);
		truckCollectDroneBDropPoint2.addDependency(truckGoDropPoint2);
        truckCollectDroneBDropPoint2.addDependency(droneBGoDropPoint2);
		truckCollectDroneCDropPoint2.addDependency(truckGoDropPoint2);
        truckCollectDroneCDropPoint2.addDependency(droneCGoDropPoint2);
		truckSendDriverDropPoint2.addDependency(truckGoDropPoint2);
		truckSendDroneADropPoint2.addDependency(truckCollectDroneADropPoint2);
		truckSendDroneBDropPoint2.addDependency(truckCollectDroneBDropPoint2);
		truckSendDroneCDropPoint2.addDependency(truckCollectDroneCDropPoint2);
		truckGoDropPoint3.addDependency(truckCollectDriverDropPoint2);
        truckGoDropPoint3.addDependency(truckSendDroneADropPoint2);
        truckGoDropPoint3.addDependency(truckSendDroneBDropPoint2);
        truckGoDropPoint3.addDependency(truckSendDroneCDropPoint2);
		truckCollectDriverDropPoint2.addDependency(driverGoDropPoint2);
		truckCollectDroneADropPoint3.addDependency(droneAGoDropPoint3);
		truckCollectDroneBDropPoint3.addDependency(droneBGoDropPoint3);
		truckCollectDroneCDropPoint3.addDependency(droneCGoDropPoint3);
		truckGoDropPoint4.addDependency(truckCollectDroneADropPoint3);
		truckGoDropPoint4.addDependency(truckCollectDroneBDropPoint3);
		truckGoDropPoint4.addDependency(truckCollectDroneCDropPoint3);
		truckSendDriverDropPoint4.addDependency(truckGoDropPoint4);
		truckCollectDriverDropPoint4.addDependency(driverGoDropPoint4);
		truckGoDropPoint5.addDependency(truckCollectDriverDropPoint4);

		// DroneA dependencies
		droneAGoHouse0.addDependency(truckSendDroneADropPoint1);
		droneADropHouse0.addDependency(droneAGoHouse0);
		droneAGoDropPoint2.addDependency(droneADropHouse0);
		droneAGoHouse8.addDependency(truckSendDroneADropPoint2);
		droneADropHouse8.addDependency(droneAGoHouse8);
		droneAGoDropPoint3.addDependency(droneADropHouse8);

		// DroneB dependencies
		droneBGoHouse2.addDependency(truckSendDroneBDropPoint1);
		droneBDropHouse2.addDependency(droneBGoHouse2);
		droneBGoDropPoint2.addDependency(droneBDropHouse2);
		droneBGoHouse7.addDependency(truckSendDroneBDropPoint2);
		droneBDropHouse7.addDependency(droneBGoHouse7);
		droneBGoDropPoint3.addDependency(droneBDropHouse7);

		// DroneC dependencies
		droneCGoHouse1.addDependency(truckSendDroneCDropPoint1);
		droneCDropHouse1.addDependency(droneCGoHouse1);
		droneCGoDropPoint2.addDependency(droneCDropHouse1);
		droneCGoHouse5.addDependency(truckSendDroneCDropPoint2);
		droneCPickHouse5.addDependency(droneCGoHouse5);
		droneCGoHouse6.addDependency(droneCPickHouse5);
		droneCDropHouse6.addDependency(droneCGoHouse6);
		droneCGoDropPoint3.addDependency(droneCDropHouse6);

		// Driver dependencies
		driverGoHouse3.addDependency(truckSendDriverDropPoint1);
		driverDropHouse3.addDependency(driverGoHouse3);
		driverGoDropPoint1.addDependency(driverDropHouse3);
		driverGoHouse4.addDependency(truckSendDriverDropPoint2);
		driverDropHouse4.addDependency(driverGoHouse4);
		driverGoDropPoint2.addDependency(driverDropHouse4);
		driverGoHouse9.addDependency(truckSendDriverDropPoint4);
		driverDropHouse9.addDependency(driverGoHouse9);
		driverGoDropPoint4.addDependency(driverDropHouse9);


		// Start simulation

		Node root = truckGoDropPoint0;
		root.queueAction();

		while(true) play();
	}

	public static void main(String[] args) {
		new DemoScenario();
	}

	private void play(){

		String res = new Scanner(System.in).next();
		Shipper s = null;
		if(res.contains("t")) s = truck;
		else if (res.contains("d")) s = driver;
		else if (res.contains("a")) s = droneA;
		else if (res.contains("b")) s = droneB;
		else if (res.contains("c")) s = droneC;

		if(s != null){
			try{
				s.endAction();
			} catch (Throwable t){
				System.err.println("Waiting a dependency");
			}
		}

	}

}
