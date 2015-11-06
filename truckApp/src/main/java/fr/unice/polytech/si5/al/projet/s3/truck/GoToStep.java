package fr.unice.polytech.si5.al.projet.s3.truck;


public class GoToStep extends State {
	private String location;


	public GoToStep(String name, String location) {
		super(name);
		this.location = location;
	}

	public void execute(DroneDeliveryApp app) {
		System.out.println("\t+ Bitch go to " + location + "...");
		this.status = TaskStatus.DONE;
	}

	String getDescription() {
		return "(Go To " + location + ")";
	}
}
