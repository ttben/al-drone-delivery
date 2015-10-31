package fr.unice.polytech.si5.al.projet.s3.truck;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Delivery extends Task{
	public Delivery(String name, List<Task> tasks) {
		super(name, tasks);
	}

	public void execute() {
		super.execute();

		System.out.println("Go to " + this.name);
	}
}