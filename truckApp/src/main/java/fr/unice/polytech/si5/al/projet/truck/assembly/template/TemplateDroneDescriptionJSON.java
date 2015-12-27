package fr.unice.polytech.si5.al.projet.truck.assembly.template;

/**
 * @author Etienne Strobbe
 */
public class TemplateDroneDescriptionJSON {

    private String droneName;
    private String droneID;

    public TemplateDroneDescriptionJSON(String droneName, String droneID) {
        this.droneName = droneName;
        this.droneID = droneID;
    }

    public String getDroneName() {
        return droneName;
    }

    public void setDroneName(String droneName) {
        this.droneName = droneName;
    }

    public String getDroneID() {
        return droneID;
    }

    public void setDroneID(String droneID) {
        this.droneID = droneID;
    }
}
