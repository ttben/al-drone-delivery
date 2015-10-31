package fr.unice.polytech.si5.al.projet.s3.truck;

import java.util.*;

/**
 * A tour is a set of assignment, and an assignment is a location (drop point location) and a list of tasks
 */
public class Tour implements Task {

	private Stack<Task> taskStack = new Stack<Task>();

	public Tour(List<Task> dropPoints) {

		List<Task> stackedList = dropPoints;
		Collections.reverse(stackedList);

		for(int i = 0 ; i < stackedList.size() ; i ++) {
			this.taskStack.add(stackedList.get(i));
		}
	}

	public List<Task> getTaskStack() {
		return taskStack;
	}

	@Override
	public boolean isDone() {
		return this.taskStack.empty();
	}

	@Override
	public void execute() {
		try {
			// System.out.println("STACK \t" + this.taskStack.toString());
			Task taskToDevelop = this.taskStack.peek();

			if(!taskToDevelop.isDone()) {
				// System.out.println("TASK " + taskToDevelop + " is not done");
				List<Task> tasksToAddOnTheTopOfTheStack = taskToDevelop.develop();
				tasksToAddOnTheTopOfTheStack.forEach(t -> this.taskStack.add(t));
			}

			// System.out.println("STACK \t" + this.taskStack.toString());

			Task taskToDo = this.taskStack.peek();
			taskToDo.execute();
		}
		catch (EmptyStackException e) {
			System.out.println("There is no more things to do on this tour !.. Is it ok ?");
		}
	}

	@Override
	public List<Task> develop() {
		return null;
	}
}