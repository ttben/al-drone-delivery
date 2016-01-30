package app.modeleFactory;

import app.shipper.Drone;
import app.shipper.HumanShipper;
import app.modeleFactory.exceptions.NoNameDefinedException;
import app.modeleFactory.exceptions.NoTypeDefinedException;
import app.modeleFactory.exceptions.ShipperTypeNotDefinedException;
import app.modeleFactory.exceptions.ShippersRootElementNotFoundException;
import app.shipper.Shipper;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class ModelFactoryBuildShippersTest {
	public static final String KEY_TRUCK = "Truck";
	public static final String DRONEA_KEY = "DroneA";
	public static final String DRONEB_KEY = "DroneB";
	public static final String HUMAN_KEY = "Mr. Jones";
	public static final int NUMBER_OF_ENTITIES = 4;

	@Test
	public void aFactory_WhenBuildShipper_ShouldHaveNonEmptyResult() throws Exception {
		Map<String, Shipper> actualResult = ModelFactory.buildShippers("shippers.json");
		boolean isEmpty = actualResult.isEmpty();
		assertFalse(isEmpty);
	}

	@Test
	public void aFactory_WhenBuildShipper_ShouldHaveBuiltRightNumberOfShipper() throws Exception {
		Map<String, Shipper> actualResult = ModelFactory.buildShippers("shippers.json");

		int expectedSize = NUMBER_OF_ENTITIES;
		int actualSize = actualResult.size();

		assertEquals(expectedSize, actualSize);
	}

	@Test
	public void aFactory_WhenBuildShipper_ShouldHaveBuiltTruck() throws Exception {
		Map<String, Shipper> actualResult = ModelFactory.buildShippers("shippers.json");
		boolean containsTruck = actualResult.containsKey(KEY_TRUCK);
		assertTrue(containsTruck);
	}

	@Test
	public void aFactory_WhenBuildShipper_ShouldHaveBuiltDroneA() throws Exception {
		Map<String, Shipper> actualResult = ModelFactory.buildShippers("shippers.json");
		boolean containsDroneA = actualResult.containsKey(DRONEA_KEY);
		assertTrue(containsDroneA);
	}

	@Test
	public void aFactory_WhenBuildShipper_ShouldHaveBuiltDroneAWithProperType() throws Exception {
		Map<String, Shipper> actualResult = ModelFactory.buildShippers("shippers.json");
		Object droneA = actualResult.get(DRONEA_KEY);

		boolean droneAIsADroneObject = droneA instanceof Drone;

		assertTrue(droneAIsADroneObject);
	}

	@Test
	public void aFactory_WhenBuildShipper_ShouldHaveBuiltDroneB() throws Exception {
		Map<String, Shipper> actualResult = ModelFactory.buildShippers("shippers.json");
		boolean containsDroneB = actualResult.containsKey(DRONEB_KEY);
		assertTrue(containsDroneB);
	}

	@Test
	public void aFactory_WhenBuildShipper_ShouldHaveBuiltDroneBWithProperType() throws Exception {
		Map<String, Shipper> actualResult = ModelFactory.buildShippers("shippers.json");
		Object droneB = actualResult.get(DRONEB_KEY);

		boolean droneAIsBDroneObject = droneB instanceof Drone;

		assertTrue(droneAIsBDroneObject);
	}

	@Test
	public void aFactory_WhenBuildShipper_ShouldHaveBuiltHumanShipper() throws Exception {
		Map<String, Shipper> actualResult = ModelFactory.buildShippers("shippers.json");
		boolean containsHumanShipper = actualResult.containsKey(HUMAN_KEY);
		assertTrue(containsHumanShipper);
	}

	@Test
	public void aFactory_WhenBuildShipper_ShouldHaveBuiltHumanShipperWithProperType() throws Exception {
		Map<String, Shipper> actualResult = ModelFactory.buildShippers("shippers.json");
		Object humanShipper = actualResult.get(HUMAN_KEY);

		boolean humanShipperIsAHumanShipperObject = humanShipper instanceof HumanShipper;

		assertTrue(humanShipperIsAHumanShipperObject);
	}

	@Test(expected = ShippersRootElementNotFoundException.class)
	public void aFactory_WhenRootKeyShippersIsMalformed_ShouldThrowException() throws Exception {
		Map<String, Shipper> actualResult = ModelFactory.buildShippers("shippers-malformed-rootElement.json");
	}

	@Test(expected = ShipperTypeNotDefinedException.class)
	public void aFactory_WhenCompositeShipperKeyIsMalformed_ShouldThrowException() throws Exception {
		Map<String, Shipper> actualResult = ModelFactory.buildShippers("shippers-key-malformed-composite.json");
	}

	@Test(expected = ShipperTypeNotDefinedException.class)
	public void aFactory_WhenDroneShipperKeyIsMalformed_ShouldThrowException() throws Exception {
		Map<String, Shipper> actualResult = ModelFactory.buildShippers("shippers-key-malformed-drone.json");
	}

	@Test(expected = ShipperTypeNotDefinedException.class)
	public void aFactory_WhenHumanShipperKeyIsMalformed_ShouldThrowException() throws Exception {
		Map<String, Shipper> actualResult = ModelFactory.buildShippers("shippers-key-malformed-human.json");
	}

	@Test(expected = NoNameDefinedException.class)
	public void aFactory_WhenCompositeShipperNameIsMalformed_ShouldThrowException() throws Exception {
		Map<String, Shipper> actualResult = ModelFactory.buildShippers("shippers-name-malformed-composite.json");
	}

	@Test(expected = NoNameDefinedException.class)
	public void aFactory_WhenDroneShipperNameIsMalformed_ShouldThrowException() throws Exception {
		Map<String, Shipper> actualResult = ModelFactory.buildShippers("shippers-name-malformed-drone.json");
	}

	@Test(expected = NoNameDefinedException.class)
	public void aFactory_WhenHumanShipperNameIsMalformed_ShouldThrowException() throws Exception {
		Map<String, Shipper> actualResult = ModelFactory.buildShippers("shippers-name-malformed-human.json");
	}

	@Test(expected = NoTypeDefinedException.class)
	public void aFactory_WhenCompositeShipperTypeIsMalformed_ShouldThrowException() throws Exception {
		Map<String, Shipper> actualResult = ModelFactory.buildShippers("shippers-type-malformed-composite.json");
	}

	@Test(expected = NoTypeDefinedException.class)
	public void aFactory_WhenDroneShipperTypeIsMalformed_ShouldThrowException() throws Exception {
		Map<String, Shipper> actualResult = ModelFactory.buildShippers("shippers-type-malformed-drone.json");
	}

	@Test(expected = NoTypeDefinedException.class)
	public void aFactory_WhenHumanShipperTypeIsMalformed_ShouldThrowException() throws Exception {
		Map<String, Shipper> actualResult = ModelFactory.buildShippers("shippers-type-malformed-human.json");
	}
}
