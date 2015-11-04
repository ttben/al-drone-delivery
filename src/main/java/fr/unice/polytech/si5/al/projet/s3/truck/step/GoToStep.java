package fr.unice.polytech.si5.al.projet.s3.truck.step;


import fr.unice.polytech.si5.al.projet.s3.truck.DroneDeliveryApp;

public class GoToStep extends ExecutableStep {
	private String location;

	private GoToStep prev;
	private GoToStep next;

	public GoToStep(String name, String location) {
		super(name);
		this.location = location;
	}

	@Override
	public void execute(DroneDeliveryApp app) {
		System.out.println("\t+ Bitch go to " + location + "...");
		this.status = TaskStatus.DONE;
	}

	@Override
	String getDescription() {
		return "(Go To " + location + ")";
	}


	public void setPrev(GoToStep prev) {
		this.prev = prev;
	}

	public void setNext(GoToStep next) {
		this.next = next;
	}

	public boolean hasPrev() {
		return this.prev != null;
	}

	public boolean hasNext() {
		return this.next != null;
	}

	public GoToStep next() {
		return this.next;
	}

	public void detach() {
		this.prev = null;
		this.next = null;
	}
}
