package fr.unice.polytech.si5.al.projet.s3.truck.assembly;

import java.util.List;

/**
 * @author Etienne Strobbe (02/11/2015).
 */
public class TemplateJSON {


    public String location;
    public List<TemplateDeliveryJSON> deliveries;

    public TemplateJSON(String location, List<TemplateDeliveryJSON> deliveries) {
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






    public class TemplateDeliveryJSON{
        public String drone;
        public String box;
        public List<String> droneAlt;

        public TemplateDeliveryJSON(String drone, String box, List<String> droneAlt){
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
}
