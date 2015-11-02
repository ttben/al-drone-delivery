package fr.unice.polytech.si5.al.projet.s3.truck.assembly;

import com.google.gson.Gson;
import fr.unice.polytech.si5.al.projet.s3.truck.Delivery;
import fr.unice.polytech.si5.al.projet.s3.truck.Node;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Etienne Strobbe (02/11/2015).
 */
public class DeliveryAssembly {

    private static Gson gson = new Gson();

    public static Delivery JsonToDelivery(String json){
        System.out.println(json);
        TemplateJSON templateJSON[] = gson.fromJson(json,TemplateJSON[].class);

        return null;
    }


}
