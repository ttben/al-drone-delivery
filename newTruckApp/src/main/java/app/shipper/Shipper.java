package app.shipper;

import app.Node;
import app.Output;
import app.action.Action;

import java.util.LinkedList;
import java.util.Queue;

public class Shipper {

	String name;
	Output output;

	private Node activeNode;
	private Queue<Node> actionsQueue;

	public Shipper(String name) {
		this.name = name;
		this.actionsQueue = new LinkedList<>();
	}

	/**
	 * 
	 * @param action
	 */
	public void execute(Action action) {
		// TODO - implement Shipper.execute
		throw new UnsupportedOperationException();
	}

	public void queueActionNode(Node actionNode) {
		boolean doStart = this.actionsQueue.size() == 0;
		this.actionsQueue.add(actionNode);
		if (doStart) {
			this.startNextAction();
		}
	}

	public void setActiveNode(Node activeNode) {
		this.activeNode = activeNode;
	}

	public void startNextAction() {
		this.activeNode = this.actionsQueue.poll();
		this.activeNode.start();
	}

	public void endAction() {
		System.out.println("Finished: "+this.activeNode);
		this.activeNode.end();

		if (this.actionsQueue.size() > 0) {
			this.startNextAction();
		}
	}

	public String getName() {
		return name;
	}
}