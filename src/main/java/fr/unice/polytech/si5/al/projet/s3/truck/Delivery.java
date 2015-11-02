package fr.unice.polytech.si5.al.projet.s3.truck;


import fr.unice.polytech.si5.al.projet.s3.drone.Drone;

import java.util.List;

public class Delivery extends Node{

	private Package aPackage;
	private String name;
	private Drone drone;
	private List<Drone> droneAlt;


	public Delivery(String name, Package aPackage){
		super(name);
		this.aPackage = aPackage;
	}

	public Delivery(String name, Package aPackage, Drone drone, List<Drone> droneAlt) {
		this(name,aPackage);
		this.aPackage = aPackage;
		this.drone = drone;
		this.droneAlt = droneAlt;

	}

	@Override
	public void doNext(){
		System.out.println("do next "+getName());
		done();
	}

	/*
	public void execute() {
		super.execute();

		System.out.println("Go to " + this.name);
		this.isDone(true);
	}*/
}