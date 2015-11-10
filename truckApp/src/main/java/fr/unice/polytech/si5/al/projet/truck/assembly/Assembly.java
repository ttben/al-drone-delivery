package fr.unice.polytech.si5.al.projet.truck.assembly;

import com.google.gson.Gson;
import fr.unice.polytech.si5.al.projet.truck.*;
import fr.unice.polytech.si5.al.projet.truck.assembly.template.TemplateDeliveryDescriptionJSON;
import fr.unice.polytech.si5.al.projet.truck.assembly.template.TemplateDroneDescriptionJSON;
import fr.unice.polytech.si5.al.projet.truck.assembly.template.TemplateDronesDeliveriesDescriptionJSON;
import fr.unice.polytech.si5.al.projet.truck.assembly.template.TemplateDropPointJSON;
import fr.unice.polytech.si5.al.projet.truck.domain.Deployment;
import fr.unice.polytech.si5.al.projet.truck.domain.DropPoint;
import fr.unice.polytech.si5.al.projet.truck.domain.GoToStep;
import fr.unice.polytech.si5.al.projet.truck.domain.Tour;
import fr.unice.polytech.si5.al.projet.truck.domain.delivery.Delivery;
import fr.unice.polytech.si5.al.projet.truck.domain.delivery.DeliveryID;
import fr.unice.polytech.si5.al.projet.truck.domain.drone.Drone;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Etienne Strobbe
 */
public class Assembly {

    private static Gson gson = new Gson();

    public static String getFile(String fileName) {

        StringBuilder result = new StringBuilder("");

        //Get file from fr.unice.polytech.al.drones.central.resources folder
        ClassLoader classLoader = Assembly.class.getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                result.append(line).append("\n");
            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();

    }

    public static List<DropPoint> buildDropPointList(String json){
        List<DropPoint> dropPointsToReturn = new ArrayList<>();
        TemplateDropPointJSON[] templateDropPointJsonTab = gson.fromJson(json,TemplateDropPointJSON[].class);
        for (TemplateDropPointJSON aTemplateDropPointJson : templateDropPointJsonTab) {
            // building deployment and GoToStep
            List<Drone> theDrones = DeliveryToDroneDispatcher.dispatchDeliveryToDroneFromTemplate(aTemplateDropPointJson);
            Map<DeliveryID,List<Drone>> altDroneMap = DeliveryToDroneDispatcher.dispatchAltDroneToDeliveryFromTemplate(aTemplateDropPointJson);
            Deployment deployment = new Deployment(theDrones,altDroneMap);
            GoToStep goToStep = new GoToStep("GoToStep",aTemplateDropPointJson.getLocation());
            DropPoint tmp = new DropPoint(goToStep,deployment);
            dropPointsToReturn.add(tmp);
        }
        return dropPointsToReturn;
    }

    public static List<Drone> buildDrones(String json){
        List<Drone> dronesToReturn = new ArrayList<>();
        TemplateDronesDeliveriesDescriptionJSON templateDronesDeliveriesDescriptionJSON = gson.fromJson(json,TemplateDronesDeliveriesDescriptionJSON.class);
        List<TemplateDroneDescriptionJSON> templateDroneDescriptionJSONList = templateDronesDeliveriesDescriptionJSON.getDroneDescriptions();
        for (TemplateDroneDescriptionJSON templateDrone : templateDroneDescriptionJSONList){
            Drone drone = new Drone(templateDrone.getDroneID(),templateDrone.getDroneName());
            dronesToReturn.add(drone);
        }
        return dronesToReturn;
    }

    public static List<Delivery> buildDelivery(String json){
        List<Delivery> deliveriesToReturn = new ArrayList<>();
        TemplateDronesDeliveriesDescriptionJSON templateDronesDeliveriesDescriptionJSON = gson.fromJson(json,TemplateDronesDeliveriesDescriptionJSON.class);
        List<TemplateDeliveryDescriptionJSON> templateDeliveryDescriptionJSONList = templateDronesDeliveriesDescriptionJSON.getDeliveryDescriptions();
        for (TemplateDeliveryDescriptionJSON templateDelivery : templateDeliveryDescriptionJSONList){
            Delivery delivery = new Delivery(new DeliveryID(templateDelivery.getDeliveryID()), templateDelivery.getDestination());
            deliveriesToReturn.add(delivery);
        }
        return deliveriesToReturn;
    }

    public static Tour getTourFromJson(String descriptionJson, String missionJson){
        DataBaseCreator.init(descriptionJson);
        List<DropPoint> dropPoints = buildDropPointList(missionJson);
        return new Tour(dropPoints);
    }



    public static void main(String[] args) {
        String missionJson = getFile("json/app-in-stub.json");
        String descriptionJson = getFile("json/drones-n-deliveries-descriptions.json");
        //System.out.printf(missionJson);
        //System.out.printf(descriptionJson);
        Tour t = getTourFromJson(descriptionJson,missionJson);
        System.out.println("La tour = "+t);
    }

}
