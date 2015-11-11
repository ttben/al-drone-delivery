package fr.unice.polytech.al.drones.central.config;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by sebastien on 10/11/15.
 */
public class AddressesHolder {

    public static HashMap<String, String> loadAddresses() {
        System.out.println("Loading addresses");
        HashMap<String, String> reelAdressToIP = new HashMap<String, String>();
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("classes/addresses.properties");
        }catch (Throwable t){
            System.err.println("Can't find addresses configs.");
        }
        try {
            prop.load(input);
        } catch (IOException e) {
            System.err.println("Malformed addresses configs.");
        }
        for(Map.Entry<Object, Object> p : prop.entrySet()){
            reelAdressToIP.put(p.getKey().toString(), p.getValue().toString());
        }
        System.out.println("Addresses : " + prop.entrySet());
        return reelAdressToIP;
    }
}
