package fr.unice.polytech.si5.al.projet.central;

public class Address {

	private String name;
	private String number;
	private String street;
	private String city;
	private String zipOrPostalCode;
	private String country;

	public Address(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipOrPostalCode() {
		return zipOrPostalCode;
	}

	public void setZipOrPostalCode(String zipOrPostalCode) {
		this.zipOrPostalCode = zipOrPostalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Address() {
	}
}