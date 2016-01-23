package app.action;

import app.shipper.Shipper;

import java.util.Observable;

public abstract class Action extends Observable {

	public abstract void execute();

	public abstract Shipper getTarget();

	private Object[] params;

	public Action(Object[] params) {
		this.params = params;
	}

	@Override
	public String toString() {
		return "Action["+this.getClass().getSimpleName()+"]";
	}

	public Object[] getParams() {
		return params;
	}
}