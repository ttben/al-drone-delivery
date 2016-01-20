package app.demonstrator;

import java.awt.*;

/**
 * Created by Sébastien on 20/01/2016.
 */
public enum STATE {
    IDLE(10, new Color(80,80,140,255)),
    FLYING(10, new Color(80,140,140,255)),
    DROPPING(10, new Color(180,100,80,255)),
    PICKING(10, new Color(180,80,100,255))
    ;

    private final int size;
    private final Color color;

    private STATE(int size, Color color) {
        this.size = size;
        this.color = color;
    }

    public int getSize() {
        return size;
    }
    public Color getColor(){
        return color;
    }
}
