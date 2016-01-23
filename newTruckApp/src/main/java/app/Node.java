package app;

import app.action.Action;
import app.shipper.Shipper;

import java.util.*;

public class Node extends Observable {

	public static Map<Shipper, Node> currentNodeForEachDrone;
	private Action action;

	private List<Node> dependencies;
	private List<Node> resolvedDependencies;
	private List<Node> nexts;

	public Node(Action action) {
		this.action = action;

		this.nexts = new LinkedList<>();

		this.dependencies = new LinkedList<>();
		this.resolvedDependencies = new LinkedList<>();
	}

	public void addDependency(Node n) {
		this.dependencies.add(n);
		n.nexts.add(this);
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
		this.queue();
		//this.start();
	}

	public void queue() {
		this.action.getTarget().queueActionNode(this);
	}

	public void start() {
		System.out.printf("Executing node %s\n", this);
		this.action.execute();
	}

	public void end() {
		for (Node n: this.nexts) {
			n.onDependencyResolve(this);
		}
	}

	@Override
	public String toString() {
		return "[Node:" + this.action.getClass().getSimpleName()
				+ ", Target: " + this.action.getTarget().getName()
				+ ", Params: " + Arrays.asList(this.action.getParams())
				+ "]";
	}
}
