package fr.unice.polytech.si5.al.projet.s3.truck;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

public class DropPoint extends Step {

	private Queue<Delivery> pendingDeliveriesQueue;
	private Queue<Delivery> doneDeliveriesQueue;
	private Queue<Delivery> failedDeliveriesQueue;
	private String location;
	private TaskStatus status;

	public DropPoint(String location, List<Delivery> deliveryList) {
		this.location = location;
		this.pendingDeliveriesQueue = new LinkedList<>();
		deliveryList.forEach(pendingDeliveriesQueue::add);
		this.status = TaskStatus.PENDING;
	}

	public Queue<Delivery> getPendingDeliveriesQueue() {
		return this.pendingDeliveriesQueue;
	}

	public void execute() {
		status = TaskStatus.PROCESSING;
		System.out.println("Executing DropPoint at position : " + location);

		try {
			cleanPendingQueue();
			Delivery currentDelivery = pendingDeliveriesQueue.element();
			currentDelivery.execute();
		} catch (NoSuchElementException e) {
			this.done();
			System.out.println("There is no more things to do on this dropPoint !");
		}
	}


	/**
	 * Clean head of queue :  all doneDropPointsQueue or failed element are deleted
	 */
	private void cleanPendingQueue() {
		Delivery currentDelivery = pendingDeliveriesQueue.element();

		while (currentDelivery.isDone() || currentDelivery.isFailed()) {

			if (currentDelivery.isDone()) {
				this.doneDeliveriesQueue.add(currentDelivery);
			} else {
				this.failedDeliveriesQueue.add(currentDelivery);
			}

			pendingDeliveriesQueue.poll();
			currentDelivery = pendingDeliveriesQueue.element();
		}
	}
}
