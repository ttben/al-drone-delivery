package fr.unice.polytech.si5.al.projet.truck;

import com.sun.org.apache.bcel.internal.generic.GOTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DropPoint extends ForwardChain<DropPoint> {

	private GoToStep goToStep;
	private Deployment deployment;

	public DropPoint(GoToStep goToStep, Deployment deployment) {
		this.goToStep = goToStep;
		this.deployment = deployment;
	}

	public GoToStep start() {
		this.goToStep.start();
		return this.goToStep;
	}

	public List<Delivery> startDeploy() throws Exception {
		if (!this.goToStep.isDone()) {
			throw new Exception("Can not start to deploy ! You're have to be at the drop point location (" + goToStep.getName() + ") first !");
		}

		//	TODO make start method return deliveries instead of sequence calls
		deployment.start();

		return deployment.getCurrentDeliveries();
	}

	public List<Delivery> start(DeliveryID deliveryID) {
		this.deployment.start(deliveryID);
		return this.deployment.getCurrentDeliveries();
	}

	public boolean isDone() {
		return this.goToStep.isDone() && this.deployment.isDone();
	}

	public void arrivedAtLocation() throws Exception {
		this.goToStep.done();
		this.startDeploy();
	}

	public List<Delivery> getCurrentDeliveries() {
		return deployment.getCurrentDeliveries();
	}

	public Map<Drone, Delivery> getCurrentDeliveriesDescription() {
		return deployment.getCurrentDeliveriesDescription();
	}

	public void isGone(DroneID droneID) {
		this.deployment.isGone(droneID);
	}

	public void declareIsBack(DroneID droneID) {
		this.deployment.isBack(droneID);
	}

	public boolean checkAssociation(DroneID droneID, DeliveryID deliveryID) {
		return this.deployment.checkAssociation(droneID,deliveryID);
	}

	public List<Drone> getDroneGone() {
		return this.deployment.getDroneGone();
	}
}
