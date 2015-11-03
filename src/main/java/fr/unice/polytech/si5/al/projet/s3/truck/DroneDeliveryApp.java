package fr.unice.polytech.si5.al.projet.s3.truck;

import fr.unice.polytech.si5.al.projet.s3.drone.Drone;
import fr.unice.polytech.si5.al.projet.s3.drone.ParrotDrone;
import fr.unice.polytech.si5.al.projet.s3.truck.assembly.TourAssembly;
import fr.unice.polytech.si5.al.projet.s3.truck.step.Sequence;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class DroneDeliveryApp {
	private Sequence tour;

	private Map<String, Drone> mapIDToDrone = new HashMap<>();
	private Map<String, Box> mapIDToBox = new HashMap<>();

	public DroneDeliveryApp(String pathOfFile) {
		try {
			BufferedReader br = null;
			br = new BufferedReader(new FileReader(pathOfFile));

			String res = "";
			String cr = "";

			while ((cr = br.readLine()) != null) {
				System.out.println(cr);
				res += cr;
			}

			System.out.println("RES\n" + res);

			Sequence t = TourAssembly.jsonToTour(res);
			this.tour = t;
			System.out.println(res);
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}

		mapIDToDrone.put("droneID1", new ParrotDrone("GROS ROSE"));
		mapIDToDrone.put("droneID2", new ParrotDrone("PETIT BLEU"));
		mapIDToDrone.put("droneID3", new ParrotDrone("GROS MARRON"));

		mapIDToBox.put("packageID1", new Box("chez etienne",12));
		mapIDToBox.put("packageID2", new Box("chez moi",14));
		mapIDToBox.put("packageID3", new Box("chez toi",128));
		mapIDToBox.put("packageID4", new Box("chez lui",15212));

	}

	public void start() {
		while (!this.tour.isDone()) {
			tour.execute(this);
		}
	}

	public Drone getDroneByID(String id) {
		return this.mapIDToDrone.get(id);
	}

	public Box getBoxByID(String box) {
		return this.mapIDToBox.get(box);
	}
}