package app.demonstrator;

import app.Drone;
import app.demonstrator.drone.GraphicDrone;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sébastien on 20/01/2016.
 */
public class DrawPanel extends JPanel {

    private Map<String, GraphicEntity> shippers;


    public DrawPanel(){
        this.setBackground(Color.white);
        shippers = new HashMap<>();
    }

    public void paint(Graphics g) {

        Graphics graphics2D = antiAliasing(g);

        for(Map.Entry<String, GraphicEntity> entry : shippers.entrySet())
            entry.getValue().paint(graphics2D, entry.getKey());
    }

    private Graphics antiAliasing(Graphics g) {

        Graphics2D graphics2D = (Graphics2D) g;

        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        return graphics2D;
    }

    public void removeShipper(String name) {
        shippers.remove(name);
    }

    public void changeShipperLocation(String name, Dimension location) {
        shippers.get(name).setLocation(location);
    }

    public void changeShipperTargetLocation(String name, Dimension target) {
        shippers.get(name).setTargetLocation(target);
    }

    public void setDroneState(Drone drone, ShipperState state){
        ((GraphicDrone) shippers.get(drone.getName())).setState(state);
    }

    public void createShipper(String name, GraphicDrone graphicDrone) {
        shippers.put(name, graphicDrone);
    }

    public void changeShipperLocation(String s) {
        GraphicEntity ge = shippers.get(s);
        if(ge.getTargetLocation() != null){
            ge.setLocation(ge.getTargetLocation());
            ge.setTargetLocation(null);
        }
    }
}
