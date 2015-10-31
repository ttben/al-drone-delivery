package fr.unice.polytech.si5.al.projet.s3.truck;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
		if(isDone) {
			return;
		}
		isDone = true;
	}

	@Override
	public void develop(Stack<Task> taskStack) {
		if(isDone) {
			return;
		}

	}
}
