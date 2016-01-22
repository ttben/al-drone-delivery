package app.demonstrator.drone;

import app.Drone;
import app.demonstrator.GraphicEntity;
import app.demonstrator.ShipperState;

import java.awt.*;

/**
 * Created by Sébastien on 22/01/2016.
 */
public class GraphicDrone extends GraphicEntity {

    private ShipperState state;

    public GraphicDrone(Dimension actualPosition, Dimension nextPosition, ShipperState state) {
        super(actualPosition, nextPosition);
        this.state = state;
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

    private Color getColor(){
        switch (state){
            case IDLE:
                return new Color(80,80,140,255);
            case MOVING:
                return new Color(80,140,140,255);
            case DROPPING:
                return new Color(180,100,80,255);
            case PICKING:
                return new Color(180,80,100,255);
        }
        return Color.black;
    }

    private int getSize(){
        return 10;
    }


    public void setState(ShipperState state) {
        this.state = state;
    }
}
