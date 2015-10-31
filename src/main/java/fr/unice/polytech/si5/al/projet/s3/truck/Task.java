package fr.unice.polytech.si5.al.projet.s3.truck;

import java.util.*;

public class Task {

	protected String name;
	protected TaskStatus taskStatus;
	protected List<Task> taskList = new ArrayList<>();

	public Task(String name, List<Task> internalTasks) {
		this.name = name;
		this.taskStatus = TaskStatus.PENDING;
		this.taskList = internalTasks;
		Collections.reverse(this.taskList);
	}

	String getName() {
		return this.name;
	}

	final void isDone(boolean isDone) {
		this.taskStatus = TaskStatus.DONE;
	}

	final boolean isDone() {
		return this.taskStatus.equals(TaskStatus.DONE);
	}

	void execute() {
		if(this.isDone()) {
			return;
		}

		//	Children must do stuff
	}

	void develop(Stack<Task> taskStack) {
		if (this.isDone()) {
			return;
		}

		//	Develop internal tasks
		this.taskList.forEach(t -> taskStack.push(t));

		//	If after developing internal tasks this is
		//	at the top of the stack, it means that the
		//	recursion must end
		if(!taskStack.isEmpty() && taskStack.peek() !=  this) {
			taskStack.peek().develop(taskStack);
		}
	}

	public String toString() {
		return "{"+this.getClass().getSimpleName() + "}:" + this.name;
	}
}