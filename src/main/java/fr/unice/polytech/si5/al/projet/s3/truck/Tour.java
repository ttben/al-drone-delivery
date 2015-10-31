package fr.unice.polytech.si5.al.projet.s3.truck;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

/**
 * A tour is a set of assignment, and an assignment is a location (drop point location) and a list of tasks
 */
public class Tour extends Node {

	public Tour(String name, List<Node> dropPoints) {
		super(name,dropPoints);
	}


	@Override
	public void doNext(){
		if(currentIterator.hasNext()){
			if(current == null){
				current = currentIterator.next();

			}
			else if(current.isDone()){
				current = currentIterator.next();
			}
			current.doNext();
		}
		else {
			// no item left
			System.out.println("Fin de la tournée "+ getName());
			done();
		}
	}


	/*
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



			Task taskToDo = this.taskStack.peek();
			taskToDo.execute();
			if(taskToDo.isDone()) {
				this.taskStack.pop();
			}
		}
		catch (EmptyStackException e) {
			System.out.println("There is no more things to do on this tour !.. Is it ok ?...");
		}
	}*/
}