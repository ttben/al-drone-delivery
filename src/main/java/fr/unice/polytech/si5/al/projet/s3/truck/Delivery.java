package fr.unice.polytech.si5.al.projet.s3.truck;


import java.util.ArrayList;
import java.util.List;

public class Delivery implements Task{

	private boolean isDone = false;
	private List<Task> tasks = new ArrayList<Task>();

	public Delivery(List<Task> tasks) {
		this.tasks = tasks;
	}

	public void isDone(boolean isDone) {
		this.isDone = isDone;
	}

	public boolean isDone() {
		return false;
	}

	public void execute() {

	}

	@Override
	public List<Task> develop() {
		return this.tasks;
	}
}