package fr.unice.polytech.si5.al.projet.s3.truck;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * A tour is a set of assignment, and an assignment is a location (drop point location) and a list of tasks
 */
public class Tour {

	private Queue<DropPoint> dropPointQueue;
	private String name;
	private TaskStatus status;

	public Tour(String name, List<DropPoint> dropPointsList) {
		this.name = name;
		this.dropPointQueue = new LinkedList<>();
		dropPointsList.forEach(dropPointQueue::add);
		status = TaskStatus.PENDING;
	}

	public Queue<DropPoint> getDropPointQueue() {
		return dropPointQueue;
	}

	public boolean isDone() {
		return status == TaskStatus.DONE;
	}

	public int getNumberOfDropPointOnQueue() {
		return this.dropPointQueue.size();
	}

	public void execute() {
		try {

			DropPoint currentDropPoint = dropPointQueue.element();
			currentDropPoint = cleanQueueAndGetTopIfNotEmpty(currentDropPoint);

			currentDropPoint.execute();
			cleanQueueAndGetTopIfNotEmpty(currentDropPoint);

		} catch (NoSuchElementException e) {
			status = TaskStatus.DONE;
			System.out.println("There is no more things to do on this tour !");
		}
	}

	private DropPoint cleanQueueAndGetTopIfNotEmpty(DropPoint lastDropPointHandled) {
		while (lastDropPointHandled.isDone() || lastDropPointHandled.failed()) {
			dropPointQueue.poll();
			lastDropPointHandled = dropPointQueue.element();
		}
		return lastDropPointHandled;
	}

}