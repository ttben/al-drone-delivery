package fr.unice.polytech.si5.al.projet.truck;

import fr.unice.polytech.si5.al.projet.truck.assembly.Assembly;
import fr.unice.polytech.si5.al.projet.truck.domain.Deployment;
import fr.unice.polytech.si5.al.projet.truck.domain.DropPoint;
import fr.unice.polytech.si5.al.projet.truck.domain.GoToStep;
import fr.unice.polytech.si5.al.projet.truck.domain.Tour;
import fr.unice.polytech.si5.al.projet.truck.domain.delivery.Delivery;
import fr.unice.polytech.si5.al.projet.truck.domain.delivery.DeliveryID;
import fr.unice.polytech.si5.al.projet.truck.domain.drone.Drone;
import fr.unice.polytech.si5.al.projet.truck.domain.drone.DroneID;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

/**
 * Created by Benjamin on 06/11/2015.
 */
public class Controller {
	private Tour model;
	private View view;

	public static boolean DEMO = true;

	public Controller() throws IOException {
		Tour t = null;

		if(DEMO) {

			System.out.println("Recuperation des informations sur la tournee ...");

			URL warehouseGetTour = new URL("http://localhost:8383/tour");
			URLConnection yc = warehouseGetTour.openConnection();
			BufferedReader in = new BufferedReader(
					new InputStreamReader(
							yc.getInputStream()));
			String inputLine;

			String res = "";
			while ((inputLine = in.readLine()) != null) {
				if(!DEMO) {
					System.out.println(inputLine);
				}
				res += inputLine;
			}
			in.close();


			t = Assembly.getTourFromJson(Assembly.getFile("json/drones-n-deliveries-descriptions.json"), res);
		}

		else {
			List<Drone> drones = new ArrayList<>();
			Drone packito = new Drone("7", "Packito");
			Drone geraldo = new Drone("3", "Geraldo");
			Drone helperado = new Drone("4", "The helper");

			Delivery delivery1OfPackito = new Delivery(new DeliveryID("d1"), "-82.55N 78.79E");
			Delivery delivery2OfPackito = new Delivery(new DeliveryID("d2"), "-82.50N 77.19E");

			Delivery delivery1OfGeraldo = new Delivery(new DeliveryID("d3"), "-81.55N 75.79E");

			packito.addDelivery(delivery1OfPackito);
			packito.addDelivery(delivery2OfPackito);

			geraldo.addDelivery(delivery1OfGeraldo);

			drones.add(packito);
			drones.add(geraldo);
			drones.add(helperado);

			Map<DeliveryID, List<Drone>> altDrones = new HashMap<>();
			List<Drone> listAltDrones = new ArrayList<>();
			listAltDrones.add(helperado);
			altDrones.put(delivery2OfPackito.getID(), listAltDrones);

			GoToStep goToStep = new GoToStep("Super U", "-82.5588N 78.787E");
			Deployment deployment = new Deployment(drones, altDrones);

			DropPoint dp = new DropPoint(goToStep, deployment);
			List<DropPoint> dps = new ArrayList<>();
			dps.add(dp);

			t = new Tour(dps);
		}

		this.model = t;
		this.view = new ConsoleView(this);
		this.getGlobalTourDescription();
	}

	public void getGlobalTourDescription() {
		Map<String, Object> globalTourDescription;

		try {
			if(this.model.isFinished()){
				this.view.displayTourFinished();
			}
			else {
				globalTourDescription = this.model.getGlobalDeliveriesDescription();
				this.view.displayTourState(globalTourDescription);
			}


		} catch (IllegalAccessException e) {
			this.view.displayStartTour();
		}


	}

	public void startTour() {
		GoToStep goToStep = model.start();
		this.view.tourHasStarted(goToStep);
	}

	public Map<Drone, Delivery> getCurrentDeliveriesDescription() throws IllegalAccessException {
		return this.model.getCurrentDeliveriesDescription();
	}

	public static void main(String[] args) throws IOException {
		Controller controller = new Controller();

	}

	public void isArrivedAtLocation() {
		DropPoint dropPoint = null;
		try {
			dropPoint = this.model.truckDriverIsArrivedAtLocation();
			this.getGlobalTourDescription();
		} catch (Exception e) {
			// TODO display error
			e.printStackTrace();
		}
	}

	public void checkPair(String droneID, String packageID) {
		try {
			if(this.model.checkAssociation(new DroneID(droneID), new DeliveryID(packageID))) {
				this.view.askIfDroneGone(droneID);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			this.view.refuseAssociation();
			this.getGlobalTourDescription();
		}
	}

	public void declareDroneProblem(String droneID) {
		if(!DEMO) {
			System.out.println("DRONE PB");
		}
		this.model.droneDead(new DroneID(droneID));
		this.getGlobalTourDescription();
	}

	public void droneGone(String droneID) {
		this.model.droneGone(new DroneID(droneID));
		this.getGlobalTourDescription();
	}

	public void droneBack(String droneID) {
		try {
			this.model.droneBack(new DroneID(droneID));
			this.getGlobalTourDescription();
		}
		catch(IllegalArgumentException e) {
			this.view.displayDroneNotFound(droneID);
			this.getGlobalTourDescription();
		}
	}

}
