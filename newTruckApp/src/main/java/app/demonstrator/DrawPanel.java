package app.demonstrator;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sébastien on 20/01/2016.
 */
public class DrawPanel extends JPanel {

    private static final boolean drawOldLines = true;

    private Map<String, GraphicEntity> shippers;
    private Map<Map.Entry<Dimension, Dimension>, Color> oldPaths;

    public DrawPanel(){
        shippers = new HashMap<>();
        oldPaths = new HashMap<>();
        this.setOpaque(true);
        this.setBackground(Color.white);
    }

    public void createShipper(String name, GraphicEntity graphic) {
        shippers.put(name, graphic);
        graphic.giveOldPaths(oldPaths);
    }

    public void removeShipper(String name) {
        shippers.remove(name);
    }

    public void setShipperState(String name, ShipperState state){
        shippers.get(name).setState(state);
    }

    public void changeShipperTargetLocation(String name, Dimension target) {
        shippers.get(name).setTargetLocation(target);
        setShipperState(name, ShipperState.MOVING);
    }

    public void changeShipperLocation(String name) {
        GraphicEntity ge = shippers.get(name);
        if(ge.getTargetLocation() != null){
            ge.setLocation(ge.getTargetLocation());
            ge.setTargetLocation(null);
        }
        setShipperState(name, ShipperState.IDLE);
    }

    GraphicEntity getShipper(String name) {
        return shippers.get(name);
    }

    public void paint(Graphics g) {

        super.paintComponent(g);

        Graphics graphics2D = antiAliasing(g);

        // Draw old traces
        if(drawOldLines) {
            for (Map.Entry<Map.Entry<Dimension, Dimension>, Color> entry : oldPaths.entrySet()) {
                Dimension first = entry.getKey().getKey();
                Dimension second = entry.getKey().getValue();
                Color color = entry.getValue();

                g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha() / 2));
                g.drawLine(first.width, first.height, second.width, second.height);
            }
        }

        // Draw entities
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
}
