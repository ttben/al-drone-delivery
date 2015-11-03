package fr.unice.polytech.si5.al.projet.s3.truck.step;


public class GoToStep extends ExecutableStep {
	private String location;

	public GoToStep(String name, String location) {
		super(name);
		this.location = location;
	}

	@Override
	public void execute() {
		System.out.println("Bitch go to " + location + "...");
		this.status = TaskStatus.DONE;
	}
}
