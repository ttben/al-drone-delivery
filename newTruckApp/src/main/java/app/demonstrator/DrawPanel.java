package app.demonstrator;

import javax.swing.*;
import java.awt.*;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sébastien on 20/01/2016.
 */
public class DrawPanel extends JPanel {

    private Map<String, Map.Entry<Dimension, STATE>> drones;


    public DrawPanel(){

        this.setBackground(Color.white);
        drones = new HashMap<>();

    }

    public void setDrone(String name, Dimension location, STATE state){
        if(location == null)
            drones.remove(name);
        else
            drones.put(name, new AbstractMap.SimpleEntry<Dimension, STATE>(location, state));
        repaint();
    }

    public void paintComponent(Graphics g) {
        removeAll();

        Graphics2D graphics2D = antiAliasing(g);

        // Drones
        for(Map.Entry<String, Map.Entry<Dimension, STATE>> drone : drones.entrySet()){
            drawDrone(drone.getKey(), drone.getValue().getKey(), drone.getValue().getValue(), graphics2D);
        }
    }

    private Graphics2D antiAliasing(Graphics g) {

        Graphics2D graphics2D = (Graphics2D) g;

        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        return graphics2D;
    }

    private void drawDrone(String name, Dimension location, STATE state, Graphics g) {
        g.setColor(Color.black);
        g.fillOval(location.width - 1, location.height - 1, state.getSize() + 2, state.getSize() + 2);
        g.setColor(state.getColor());
        g.fillOval(location.width, location.height, state.getSize(), state.getSize());
    }

}
