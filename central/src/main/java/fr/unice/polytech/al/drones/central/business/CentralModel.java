package fr.unice.polytech.al.drones.central.business;

import fr.unice.polytech.al.drones.central.config.AddressesHolder;
import fr.unice.polytech.si5.al.projet.algorithm.WeightedWaypoint;
import fr.unice.polytech.si5.al.projet.algorithm.clustering.Cluster;
import fr.unice.polytech.si5.al.projet.algorithm.clustering.SimpleClustering;
import fr.unice.polytech.si5.al.projet.algorithm.sequencing.Sequence;
import fr.unice.polytech.si5.al.projet.algorithm.sequencing.SimplePointSequencingAlgorithm;
import fr.unice.polytech.si5.al.projet.central.Address;
import fr.unice.polytech.si5.al.projet.central.AddressToCoordsResolver;
import fr.unice.polytech.si5.al.projet.math.Vector2D;
import fr.unice.polytech.si5.al.projet.shipping.PackageToShip;

import java.util.*;

/**
 * Created by sebastien on 11/11/15.
 */
public class CentralModel {

    private static CentralModel m;
    private static WarehouseChooser w;

    public CentralModel(HashMap<String, String> warehouses) {
        w = new WarehouseChooser(warehouses);
    }

    public CentralModel getInstance(){
        if(m == null)
            m = new CentralModel(AddressesHolder.loadAddresses());
        return m;
    }


    public static String chooseWarehouseIP(Address address) {
        return w.choose(address);
    }

    public static DropPoint getDropPoint(List<PackageToShip> toSend) {
        ArrayList<Delivery> deliveries = new ArrayList<Delivery>();
        for(PackageToShip pck : toSend){
            deliveries.add(new Delivery("drone1", "box", "drone2", pck.getAddress()));
        }
        return new DropPoint("dropLocation", deliveries);
    }

	public static List<List<DropPoint>> computeTours(List<PackageToShip> packagesToSend) {
		AddressToCoordsResolver addressResolver = new AddressToCoordsResolver();

		Vector2D warehousePosition = new Vector2D(250, 250);

		Map<PackageToShip, Vector2D> packagesToShipPositions = new HashMap<>();
		for (PackageToShip p : packagesToSend) {
			Vector2D addressPosition = addressResolver.resolveAddress(p.getAddress());
			packagesToShipPositions.put(p, addressPosition);
		}

		List<Vector2D> points = new LinkedList<>(packagesToShipPositions.values());

		//List<Vector2D> points = PointGenerator.generateVector2D(200);

		// First cluster the points into drop points
		List<Cluster> clusters = new SimpleClustering().process(points, 50, 50);

		// Then sequence the clusters
		List<Sequence> clustersSequences = new SimplePointSequencingAlgorithm().process(
				clusters, warehousePosition,
				3, 15
		);

		// Now, map the algorithms model to the Drone Delivery business model
		List<List<DropPoint>> tours = new ArrayList<>();
		// 1 sequence = 1 tour
		for (Sequence sequence : clustersSequences) {
			List<DropPoint> tour = new LinkedList<>();
			tours.add(tour);
			// 1 waypoint in sequence = 1 drop point in tour
			for (WeightedWaypoint waypoint : sequence) {
				Cluster cluster = (Cluster) waypoint.getSourceObject();
				Vector2D clusterAnchor = cluster.computeAnchor();

				List<Delivery> dropPointDeliveries = new LinkedList<>();
				List<Vector2D> clusterSourcesVectors = (List<Vector2D>) cluster.getSourceObject(); // TODO: retrieve from algo result
				//List<PackageToShip> clusterSourcesPackages = null; // TODO: retrieve from algo result
				// clusters sources = packages to ship for drop point;

				// Iterator over the vectors
				for (Vector2D vector : clusterSourcesVectors) {
					// Many PackageToShip can be bound to the same location.
					List<PackageToShip> packagesToShipAtLocation =
							CentralModel.getKeysFor(packagesToShipPositions, vector);
					for (PackageToShip packageToShip : packagesToShipAtLocation) {
						dropPointDeliveries.add(new Delivery("drone1", "box", "drone2", packageToShip.getAddress()));
					}
				}


				DropPoint dropPoint = new DropPoint(clusterAnchor.getX()+"/"+clusterAnchor.getY(), dropPointDeliveries);
				tour.add(dropPoint);
			}
		}

		return tours;
	}

	static private <T, U> List<T> getKeysFor(Map<T, U> set, U value) {
		List<T> matchingKeys = new LinkedList<>();
		for (T key : set.keySet()) {
			if (set.get(key) == value) {
				matchingKeys.add(key);
			}
		}
		return matchingKeys;
	}
}
