package fr.unice.polytech.al.drones.central.business;

import org.json.JSONObject;

public class CustomerAddress {

    private String name;
    private String number;
    private String street;
    private String city;
    private String zipOrPostalCode;
    private String country;

    public CustomerAddress(String name, String number, String street, String city, String zipOrPostalCode, String country) {
        this.name = name;
        this.number = number;
        this.street = street;
        this.city = city;
        this.zipOrPostalCode = zipOrPostalCode;
        this.country = country;
    }

    public JSONObject toJSON(){
        JSONObject js = new JSONObject();
        js.put("name", name);
        js.put("number", number);
        js.put("street", street);
        js.put("city", city);
        js.put("zipOrPostalCode", zipOrPostalCode);
        js.put("country", country);
        return js;
    }

}