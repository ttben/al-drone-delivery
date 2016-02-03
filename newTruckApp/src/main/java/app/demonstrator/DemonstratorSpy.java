package app.demonstrator;

import app.shipper.*;
import app.output.Output;
import app.action.*;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Observable;

/**
 * Created by Sébastien on 20/01/2016.
 */
public class DemonstratorSpy implements Output {

    DemonstratorWindow window;

    public DemonstratorSpy(){
        window = new DemonstratorWindow(1024, 768, "Demonstrator");
    }


    @Override
    public void register(CompositeShipper shipper){
        window.createTruck(shipper.getName());
    }

    public void register(Drone shipper) {
        window.createDrone(shipper.getName());
    }

    public void register(HumanShipper shipper) {
        window.createHumanShipper(shipper.getName());
    }

        @Override
    public void register(BasicShipper shipper) {

        if(shipper instanceof Drone) {
            window.createDrone(shipper.getName());
        }
        else if (shipper instanceof HumanShipper) {
            window.createHumanShipper(shipper.getName());
        } else {
            System.out.println("Oups LOL !");
        }
     }

    @Override
    public void register(Shipper shipper){
        System.out.println("Oups registering a thing not basic nor composite !");
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

    public void update(Collect collect, Shipper shipper, ActionEvent event){
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
