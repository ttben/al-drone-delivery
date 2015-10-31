package fr.unice.polytech.si5.al.projet.s3.truck;

import java.util.LinkedList;
import java.util.List;

public class DropPoint extends Node{

	private LinkedList<Delivery> children;
	private String name;
	private boolean done;

	public DropPoint(String name, List<Node> deliveries) {
		super(name,deliveries);
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
			System.out.println("do next "+ getName());
			done();
		}

	}



	/*
	public void execute() {
		super.execute();

		//	Retrieve the top of the stack
		Task taskToDevelop = this.taskList.peek();


		//	Develop its internal tasks
		taskToDevelop.develop(this.taskStack);

		//	Retrieve the new top of the stack and execute it
		Task taskToDo = this.taskStack.peek();
		taskToDo.execute();

		System.out.println("Go to droppoint " + this.name);
		isDone(true);
	}*/
}
