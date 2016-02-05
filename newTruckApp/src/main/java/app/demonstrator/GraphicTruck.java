package app.demonstrator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Sébastien on 22/01/2016.
 */
public class GraphicTruck extends GraphicEntity{

    private List<GraphicEntity> composites;
    private List<GraphicEntity> quitComposites;

    public GraphicTruck(Dimension location, Dimension nextLocation, ShipperState state) {
        super(location, nextLocation, state);
        composites = new ArrayList<>();
        quitComposites = new ArrayList<>();
    }

    public void addComposite(GraphicEntity composite){
        composites.add(composite);
    }

    public void removeComposite(String name) {
        for (int i = 0; i < composites.size(); i++) {
            if (composites.get(i).toString().equals(name))
                composites.remove(i);
        }
    }

    public void addQuitComposite(GraphicEntity entity) {
        quitComposites.add(entity);
    }
    public void removeQuitComposite(String name) {
        for (int i = 0; i < quitComposites.size(); i++) {
            if (quitComposites.get(i).toString().equals(name))
                quitComposites.remove(i);
        }
    }

    @Override
    public List<GraphicEntity> getComposites() {
        return composites;
    }

    protected Image getIcon(ShipperState state) {
        return new ImageIcon(this.getClass().getResource("/truck-" + state.toString() + ".png")).getImage();
    }

    protected Color getColor(){
        switch (state){
            case IDLE:
                return new Color(151, 163, 53,255);
            case MOVING:
                return new Color(200, 181, 72,255);
            case DROPPING:
                return new Color(225, 220, 0,255);
            case PICKING:
                return new Color(225, 220, 0,255);
        }
        return Color.black;
    }

    protected int getSize(){
        return 10;
    }


    public Dimension getActualPosition() {
        return actualPosition;
    }

}
