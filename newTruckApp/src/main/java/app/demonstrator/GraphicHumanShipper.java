package app.demonstrator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sébastien on 22/01/2016.
 */
public class GraphicHumanShipper extends GraphicEntity {

    public GraphicHumanShipper(Dimension actualPosition, Dimension nextPosition, ShipperState state) {
        super(actualPosition, nextPosition, state);
    }

    @Override
    public List<GraphicEntity> getComposites() {
        return new ArrayList<>();
    }

    protected Color getColor(){
        switch (state){
            case IDLE:
                return new Color(178, 91, 78,255);
            case MOVING:
                return new Color(195, 46, 46,255);
            case DROPPING:
                return new Color(243, 8, 21,255);
            case PICKING:
                return new Color(243, 8, 21,255);
        }
        return Color.black;
    }

    @Override
    protected Image getIcon(ShipperState state) {
        return new ImageIcon(this.getClass().getResource("/worker-" + state.toString() + ".png")).getImage();
    }

    protected int getSize(){
        return 10;
    }

}
