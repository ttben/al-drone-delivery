package fr.unice.polytech.si5.al.projet.truck.assembly.template;

import java.util.List;

/**
 * @author Etienne Strobbe
 */
public class TemplateDropPointJSON {


    private String location;
    private List<TemplateDeliveryJSON> deliveries;

    public TemplateDropPointJSON(String location, List<TemplateDeliveryJSON> deliveries) {
        this.location = location;
        this.deliveries = deliveries;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<TemplateDeliveryJSON> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<TemplateDeliveryJSON> deliveries) {
        this.deliveries = deliveries;
    }



}
