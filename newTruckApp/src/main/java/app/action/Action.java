package app.action;

import app.shipper.Shipper;

import java.util.Arrays;
import java.util.Observable;

public abstract class Action extends Observable {



	protected void execute() {
		System.out.println("Executing action : " + this.getClass().getSimpleName());
	}

	public abstract Shipper getTarget();

	private Object[] params;

	public Action(Object[] params) {
		this.params = params;
	}

	public Object[] getParams() {
		return params;
	}

	public void start() {
		System.out.println("Starting action "+this.toString());
		this.setChanged();
		this.notifyObservers(ActionEvent.STARTED);
		this.execute();
	}

	public void end() {
		this.setChanged();
		this.notifyObservers(ActionEvent.ENDED);
	}

	public void queue() {
		this.getTarget().queueAction(this);
	}

	@Override
	public String toString() {
		return "Action["+ this.getClass().getSimpleName()
				+ ", Target: " + this.getTarget().getName()
				+ ", Params: " + Arrays.asList(this.getParams())
				+ "]";
	}
}