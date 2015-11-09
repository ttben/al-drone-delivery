package fr.unice.polytech.si5.al.projet.truck.assembly.template;

import java.util.List;

/**
 * @author Etienne Strobbe
 */
public class TemplateDronesDeliveriesDescriptionJSON {

    private List<TemplateDroneDescriptionJSON> droneDescriptions;
    private List<TemplateDeliveryDescriptionJSON> deliveryDescriptions;

    public TemplateDronesDeliveriesDescriptionJSON(List<TemplateDroneDescriptionJSON> droneDescriptions, List<TemplateDeliveryDescriptionJSON> deliveryDescriptions) {
        this.droneDescriptions = droneDescriptions;
        this.deliveryDescriptions = deliveryDescriptions;
    }

    public List<TemplateDroneDescriptionJSON> getDroneDescriptions() {
        return droneDescriptions;
    }

    public void setDroneDescriptions(List<TemplateDroneDescriptionJSON> droneDescriptions) {
        this.droneDescriptions = droneDescriptions;
    }

    public List<TemplateDeliveryDescriptionJSON> getDeliveryDescriptions() {
        return deliveryDescriptions;
    }

    public void setDeliveryDescriptions(List<TemplateDeliveryDescriptionJSON> deliveryDescriptions) {
        this.deliveryDescriptions = deliveryDescriptions;
    }
}
