package app.demonstrator;

import app.Drone;
import app.demonstrator.GraphicEntity;
import app.demonstrator.ShipperState;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Sébastien on 22/01/2016.
 */
public class GraphicDrone extends GraphicEntity {

    public GraphicDrone(Dimension actualPosition, Dimension nextPosition, ShipperState state) {
        super(actualPosition, nextPosition, state);
    }

    @Override
    public void paint(Graphics g, String name) {
        if(actualPosition == null) return;

        g.setColor(Color.black);
        g.fillOval(actualPosition.width - 1, actualPosition.height - 1, getSize() + 2, getSize() + 2);
        g.setColor(getColor());
        g.fillOval(actualPosition.width, actualPosition.height, getSize(), getSize());

        if(nextPosition != null){
            g.drawLine(actualPosition.width, actualPosition.height, nextPosition.width, nextPosition.height);

            Color actual = getColor();
            Color fadeOut = new Color(actual.getRed(), actual.getGreen(), actual.getBlue(), 100);
            g.setColor(fadeOut);
            g.fillOval(nextPosition.width, nextPosition.height, getSize(), getSize());
        }
    }

    @Override
    public List<GraphicEntity> getComposites() {
        return new ArrayList<>();
    }

    private Color getColor(){
        switch (state){
            case IDLE:
                return new Color(80,80,140,255);
            case MOVING:
                return new Color(80,140,140,255);
            case DROPPING:
                return new Color(110,140,120,255);
            case PICKING:
                return new Color(120,140,120,255);
        }
        return Color.black;
    }

    private int getSize(){
        return 10;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
