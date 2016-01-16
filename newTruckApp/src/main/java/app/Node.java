package app;

import app.action.Action;
import app.shipper.Shipper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Benjamin on 16/01/2016.
 */
public class Node {

	private Map<Shipper, Node> currentNodeForEachDrone;
	private Action action;

	private Node next;

	public Node(Map<Shipper, Node> currentNodeForEachDrone,Action actionToDo, Node next) {
		this.currentNodeForEachDrone = currentNodeForEachDrone;

		this.action = actionToDo;
		this.next = next;

		this.currentNodeForEachDrone.put(this.action.getTarget(), this);
	}

	public void execute() {
		//System.out.printf("Executing node %s\n", this);
		this.action.execute();
	}

	public void hasFinished() {

		this.currentNodeForEachDrone.put(this.action.getTarget(), this.next);
		System.out.printf("Node [%s] has finished. remains : \t%s\n", this.action.getTarget(), currentNodeForEachDrone);
		if(this.next!=null) {
			System.out.printf("Executing next node ...\n\n");
			this.next.execute();
		}
	}

	@Override
	public String toString() {
		return "[Node:" + this.action.getClass().getSimpleName() + " next: " + this.next + "]";
	}
}
