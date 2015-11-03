package fr.unice.polytech.si5.al.projet.s3.truck.assembly;

import java.util.List;

/**
 * @author Etienne Strobbe (02/11/2015).
 */
public class TemplateDropPoint {

    public String location;
    public List<TemplateDelivery> deliveries;

    public TemplateDropPoint(String location, List<TemplateDelivery> deliveries) {
        this.location = location;
        this.deliveries = deliveries;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<TemplateDelivery> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<TemplateDelivery> deliveries) {
        this.deliveries = deliveries;
    }
}