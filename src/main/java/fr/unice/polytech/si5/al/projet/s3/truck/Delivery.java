package fr.unice.polytech.si5.al.projet.s3.truck;

import java.util.*;

public class Delivery {

	private Collection<Task> tasks;

	public Delivery() {
		this.tasks = new ArrayList<Task>();
	}

	public Delivery(List<Task> tasks) {
		this.tasks = tasks;
	}

	public void addTask(Task task) {
		this.tasks.add(task);
	}

}