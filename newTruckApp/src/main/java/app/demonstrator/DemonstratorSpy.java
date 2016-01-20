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
    public void update(Observable o, Object arg) {
        try {
            CompositeShipper sh = new CompositeShipper();
            this.getClass().getMethod("update", o.getClass(), Shipper.class).invoke(this, o, arg);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void update(CollectDrone collect, Shipper shipper){
        logNotImplementedMethod(collect, shipper);
    }
    public void update(Drop drop, Shipper shipper){
        logNotImplementedMethod(drop, shipper);
    }
    public void update(GoToDropPoint goD, Shipper shipper){
        logNotImplementedMethod(goD, shipper);
    }
    public void update(GoToShippingPosition goS, Shipper shipper){

        window.setDrone(shipper.toString(), goS.getLocation(), STATE.FLYING);
        logNotImplementedMethod(goS, shipper);
    }
    public void update(Pick pick, Shipper shipper){
        logNotImplementedMethod(pick, shipper);
    }
    public void update(SendDrone send, Shipper shipper){
        logNotImplementedMethod(send, shipper);
    }

    private void logNotImplementedMethod(Action a, Shipper s){
        String lastMethodCalled = Thread.currentThread().getStackTrace()[2].getMethodName();
        System.out.println("/!\\ TO IMPLEMENT : " + lastMethodCalled
            + " with " + a.getClass() + " on " + s.getClass());
    }
}
