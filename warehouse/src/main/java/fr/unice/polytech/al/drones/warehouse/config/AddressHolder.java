package fr.unice.polytech.al.drones.warehouse.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by sebastien on 10/11/15.
 */
public class AddressHolder {

    private static String ip;

    public static void loadAddress() {
        System.out.println("Loading central address");
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = PortReacher.class.getClassLoader().getResourceAsStream("centralAddress.properties");
        }catch (Throwable t){
            System.err.println("Can't find addresses configs.");
        }
        try {
            prop.load(input);
        } catch (IOException e) {
            System.err.println("Malformed addresses configs.");
        }
        ip = prop.getProperty("central");
    }
}
