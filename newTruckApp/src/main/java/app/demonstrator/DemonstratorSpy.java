package app.demonstrator;

import app.Drone;
import app.Output;
import app.action.*;
import app.demonstrator.drone.GraphicDrone;
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
    public void update(Observable o, Object arg) {
        try {
            CompositeShipper sh = new CompositeShipper();
            this.getClass().getMethod("update", o.getClass(), Shipper.class).invoke(this, o, arg);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        window.refresh();
    }

    public void createDrone(Drone drone, Dimension location){
        window.createDrone(drone.toString(), location);
        window.refresh();
    }

    public void update(CollectDrone collect, Shipper shipper){
        window.changeShipperLocation(shipper.toString());
    }
    public void update(Drop drop, Shipper shipper){
        window.changeShipperLocation(shipper.toString());
    }
    public void update(GoToDropPoint goD, Shipper shipper){
        window.changeShipperTargetLocation(shipper.toString(), goD.getLocation());
    }
    public void update(GoToShippingPosition goS, Shipper shipper){
        window.changeShipperTargetLocation(shipper.toString(), goS.getLocation());
    }
    public void update(Pick pick, Shipper shipper){
        window.changeShipperLocation(shipper.toString());
    }
    public void update(SendDrone send, Shipper shipper){logNotImplementedMethod(send, shipper);}

    private void logNotImplementedMethod(Action a, Shipper s){
        String lastMethodCalled = Thread.currentThread().getStackTrace()[2].getMethodName();
        System.out.println("/!\\ TO IMPLEMENT : " + lastMethodCalled
            + " with " + a.getClass() + " on " + s.getClass());
    }

    public void removeShipper(String s) {
        window.removeShipper(s);
        window.refresh();
    }
}
