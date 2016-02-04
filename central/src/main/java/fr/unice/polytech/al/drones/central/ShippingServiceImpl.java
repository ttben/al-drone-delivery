package fr.unice.polytech.al.drones.central;

import fr.unice.polytech.al.drones.central.business.CentralModel;
import fr.unice.polytech.al.drones.central.business.DropPoint;
import fr.unice.polytech.si5.al.projet.shipping.PackageToShip;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.unice.polytech.si5.al.projet.shipping.PackageToShipList;

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
        HashMap<String, List<PackageToShip>> packagesToWarehouses = new HashMap<String, List<PackageToShip>>();

        // Attribute a warehouses for each package to ship
        for(PackageToShip pack : pkts.getPackageToShipList()){
			// Get the warehouse that will take care of the package
            String warehouseIP = CentralModel.chooseWarehouseIP(pack.getAddress());
			// Add the package
			List<PackageToShip> warehousePackages = packagesToWarehouses.getOrDefault(warehouseIP, new ArrayList<>());
			warehousePackages.add(pack);
			packagesToWarehouses.put(warehouseIP, warehousePackages);
        }

		// Generate drop points to ship all the packages.
		//
		// Foreach warehouse, generate a tours
        for(String warehouseIP : packagesToWarehouses.keySet()){
			// Retrieve all the packages that have to be shipped from this warehouse.
            List<PackageToShip> toSend = packagesToWarehouses.get(warehouseIP);
			//List<DropPoint> dropPoints = CentralModel.computeDropPoints(toSend);
			List<List<DropPoint>> tours = CentralModel.computeTours(toSend);
            for (List<DropPoint> tour : tours) {
				HTTPCall.POST(warehouseIP, "/tour", Entity.entity(tour, MediaType.APPLICATION_JSON));
			}
        }

        return Response.ok().build();
    }
}
