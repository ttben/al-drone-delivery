package fr.unice.polytech.si5.al.projet.s3.truck;

import java.util.List;


public class DropPoint extends ForwardChain<DropPoint> {

	private GoToStep goToStep;
	private Deployment deployment;

	private DropPoint(GoToStep goToStep, Deployment deployment) {
		this.goToStep = goToStep;
		this.deployment = deployment;
	}

	public void start() {
		this.goToStep.start();
	}

	public List<Delivery> startDeploy() throws Exception {
		if (!this.goToStep.isDone()) {
			throw new Exception("Can not start to deploy ! You're have to be at the drop point location (" + goToStep.getName() + ") first !");
		}

		return deployment.getCurrentDeliveries();
	}

	public List<Delivery> start(DeliveryID deliveryID) {
		this.deployment.start(deliveryID);
		return this.deployment.getCurrentDeliveries();
	}

	public boolean isDone() {
		return this.goToStep.isDone() && this.deployment.isDone();
	}

	public void arrivedAtLocation() {
		this.goToStep.done();
	}
}
