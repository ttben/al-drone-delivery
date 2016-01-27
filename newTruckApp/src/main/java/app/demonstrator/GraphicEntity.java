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

    public void setLocation(Dimension location) {
        this.actualPosition = location;
    }

    public void setTargetLocation(Dimension location){
        this.nextPosition = location;
    }

    public void paint(Graphics g, String name) {
        if(actualPosition == null)
            return;

        Dimension whereToDraw = actualPosition;

        if(nextPosition != null){
            g.drawLine(actualPosition.width + getSize()/2, actualPosition.height + getSize()/2, nextPosition.width + getSize()/2, nextPosition.height + getSize()/2);

            Color actual = getColor();
            Color fadeOut = new Color(actual.getRed(), actual.getGreen(), actual.getBlue(), 100);
            g.setColor(fadeOut);
            g.fillOval(nextPosition.width, nextPosition.height, getSize(), getSize());

            whereToDraw = new Dimension((actualPosition.width + nextPosition.width)/2,(actualPosition.height + nextPosition.height)/2);

        }

        Image icon = getIcon(state);
        if (icon != null) {
            Dimension whereToDrawWithImageSize = new Dimension(whereToDraw.width - icon.getWidth(null)/2, whereToDraw.height - icon.getHeight(null)/2);
            g.drawImage(icon, whereToDrawWithImageSize.width, whereToDrawWithImageSize.height, null);
        }
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

    protected abstract Color getColor();

    protected abstract Image getIcon(ShipperState state);

    protected abstract int getSize();
}
