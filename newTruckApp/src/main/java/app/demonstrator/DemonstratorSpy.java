package app.demonstrator;

import app.Drone;
import app.Output;
import app.action.*;
import app.shipper.CompositeShipper;
import app.shipper.Shipper;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Observable;

/**
 * Created by Sébastien on 20/01/2016.
 */
public class DemonstratorSpy implements Output {

    DemonstratorWindow window;

    public DemonstratorSpy(){
        window = new DemonstratorWindow(800, 600, "Demonstrator");
    }

    @Override
    public void set(Shipper shipper) {
        if(shipper instanceof Drone) {
            window.createDrone(shipper.getName());
        }
        else {
            window.createHumanShipper(shipper.getName());
        }
    }

    @Override
    public void set(CompositeShipper shipper){
        window.createTruck(shipper.getName());
    }

    @Override
    public void update(Observable o, Object arg) {
        if(!(arg instanceof ActionEvent))
            return;

        try {
            this.getClass().getMethod("update", o.getClass(), Shipper.class, ActionEvent.class).invoke(this, o, ((Action)o).getTarget(), arg);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        window.refresh();
    }

    public void update(GoToDropPoint goD, Shipper shipper, ActionEvent event){
        if(event.equals(ActionEvent.STARTED)){
            window.changeShipperTargetLocation(shipper.getName(), goD.getLocation());
        } else {
            window.changeShipperLocation(shipper.toString());
        }
    }

    public void update(GoToShippingPosition goS, Shipper shipper, ActionEvent event){
        if(event.equals(ActionEvent.STARTED)){
            window.changeShipperTargetLocation(shipper.getName(), goS.getLocation());
        } else {
            window.changeShipperLocation(shipper.toString());
        }
    }

    public void update(Pick pick, Shipper shipper, ActionEvent event){
        if(event.equals(ActionEvent.STARTED)){
            window.changeShipperState(shipper.getName(), ShipperState.PICKING);
        } else {
            window.changeShipperState(shipper.getName(), ShipperState.IDLE);
        }
    }

    public void update(Drop drop, Shipper shipper, ActionEvent event){
        if(event.equals(ActionEvent.STARTED)){
            window.changeShipperState(shipper.getName(), ShipperState.DROPPING);
        } else {
            window.changeShipperState(shipper.getName(), ShipperState.IDLE);
        }
    }

    public void update(SendDrone send, Shipper shipper, ActionEvent event){
        if(event.equals(ActionEvent.STARTED)){
            window.changeShipperState(shipper.getName(), ShipperState.DROPPING);
            for (Object o : send.getParams()) {
                window.shipperDrop(shipper.getName(), o.toString());
            }
        }
        else {
            window.changeShipperState(shipper.getName(), ShipperState.IDLE);
        }
    }

    public void update(CollectDrone collect, Shipper shipper, ActionEvent event){
        if(event.equals(ActionEvent.STARTED)){
            window.changeShipperState(shipper.getName(), ShipperState.PICKING);
        } else {
            for (Object o : collect.getParams()) {
                window.shipperCollect(shipper.getName(), o.toString());
            }
            window.changeShipperState(shipper.getName(), ShipperState.IDLE);
        }
    }

    public void init(java.util.List<Dimension> placesToDraw) {
        window.init(placesToDraw);
    }
}
