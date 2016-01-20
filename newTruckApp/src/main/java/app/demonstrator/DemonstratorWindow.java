package app.demonstrator;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Sébastien on 20/01/2016.
 */
public class DemonstratorWindow extends JFrame{

    private int x;
    private int y;
    private DrawPanel drawPanel;

    public DemonstratorWindow(int x, int y, String name){
        super(name);

        this.x = x;
        this.y = y;
        this.drawPanel = new DrawPanel();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(x,y));
        setResizable(false);
        setVisible(true);
        getContentPane().add(drawPanel);
    }

    // Implemented with 0 to 100 inputs
    private Dimension reel2Window(Dimension reel){
        return new Dimension(reel.width * x / 100, reel.height * y / 100);
    }

    public void setDrone(String s, Dimension location, STATE flying) {
        drawPanel.setDrone(s, reel2Window(location), flying);
    }
}
