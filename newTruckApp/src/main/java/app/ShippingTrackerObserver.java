package app;

import app.action.Action;
import app.action.ActionEvent;
import app.action.Drop;
import app.shipper.BasicShipper;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Benjamin on 03/02/2016.
 */
public class ShippingTrackerObserver implements Observer {
	List<String> achievedDeliveries = new ArrayList<>();

	@Override
	public void update(Observable o, Object arg) {
		ActionEvent actionEvent = (ActionEvent) arg;

		if(o instanceof Drop && actionEvent.equals(ActionEvent.ENDED)) {

			Drop action = (Drop) o;
			System.out.printf("\n\t[DELIVERY DONE] %s has performed package %s\n", action.getTarget().getName(), action.getTarget().getPack());
			
		}
	}
}
