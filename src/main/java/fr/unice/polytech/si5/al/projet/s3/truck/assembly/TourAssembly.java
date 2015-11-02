package fr.unice.polytech.si5.al.projet.s3.truck.assembly;

import com.google.gson.Gson;
import fr.unice.polytech.si5.al.projet.s3.truck.Node;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Etienne Strobbe (02/11/2015).
 */
public class TourAssembly {

    private static Gson gson = new Gson();


    public static List<Node> JsonToTours(String json){
        TemplateJSON templateJSON[] = gson.fromJson(json,TemplateJSON[].class);
        List<Node> tours = new ArrayList<>();
        for (int i=0 ; i<templateJSON.length ; i++){
            tours.add(getTourFromTemplate(templateJSON[i]));
        }
        return tours;
    }

    private static Node getTourFromTemplate(TemplateJSON templateJSON){
        throw new NotImplementedException();
    }
}
