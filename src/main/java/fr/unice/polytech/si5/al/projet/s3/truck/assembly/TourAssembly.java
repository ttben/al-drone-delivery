package fr.unice.polytech.si5.al.projet.s3.truck.assembly;

import com.google.gson.Gson;
import fr.unice.polytech.si5.al.projet.s3.drone.Drone;
import fr.unice.polytech.si5.al.projet.s3.drone.ParrotDrone;
import fr.unice.polytech.si5.al.projet.s3.truck.Box;
import fr.unice.polytech.si5.al.projet.s3.truck.step.Delivery;
import fr.unice.polytech.si5.al.projet.s3.truck.step.ExecutableStep;
import fr.unice.polytech.si5.al.projet.s3.truck.step.GoToStep;
import fr.unice.polytech.si5.al.projet.s3.truck.step.Sequence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Etienne Strobbe (02/11/2015).
 */
public class TourAssembly {

    private static Gson gson = new Gson();

    public static Sequence jsonToTour(String json) {
        TemplateDropPoint[] templateJSONs = gson.fromJson(json, TemplateDropPoint[].class);
        List<ExecutableStep> dropPointList = new ArrayList<>();
        for (int i = 0; i < templateJSONs.length; i++) {
            dropPointList.add(getDropPointFromTemplate(templateJSONs[i]));
        }

        return new Sequence("Tour bidule", dropPointList);
    }

    private static Sequence getDropPointFromTemplate(TemplateDropPoint templateJSON) {
        List<ExecutableStep> deliveryList = new ArrayList<>();

        GoToStep goToStep = new GoToStep("Goto", templateJSON.getLocation());
        deliveryList.add(goToStep);

        templateJSON.getDeliveries().forEach(
                templateDeliveryJSON -> deliveryList.add(getDeliveryFromTemplate(templateDeliveryJSON))
        );
        
        return new Sequence(templateJSON.getLocation(), deliveryList);
    }

    private static ExecutableStep getDeliveryFromTemplate(TemplateDelivery templateDeliveryJSON) {
          return new Delivery("Delivery",
                templateDeliveryJSON.getBox(),
                templateDeliveryJSON.getDrone(),
                templateDeliveryJSON.getDroneAlt()
        );
    }

    public static String getFile(String fileName) {

        StringBuilder result = new StringBuilder("");

        //Get file from resources folder
        ClassLoader classLoader = TourAssembly.class.getClassLoader();
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

}
