package fr.unice.polytech.al.drones.central;

import fr.unice.polytech.al.drones.central.business.CentralModel;
import fr.unice.polytech.al.drones.central.business.DropPoint;
import fr.unice.polytech.al.drones.central.config.AddressesHolder;
import fr.unice.polytech.si5.al.projet.shipping.PackageToShip;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.unice.polytech.si5.al.projet.shipping.PackageToShipList;
import org.json.HTTP;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;


// Here we generate JSON data from scratch, one should use a framework instead
public class ShippingServiceImpl implements ShippingService {

    public Response addAShipping(String description) {
        ObjectMapper mapper = new ObjectMapper();
        // PackageToShip en reception
        PackageToShip pt;
        PackageToShipList pkts;
        try {
            pkts = mapper.readValue(description,  PackageToShipList.class);
        } catch (Throwable t){
            t.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        HashMap<String, List<PackageToShip>> packagesToWarehouses
                = new HashMap<String, List<PackageToShip>>();

        // Choose packages
        for(PackageToShip pack : pkts.getPackageToShipList()){
            String ip = CentralModel.chooseWarehouseIP(pack.getAddress());
            if(!packagesToWarehouses.keySet().contains(ip))
                packagesToWarehouses.put(ip, new ArrayList<PackageToShip>());
            packagesToWarehouses.get(ip).add(pack);
        }

        List<DropPoint> drops = new LinkedList<>();
        for(String ip : packagesToWarehouses.keySet()){
            List<PackageToShip> toSend = packagesToWarehouses.get(ip);
            drops.add(CentralModel.getDropPoint(toSend));
            HTTPCall.POST(ip, "/tour", Entity.entity(drops, MediaType.APPLICATION_JSON));
        }

        return Response.ok().build();
    }
}
