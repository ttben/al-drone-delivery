package fr.unice.polytech.si5.al.projet.s3.truck.step;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

public class Sequence extends ExecutableStep {
	private Queue<ExecutableStep> pendingStepQueue = new LinkedList<>();
	private Queue<ExecutableStep> doneStepQueue = new LinkedList<>();
	private Queue<ExecutableStep> failedStepQueue = new LinkedList<>();

	public Sequence(String name, List<ExecutableStep> stepList) {
		super(name);
		stepList.forEach(pendingStepQueue::add);
	}

	public Queue<ExecutableStep> getPendingStepQueue() {
		return pendingStepQueue;
	}

	public Queue<ExecutableStep> getFailedStepQueue() {
		return failedStepQueue;
	}

	public Queue<ExecutableStep> getDoneStepQueue() {
		return doneStepQueue;
	}

	public int getNumberOfDropPointsPending() {
		return this.pendingStepQueue.size();
	}


	public int getNumberOfDropPointsFailed() {
		return this.failedStepQueue.size();
	}


	public int getNumberOfDropPointsDone() {
		return this.doneStepQueue.size();
	}

	public void execute() {
		status = TaskStatus.PROCESSING;

		try {
			cleanPendingQueue();
			ExecutableStep currentDelivery = pendingStepQueue.element();
			currentDelivery.execute();
		} catch (NoSuchElementException e) {
			this.status = TaskStatus.DONE;
			System.out.println("There is no more things to do on this dropPoint !");
		}
	}

	private void cleanPendingQueue() {
		ExecutableStep currentStep = pendingStepQueue.element();

		while (currentStep.isDone() || currentStep.isFailed()) {

			if (currentStep.isDone()) {
				this.doneStepQueue.add(currentStep);
			} else {
				this.failedStepQueue.add(currentStep);
			}

			pendingStepQueue.poll();
			currentStep = pendingStepQueue.element();
		}
	}
}