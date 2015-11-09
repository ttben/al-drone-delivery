package fr.unice.polytech.si5.al.projet.truck.assembly.template;

import java.util.List;

/**
 * @author Etienne Strobbe
 */
public class TemplateDeliveryJSON {
    private String drone;
    private String box;
    private List<String> droneAlt;

    public TemplateDeliveryJSON(String drone, String box, List<String> droneAlt) {
        this.drone = drone;
        this.box = box;
        this.droneAlt = droneAlt;
    }

    public String getDrone() {
        return drone;
    }

    public void setDrone(String drone) {
        this.drone = drone;
    }

    public String getBox() {
        return box;
    }

    public void setBox(String box) {
        this.box = box;
    }

    public List<String> getDroneAlt() {
        return droneAlt;
    }

    public void setDroneAlt(List<String> droneAlt) {
        this.droneAlt = droneAlt;
    }
}