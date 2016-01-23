package app.demonstrator;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Sébastien on 22/01/2016.
 */
public class GraphicTruck extends GraphicEntity{

    private List<>

    public GraphicTruck(Dimension location, Dimension nextLocation, ShipperState state, List<String> shippers) {
        super(location, nextLocation, state);
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
                return new Color(20,100,80,255);
            case MOVING:
                return new Color(40,120,100,255);
            case DROPPING:
                return new Color(80,140,100,255);
            case PICKING:
                return new Color(90,150,100,255);
        }
        return Color.black;
    }

    private int getSize(){
        return 25;
    }
}
