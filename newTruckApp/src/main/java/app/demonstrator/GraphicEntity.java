package app.demonstrator;

import java.awt.*;

/**
 * Created by Sébastien on 22/01/2016.
 */
public abstract class GraphicEntity {

    protected Dimension actualPosition;
    protected Dimension nextPosition;

    public GraphicEntity(Dimension actualPosition, Dimension nextPosition) {
        this.nextPosition = nextPosition;
        this.actualPosition = actualPosition;
    }

    abstract public void paint(Graphics g, String name);

    public void setLocation(Dimension location) {
        this.actualPosition = location;
    }

    public void setTargetLocation(Dimension location){
        this.nextPosition = location;
    }

    public Dimension getTargetLocation() {
        return nextPosition;
    }
}
