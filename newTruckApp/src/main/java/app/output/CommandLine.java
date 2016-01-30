package app.output;

import app.action.*;
import app.shipper.CompositeShipper;
import app.shipper.Shipper;

import java.util.Observable;

public class CommandLine implements Output {

	/**
	 * 
	 * @param shipper
	 * @param action
	 */
	public void execute(Shipper shipper, Pick action) {
		System.out.println();
		// TODO - implement CommandLine.execute
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param shipper
	 * @param action
	 */
	public void execute(Shipper shipper, Drop action) {
		// TODO - implement CommandLine.execute
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param shipper
	 * @param action
	 */
	public void execute(Shipper shipper, GoToShippingPosition action) {
		// TODO - implement CommandLine.execute
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param shipper
	 * @param action
	 */
	public void execute(Shipper shipper, CollectDrone action) {
		// TODO - implement CommandLine.execute
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param shipper
	 * @param action
	 */
	public void execute(Shipper shipper, SendDrone action) {
		// TODO - implement CommandLine.execute
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param shipper
	 * @param action
	 */
	public void execute(Shipper shipper, GoToDropPoint action) {
		// TODO - implement CommandLine.execute
		throw new UnsupportedOperationException();
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.printf("CommandLine : Observable %s has raised an event with %s\n\n", o, arg);
	}

	@Override
	public void set(Shipper shipper) {

	}

	@Override
	public void set(CompositeShipper shipper) {

	}
}