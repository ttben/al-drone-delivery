package app;

import app.action.Action;
import app.action.ActionEvent;
import app.shipper.Shipper;

import java.util.*;

public class Node extends Observable implements Observer {

	private Action action;

	private List<Node> dependencies;
	private List<Node> resolvedDependencies;
	private List<Node> nexts;

	public Node(Action action) {
		this.action = action;
		this.action.addObserver(this);

		this.nexts = new LinkedList<>();

		this.dependencies = new LinkedList<>();
		this.resolvedDependencies = new LinkedList<>();
	}

	public Node(Action action, Output output){
		this(action);
		action.addObserver(output);
	}

	public void addDependency(Node parentNode) {
		this.dependencies.add(parentNode);
		parentNode.nexts.add(this);
	}

	public void onDependencyResolve(Node n) {
		if (! this.dependencies.contains(n)) {
			throw new RuntimeException("Graph build failure: non-dependency node was resolved.");
		}
		this.resolvedDependencies.add(n);

		if (this.resolvedDependencies.size() == this.dependencies.size()) {
			this.onAllDependenciesResolved();
		}
	}

	private void onAllDependenciesResolved() {
		this.queueAction();
		//this.start();
	}

	public void queueAction() {
		System.out.printf("Queuing action from node %s\n", this);
		this.action.queue();
	}

	public void end() {
		for (Node n: this.nexts) {
			n.onDependencyResolve(this);
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o == this.action && arg instanceof ActionEvent) {
			this.onActionUpdate((ActionEvent) arg);
		}
	}

	private void onActionUpdate(ActionEvent event) {
		if (event == ActionEvent.STARTED) {

		}
		else if (event == ActionEvent.ENDED) {
			this.end();
		}
	}

	@Override
	public String toString() {
		return "Node[" + this.action + "]";
	}
}
