package app.demonstrator;

import java.awt.*;
import java.util.List;

/**
 * Created by Sébastien on 22/01/2016.
 */
public abstract class GraphicEntity {

    protected Dimension actualPosition;
    protected Dimension nextPosition;
    protected ShipperState state;

    public GraphicEntity(Dimension actualPosition, Dimension nextPosition, ShipperState state) {
        this.nextPosition = nextPosition;
        this.actualPosition = actualPosition;
        this.state = state;
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

    public void setState(ShipperState state) {
        this.state = state;
    }

    public abstract List<GraphicEntity> getComposites();

    @Override
    public String toString() {
        return "\n ################ GraphicEntity{" +
                "actualPosition=" + actualPosition +
                ", nextPosition=" + nextPosition +
                ", state=" + state +
                '}';
    }
}
