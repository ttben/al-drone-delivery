package app.shipper;

import app.output.Output;
import app.action.Action;

import java.util.LinkedList;
import java.util.Queue;

public class Shipper {

	String name;
	Output output;

	private Action currentAction;

	public void setActionsQueue(Queue<Action> actionsQueue) {
		this.actionsQueue = actionsQueue;
	}

	private Queue<Action> actionsQueue;

	public Shipper(String name, Output output) {
		this.name = name;
		this.output = output;
		output.set(this);
		this.actionsQueue = new LinkedList<>();
	}
	public Action getCurrentAction() {
		return currentAction;
	}

	public Queue<Action> getActionsQueue() {
		return actionsQueue;
	}

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

	public void queueAction(Action action) {
		boolean doStart = this.currentAction == null && this.actionsQueue.size() == 0;
		if(output != null) action.addObserver(output);
		this.actionsQueue.add(action);
		if (doStart) {
			this.startNextAction();
		}
	}

	public void setCurrentAction(Action currentAction) {
		this.currentAction = currentAction;
	}

	public void startNextAction() {
		if (this.currentAction != null) {
			throw new RuntimeException("An action is still active for "+this);
		}

		this.currentAction = this.actionsQueue.poll();
		if (this.currentAction != null) {
			this.currentAction.start();
		}
	}

	public void endAction() {
		System.out.println("Ending action of "+this.getName()+": "+this.currentAction);
		if (this.currentAction == null) {
			throw new RuntimeException("The Shipper does not have any current action to end. (Shipper: "+this+")");
		}
		Action theCurrentAction = this.currentAction;
		this.currentAction = null;
		theCurrentAction.end();

		// An action might be queued by the call to theCurrentAction.end()
		if (this.currentAction == null) {
			this.startNextAction();
		}
	}

	public String getName() {
		return name;
	}
}