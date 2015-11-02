package fr.unice.polytech.si5.al.projet.s3.truck;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

public class DropPoint {

	private Queue<Delivery> deliveryQueue;
	private String location;
	private TaskStatus status;

	public DropPoint(String location, List<Delivery> deliveryList) {
		this.location = location;
		this.deliveryQueue = new LinkedList<>();
		deliveryList.forEach(deliveryQueue::add);
		this.status = TaskStatus.PENDING;
	}

	public boolean isDone() {
		return status == TaskStatus.DONE;
	}

	public void done() {
		status = TaskStatus.DONE;
	}

	public boolean failed() {
		return status == TaskStatus.ERRORED;
	}

	public Queue<Delivery> getDeliveryQueue() {
		return this.deliveryQueue;
	}

	public void execute() {
		status = TaskStatus.PROCESSING;
		System.out.println("Executing DropPoint at position : " + location);

		try {
			Delivery currentDelivery = deliveryQueue.element();
			currentDelivery = cleanQueueAndGetTopIfNotEmpty(currentDelivery);
			currentDelivery.execute();
			cleanQueueAndGetTopIfNotEmpty(currentDelivery);
		} catch (NoSuchElementException e) {
			this.done();
			System.out.println("There is no more things to do on this dropPoint !");
		}
	}

	private Delivery cleanQueueAndGetTopIfNotEmpty(Delivery lastDeliveryHandled) {
		while (lastDeliveryHandled.isDone() || lastDeliveryHandled.failed()) {
			deliveryQueue.poll();
			lastDeliveryHandled = deliveryQueue.element();
		}
		return lastDeliveryHandled;
	}
}
