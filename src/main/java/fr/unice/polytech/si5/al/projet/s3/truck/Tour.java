package fr.unice.polytech.si5.al.projet.s3.truck;

import java.util.*;

/**
 * A tour is a set of assignment, and an assignment is a location (drop point location) and a list of tasks
 */
public class Tour extends Task {

	private Stack<Task> taskStack = new Stack<>();

	public Tour(String name, List<Task> tasks) {
		super(name, tasks);
		this.develop(this.taskStack);
	}

	public Stack<Task> getTaskStack() {
		return taskStack;
	}

	public int getNumberOfTaskOnStack() {
		return this.taskStack.size();
	}

	@Override
	public void execute() {
		super.execute();

		try {
			//	Retrieve the top of the stack
			Task taskToDevelop = this.taskStack.peek();

			//	Develop its internal tasks
			taskToDevelop.develop(this.taskStack);

			//	Retrieve the new top of the stack and execute it
			Task taskToDo = this.taskStack.peek();
			taskToDo.execute();

			if(taskToDo.isDone()) {
				this.taskStack.pop();
			}
		}
		catch (EmptyStackException e) {
			System.out.println("There is no more things to do on this tour !.. Is it ok ?...");
		}
	}
}