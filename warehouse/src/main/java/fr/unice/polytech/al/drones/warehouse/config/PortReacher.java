package fr.unice.polytech.al.drones.warehouse.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by sebastien on 10/11/15.
 */
public class PortReacher {


    public static int getPort() {
        System.out.println("Finding port.");
        Properties prop = new Properties();
        InputStream input = null;
        int res = 0;
        try {
            input = PortReacher.class.getClassLoader().getResourceAsStream("server.properties");
        }catch (Throwable t){
            System.err.println("Can't find server configs.");
        }
        try {
            prop.load(input);
            res = Integer.parseInt(prop.getProperty("port"));
        } catch (IOException e) {
            System.err.println("Malformed server configs.");
        }
        return res;
    }
}
