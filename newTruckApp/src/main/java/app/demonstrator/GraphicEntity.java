package app.demonstrator;

import javax.swing.*;
import java.awt.*;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sébastien on 22/01/2016.
 */
public abstract class GraphicEntity {

    protected Dimension actualPosition;
    protected Dimension nextPosition;
    protected ShipperState state;
    protected Map<Map.Entry<Dimension, Dimension>, Color> oldPaths;

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

    public Dimension getTargetLocation() {
        return nextPosition;
    }

    public void setState(ShipperState state) {
        this.state = state;
    }

    public void giveOldPaths(Map<Map.Entry<Dimension, Dimension>, Color> oldPaths){
        this.oldPaths = oldPaths;
    }

    public void paint(Graphics g, String name) {
        if(actualPosition == null)
            return;

        Dimension whereToDraw = actualPosition;

        if(nextPosition != null){

            Color actual = getColor();
            Color fadeOut = new Color(actual.getRed(), actual.getGreen(), actual.getBlue(), 100);

            g.drawLine(actualPosition.width + getSize()/2, actualPosition.height + getSize()/2, nextPosition.width + getSize()/2, nextPosition.height + getSize()/2);
            oldPaths.put(new AbstractMap.SimpleEntry<Dimension, Dimension>(
                            new Dimension(actualPosition.width + getSize()/2, actualPosition.height + getSize()/2),
                            new Dimension(nextPosition.width + getSize()/2, nextPosition.height + getSize()/2)),
                    fadeOut
            );

            g.setColor(fadeOut);
            g.fillOval(nextPosition.width, nextPosition.height, getSize(), getSize());

            whereToDraw = new Dimension((actualPosition.width + nextPosition.width)/2,(actualPosition.height + nextPosition.height)/2);

        }

        Image icon = getIcon(state);
        if (icon != null) {
            Dimension whereToDrawWithImageSize = new Dimension(whereToDraw.width - icon.getWidth(null)/2, whereToDraw.height - icon.getHeight(null)/2);
            g.drawImage(icon, whereToDrawWithImageSize.width, whereToDrawWithImageSize.height, null);
            Color old = g.getColor();
            g.setColor(Color.black);
            g.drawString(name, whereToDraw.width - icon.getWidth(null)/2, whereToDraw.height - icon.getHeight(null)/2);
            g.setColor(old);
        }
    }

    public abstract List<GraphicEntity> getComposites();

    protected abstract Color getColor();

    protected abstract Image getIcon(ShipperState state);

    protected abstract int getSize();
}
