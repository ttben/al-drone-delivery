package fr.unice.polytech.si5.al.projet.s3.truck;

import java.util.*;

/**
 * A tour is a set of assignment, and an assignment is a location (drop point location) and a list of tasks
 */
public class Tour {

	private Stack<Task> taskStack = new Stack<Task>();

	public Tour(List<DropPoint> dropPoints) {

		List<DropPoint> stackedList = dropPoints;
		Collections.reverse(stackedList);

		for(int i = 0 ; i < stackedList.size() ; i ++) {
			this.taskStack.add(stackedList.get(i));
		}
	}

	public List<Task> getTaskStack() {
		return taskStack;
	}

	public void execute() {
		Task taskToDevelop = this.taskStack.peek();

		List<Task> tasksToAddOnTheTopOfTheStack = taskToDevelop.develop();
		tasksToAddOnTheTopOfTheStack.forEach(t -> this.taskStack.add(t));

		Task taskToDo = this.taskStack.peek();
		taskToDo.execute();
	}
}