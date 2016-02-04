package app;

import app.action.Action;
import app.shipper.Shipper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Map;

import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class AppTest {
	public static final String PATH_TO_TOUR_JSON_FILE = "tour-test-app.json";
	public static final String PATH_TO_SHIPPERS_JSON_FILE = "shippers-app-test.json";
	public static final String TRUCK_KEY = "Truck";
	public static final String HUMAN_KEY = "Human";
	public static final String DRONE_B_KEY = "DroneB";
	public static final String DRONE_A_KEY = "DroneA";

	private App anApp;

	@Mock
	private Action anInternalAction;

	@Before
	public void setUp() throws Exception {
		anApp = new App(PATH_TO_TOUR_JSON_FILE, PATH_TO_SHIPPERS_JSON_FILE);
		anApp.root.queueAction();
	}

	@Test
	public void anApp_WhenPlayingABasicScenario_ShouldHandleItToTheEnd() {
		Map<String, Shipper> shipperMap = anApp.getShipperMap();
		Shipper truck = shipperMap.get(TRUCK_KEY);
		Shipper droneA = shipperMap.get(DRONE_A_KEY);
		Shipper droneB = shipperMap.get(DRONE_B_KEY);
		Shipper humanShipper = shipperMap.get(HUMAN_KEY);

		/*
			Handle following sequence
			 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20
			 t t t t t a a b b  a  b  t  h  h  h  t  t  t  t  t
		*/

		truck.endAction();        //	Truck is leaving warehouse
		truck.endAction();        //	Truck arrived at dropPoint
		truck.endAction();        //	Truck is sending droneA : droneA is flying
		truck.endAction();        //	Truck is sending droneB : droneB is flying
		truck.endAction();        //	Truck is at another dropPoint

		droneA.endAction();        //	droneA is dropping package
		droneA.endAction();        //	droneA is going to collecting location

		droneB.endAction();        //	droneB is dropping package
		droneB.endAction();        //	droneA is going to collecting location

		droneA.endAction();        //	droneA is waiting at collection location
		assertNull(droneA.getCurrentAction());

		droneB.endAction();        //	droneB is waiting at collection location
		assertNull(droneB.getCurrentAction());

		truck.endAction();            //	Truck is sending Human
		humanShipper.endAction();    //	Human is going to Mrs Michu's house
		humanShipper.endAction();    //	Human is dropping package
		humanShipper.endAction();    //	Human is going back to the truck
		truck.endAction();         //	Truck is collecting human : now going to collecting point

		truck.endAction();        //	Truck is collecting droneA
		truck.endAction();        //	Truck is collecting droneB
		truck.endAction();        //	Truck is going to warehouse
		truck.endAction();        //	Truck is going to garage

		assertNull(truck.getCurrentAction());
	}

}
