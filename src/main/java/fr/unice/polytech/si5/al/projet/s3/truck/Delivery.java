package fr.unice.polytech.si5.al.projet.s3.truck;


import java.util.LinkedList;
import java.util.List;

public class Delivery extends Node{

	private LinkedList<Package> children;
	private String name;

	public Delivery(String name, List<Node> packages) {
		super(name,packages);
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