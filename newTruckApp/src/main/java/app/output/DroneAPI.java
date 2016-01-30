package app.output;

import app.action.Drop;
import app.action.GoToShippingPosition;
import app.action.Pick;
import app.shipper.CompositeShipper;
import app.shipper.Shipper;

import java.util.Observable;

public class DroneAPI implements Output {

	/**
	 * 
	 * @param shipper
	 * @param action
	 */
	public void execute(Shipper shipper, Pick action) {
		// TODO - implement DroneAPI.execute
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param shipper
	 * @param action
	 */
	public void execute(Shipper shipper, Drop action) {
		// TODO - implement DroneAPI.execute
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param shipper
	 * @param action
	 */
	public void execute(Shipper shipper, GoToShippingPosition action) {
		// TODO - implement DroneAPI.execute
		throw new UnsupportedOperationException();
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.printf("DroneAPI : Observable %s has raised an event with %s\n\n", o, arg);
	}

	@Override
	public void set(Shipper shipper) {

	}

	@Override
	public void set(CompositeShipper shipper) {

	}
}