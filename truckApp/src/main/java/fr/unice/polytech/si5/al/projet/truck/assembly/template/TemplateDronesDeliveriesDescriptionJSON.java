package fr.unice.polytech.si5.al.projet.truck.assembly.template;

import java.util.List;

/**
 * @author Etienne Strobbe
 */
public class TemplateDronesDeliveriesDescriptionJSON {

    private List<TemplateDroneDescriptionJSON> drones;
    private List<TemplateDeliveryDescriptionJSON> deliveries;

    public TemplateDronesDeliveriesDescriptionJSON(List<TemplateDroneDescriptionJSON> droneDescriptions, List<TemplateDeliveryDescriptionJSON> deliveryDescriptions) {
        this.drones = droneDescriptions;
        this.deliveries = deliveryDescriptions;
    }

    public List<TemplateDroneDescriptionJSON> getDroneDescriptions() {
        return drones;
    }

    public void setDroneDescriptions(List<TemplateDroneDescriptionJSON> droneDescriptions) {
        this.drones = droneDescriptions;
    }

    public List<TemplateDeliveryDescriptionJSON> getDeliveryDescriptions() {
        return deliveries;
    }

    public void setDeliveryDescriptions(List<TemplateDeliveryDescriptionJSON> deliveryDescriptions) {
        this.deliveries = deliveryDescriptions;
    }
}
