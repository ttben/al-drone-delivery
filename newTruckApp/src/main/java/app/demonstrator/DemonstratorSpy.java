package app.demonstrator;

import app.Drone;
import app.Output;
import app.action.*;
import app.shipper.CompositeShipper;
import app.shipper.Shipper;

import java.awt.*;
import java.util.List;
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
        window.createDrone(shipper.toString());
    }

    @Override
    public void set(CompositeShipper shipper){
        window.createTruck(shipper.toString());
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("O : " + o);
        System.out.println("ARG : " + arg);
        if((arg instanceof ActionEvent) && arg.equals(ActionEvent.ENDED)) {
        try {
            this.getClass().getMethod("update", o.getClass(), Shipper.class).invoke(this, o, ((Action)o).getTarget());
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            //e.printStackTrace();
        }
        window.refresh();
        }
    }

    public void update(GoToDropPoint goD, Shipper shipper){
        logNotImplementedMethod(goD, shipper);
        //window.changeShipperTargetLocation(shipper.toString(), goD.getLocation());
    }
    public void update(GoToShippingPosition goS, Shipper shipper){
        logNotImplementedMethod(goS, shipper);
        //window.changeShipperTargetLocation(shipper.toString(), goS.getLocation());
    }
    public void update(Pick pick, Shipper shipper){
        logNotImplementedMethod(pick, shipper);
        //window.changeShipperLocation(shipper.toString());
    }
    public void update(Drop drop, Shipper shipper){
        logNotImplementedMethod(drop, shipper);
        //window.changeShipperLocation(shipper.toString());
    }
    public void update(SendDrone send, Shipper shipper){
        logNotImplementedMethod(send, shipper);
    }
    public void update(CollectDrone collect, Shipper shipper){
        logNotImplementedMethod(collect, shipper);
        //window.changeShipperLocation(shipper.toString());
    }

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
