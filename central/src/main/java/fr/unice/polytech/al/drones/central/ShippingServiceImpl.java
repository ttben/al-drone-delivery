package fr.unice.polytech.al.drones.central;

import fr.unice.polytech.al.drones.central.business.CentralModel;
import fr.unice.polytech.al.drones.central.business.DropPoint;
import fr.unice.polytech.al.drones.central.config.AddressesHolder;
import fr.unice.polytech.si5.al.projet.shipping.PackageToShip;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.HTTP;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


// Here we generate JSON data from scratch, one should use a framework instead
public class ShippingServiceImpl implements ShippingService {

    public Response addAShipping(String description) {
        ObjectMapper mapper = new ObjectMapper();
        // PackageToShip en reception

        List<PackageToShip> pkts;
        try {
            pkts = Arrays.asList(
                    mapper.readValue(description, PackageToShip[].class));
        } catch (Throwable t){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        HashMap<String, List<PackageToShip>> packagesToWarehouses
                = new HashMap<String, List<PackageToShip>>();

        // Choose packages
        for(PackageToShip pack : pkts){
            String ip = CentralModel.chooseWarehouseIP(pack.getAddress());
            if(!packagesToWarehouses.keySet().contains(ip))
                packagesToWarehouses.put(ip, new ArrayList<PackageToShip>());
            packagesToWarehouses.get(ip).add(pack);
        }

        List<DropPoint> drops = new ArrayList<DropPoint>();
        for(String ip : packagesToWarehouses.keySet()){
            List<PackageToShip> toSend = packagesToWarehouses.get(ip);
            drops.add(CentralModel.getDropPoint(toSend));
            HTTPCall.POST(ip, "/tour", Entity.entity(drops, MediaType.APPLICATION_JSON));
        }

        return Response.ok().build();
    }
}
