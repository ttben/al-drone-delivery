package fr.unice.polytech.si5.al.projet.s3.truck;

import fr.unice.polytech.si5.al.projet.s3.truck.step.Delivery;
import fr.unice.polytech.si5.al.projet.s3.truck.step.Deployment;
import fr.unice.polytech.si5.al.projet.s3.truck.step.GoToStep;

import java.util.List;

/**
 * Created by Benjamin on 04/11/2015.
 */
public class Tour {
	private GoToStep currentGoToStep;
	private Deployment currentDeployment;

	public Tour(List<GoToStep> dropPointLocations, List<Deployment> deployments) {
		if(dropPointLocations.size() != deployments.size()) {
			throw new IllegalArgumentException("Number of dropPoint location is different from dropPoint description");
		}

		dropPointLocations.forEach(goToStep -> chain(goToStep,dropPointLocations.get(0)));
		deployments.forEach(dropPoint -> chain(dropPoint, deployments.get(0)));

		currentGoToStep = dropPointLocations.get(0);
		currentDeployment = deployments.get(0);
	}

	public void arrived() {
		currentGoToStep.done();
	}

	public void deploy() {
		if(!currentGoToStep.isDone()) {
			throw new Error("Can not deploy before being at specified location : " + currentGoToStep.getName());
		}
	}

	public List<Delivery> getCurrentDeliveries() {
		return this.currentDeployment.getCurrentDeliveries();
	}

	public void start(DeliveryID deliveryID) {
		this.currentDeployment.start(deliveryID);
	}

	public boolean checkDelivery(DroneID droneID, String boxID) {
		return this.currentDeployment.checkDelivery(droneID,boxID);
	}

	public void isGone(DroneID droneID) {
		this.currentDeployment.isGone(droneID);
	}

	public void isBack(DroneID droneID) {
		this.currentDeployment.isBack(droneID);
	}

	private void chain(GoToStep goToStep, GoToStep headOfChain) {
		GoToStep currentGoToStep = headOfChain;

		while(headOfChain.hasNext()) {
			currentGoToStep = currentGoToStep.next();
		}

		currentGoToStep.setNext(goToStep);
	}

	private void chain(Deployment deployment, Deployment headOfChain) {
		Deployment currentDeployment = headOfChain;

		while(headOfChain.hasNext()) {
			currentDeployment = currentDeployment.next();
		}

		currentDeployment.setNext(deployment);
	}
}
