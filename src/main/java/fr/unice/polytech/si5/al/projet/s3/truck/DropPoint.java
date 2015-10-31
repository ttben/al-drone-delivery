package fr.unice.polytech.si5.al.projet.s3.truck;

import java.util.ArrayList;
import java.util.List;

public class DropPoint implements Task {

	private boolean isDone = false;
	private List<Task> deliveries = new ArrayList<Task>();

	public DropPoint(List<Task> deliveries) {
		this.deliveries = deliveries;
	}
	public boolean isDone() {
		return isDone;
	}

	public void execute() {
		isDone = true;
	}

	@Override
	public List<Task> develop() {
		return this.deliveries;
	}
}
