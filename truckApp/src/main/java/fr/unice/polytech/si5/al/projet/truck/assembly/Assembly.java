package fr.unice.polytech.si5.al.projet.truck.assembly;

import com.google.gson.Gson;
import fr.unice.polytech.si5.al.projet.truck.*;
import fr.unice.polytech.si5.al.projet.truck.assembly.template.TemplateDropPointJSON;
import fr.unice.polytech.si5.al.projet.truck.domain.Deployment;
import fr.unice.polytech.si5.al.projet.truck.domain.DropPoint;
import fr.unice.polytech.si5.al.projet.truck.domain.GoToStep;
import fr.unice.polytech.si5.al.projet.truck.domain.delivery.Delivery;
import fr.unice.polytech.si5.al.projet.truck.domain.delivery.DeliveryID;
import fr.unice.polytech.si5.al.projet.truck.domain.drone.Drone;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.File;
import java.io.IOException;
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

        //Get file from resources folder
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

    public static DropPoint createChainedDropPoints(String json){
        DropPoint head = null;
        DropPoint current = null;
        TemplateDropPointJSON[] templateDropPointJsonTab = gson.fromJson(json,TemplateDropPointJSON[].class);
        for (TemplateDropPointJSON aTemplateDropPointJson : templateDropPointJsonTab) {
            // building deployment and GoToStep
            List<Drone> theDrones = DeliveryToDroneDispatcher.dispatchDeliveryToDroneFromTemplate(aTemplateDropPointJson);
            Map<DeliveryID,List<Drone>> altDroneMap = DeliveryToDroneDispatcher.dispatchAltDroneToDeliveryFromTemplate(aTemplateDropPointJson);
            Deployment deployment = new Deployment(theDrones,altDroneMap);
            GoToStep goToStep = new GoToStep("GoToStep",aTemplateDropPointJson.getLocation());
            DropPoint tmp = new DropPoint(goToStep,deployment);
            // 1st pass
            if (head == null){
                head = tmp;
                current = head;
            }
            else {
                DeliveryToDroneDispatcher.chain(current,tmp);
                current = current.next();
            }
        }
        return head;
    }

    private static List<Drone> buildDrones(String json){
        throw new NotImplementedException();
    }



    public static void main(String[] args) {
        String json = getFile("json/app-in-stub.json");
        System.out.printf(json);
        DropPoint dropPoint = createChainedDropPoints(json);
    }

}
