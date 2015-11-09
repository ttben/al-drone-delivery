package fr.unice.polytech.si5.al.projet.truck.assembly.template;

/**
 * @author Etienne Strobbe
 */
public class TemplateDeliveryDescriptionJSON {

    private String deliveryID;
    private String destination;

    public TemplateDeliveryDescriptionJSON(String deliveryID, String destination) {
        this.deliveryID = deliveryID;
        this.destination = destination;
    }

    public String getDeliveryID() {
        return deliveryID;
    }

    public void setDeliveryID(String deliveryID) {
        this.deliveryID = deliveryID;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
