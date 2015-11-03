package fr.unice.polytech.si5.al.projet.s3.truck;

public class Step {
	protected TaskStatus status;

	public void done() {
		this.status = TaskStatus.DONE;
	}

	public void failed() {
		this.status = TaskStatus.ERRORED;
	}

	public boolean isDone() {
		return this.status == TaskStatus.DONE;
	}

	public boolean isFailed() {
		return this.status == TaskStatus.ERRORED;
	}
}
