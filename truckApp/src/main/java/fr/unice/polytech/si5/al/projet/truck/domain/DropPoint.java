package fr.unice.polytech.si5.al.projet.truck.domain;

import fr.unice.polytech.si5.al.projet.truck.Controller;
import fr.unice.polytech.si5.al.projet.truck.domain.delivery.Delivery;
import fr.unice.polytech.si5.al.projet.truck.domain.delivery.DeliveryID;
import fr.unice.polytech.si5.al.projet.truck.domain.drone.Drone;
import fr.unice.polytech.si5.al.projet.truck.domain.drone.DroneID;

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

	public boolean isDone() {
		if(!Controller.DEMO) {
			System.out.println("Check if drop point is done ..\nGo to step is done ? " + goToStep.isDone() + "\nDeployment is done ? " + deployment.isDone());
		}
		return this.goToStep.isDone() && this.deployment.isDone();
	}

	public void arrivedAtLocation() throws Exception {
		this.goToStep.done();
		this.startDeploy();
	}

	public void declareDroneIsDead(DroneID droneID) {
		this.deployment.isDead(droneID);
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
