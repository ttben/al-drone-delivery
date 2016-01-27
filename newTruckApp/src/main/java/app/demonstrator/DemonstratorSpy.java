package app.demonstrator;

import app.Output;
import app.action.*;
import app.shipper.CompositeShipper;
import app.shipper.Shipper;

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
        window.createDrone(shipper.getName());
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
        logNotImplementedMethod(goS, shipper);
        //window.changeShipperTargetLocation(shipper.getName(), goS.getLocation());
    }
    public void update(Pick pick, Shipper shipper, ActionEvent event){
        logNotImplementedMethod(pick, shipper);
        //window.changeShipperLocation(shipper.getName());
    }
    public void update(Drop drop, Shipper shipper, ActionEvent event){
        logNotImplementedMethod(drop, shipper);
        //window.changeShipperLocation(shipper.getName());
    }
    public void update(SendDrone send, Shipper shipper, ActionEvent event){
        logNotImplementedMethod(send, shipper);
    }
    public void update(CollectDrone collect, Shipper shipper, ActionEvent event){
        logNotImplementedMethod(collect, shipper);
        //window.changeShipperLocation(shipper.getName());
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
