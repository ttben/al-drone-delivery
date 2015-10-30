package fr.unice.polytech.si5.al.projet.s3.central;

public class Location {

	private String name;
	private String number;
	private String street;
	private String city;
	private String zipOrPostalCode;
	private String country;

	public Location(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}