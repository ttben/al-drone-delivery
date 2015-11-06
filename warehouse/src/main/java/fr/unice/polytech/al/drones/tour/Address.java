package fr.unice.polytech.al.drones.tour;

/**
 * Created by user on 06/11/2015.
 */
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
}
