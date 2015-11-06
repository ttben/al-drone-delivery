package fr.unice.polytech.si5.al.projet.s3.truck;

public abstract class State {

	public boolean isValidated() {
		return this.status == TaskStatus.VALIDATED;
	}

	public boolean hasStarted() {
		return this.status == TaskStatus.STARTED;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void pending() {
		this.status = TaskStatus.PENDING;
	}

	protected enum TaskStatus {
		PENDING,
		STARTED,
		VALIDATED,
		DOING,
		DONE,
		FAILED
	}

	protected String name;
	protected TaskStatus status;

	protected State(String name) {
		this.name = name;
		this.status = TaskStatus.PENDING;
	}

	public void doing() {
		this.status = TaskStatus.DOING;
	}

	public void fail() {
		this.status = TaskStatus.FAILED;
	}

	public void done() { this.status = TaskStatus.DONE; }

	public void start() {
		this.status = TaskStatus.STARTED;
	}

	public void validate() {
		this.status = TaskStatus.VALIDATED;
	}

	public boolean isDone() {
		return this.status == TaskStatus.DONE;
	}

	public boolean isFailed() {
		return this.status == TaskStatus.FAILED;
	}

	public boolean isPending() {
		return this.status == TaskStatus.PENDING;
	}

	public boolean isDoing() {
		return this.status == TaskStatus.DOING;
	}

	public String getName() {
		return this.name;
	}

}