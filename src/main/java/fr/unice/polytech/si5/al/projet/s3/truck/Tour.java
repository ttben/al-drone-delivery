package fr.unice.polytech.si5.al.projet.s3.truck;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * A tour is a set of assignment, and an assignment is a location (drop point location) and a list of tasks
 */
public class Tour {

	private Queue<DropPoint> pendingDropPointsQueue = new LinkedList<>();
	private Queue<DropPoint> doneDropPointsQueue = new LinkedList<>();
	private Queue<DropPoint> failedDropPointsQueue = new LinkedList<>();

	private String name;
	private TaskStatus status;

	public Tour(String name, List<DropPoint> dropPointsList) {
		this.name = name;
		this.status = TaskStatus.PENDING;
		dropPointsList.forEach(this.pendingDropPointsQueue::add);
	}

	public Queue<DropPoint> getPendingDropPointsQueue() {
		return pendingDropPointsQueue;
	}

	public Queue<DropPoint> getFailedDropPointsQueue() {
		return failedDropPointsQueue;
	}

	public Queue<DropPoint> getDoneDropPointsQueue() {
		return doneDropPointsQueue;
	}

	public boolean isDone() {
		return this.status == TaskStatus.DONE;
	}

	public int getNumberOfDropPointsPending() {
		return this.pendingDropPointsQueue.size();
	}


	public int getNumberOfDropPointsFailed() {
		return this.failedDropPointsQueue.size();
	}


	public int getNumberOfDropPointsDone() {
		return this.doneDropPointsQueue.size();
	}

	public void execute() {
		try {
			cleanPendingQueue();
			DropPoint currentDropPoint = this.pendingDropPointsQueue.element();
			currentDropPoint.execute();
		} catch (NoSuchElementException e) {
			this.status = TaskStatus.DONE;
			System.out.println("There is no more things to do on this tour !");
		}
	}


	/**
	 * Clean head of queue :  all doneDropPointsQueue or failed element are deleted
	 */
	private void cleanPendingQueue() {
		DropPoint currentDropPoint = pendingDropPointsQueue.element();

		while (currentDropPoint.isDone() || currentDropPoint.isFailed()) {

			if (currentDropPoint.isDone()) {
				this.doneDropPointsQueue.add(currentDropPoint);
			} else {
				this.failedDropPointsQueue.add(currentDropPoint);
			}

			pendingDropPointsQueue.poll();
			currentDropPoint = pendingDropPointsQueue.element();
		}
	}
}