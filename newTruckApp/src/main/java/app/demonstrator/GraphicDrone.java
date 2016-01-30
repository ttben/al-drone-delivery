package app.demonstrator;

import javax.swing.*;
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
    public List<GraphicEntity> getComposites() {
        return new ArrayList<>();
    }

    protected Color getColor(){
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

    @Override
    protected Image getIcon(ShipperState state) {
        return new ImageIcon(this.getClass().getResource("/drone-" + state.toString() + ".png")).getImage();
    }

    protected int getSize(){
        return 10;
    }

}
