package app.demonstrator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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

    public void createDrone(String name){
        drawPanel.createShipper(name, new GraphicDrone(null, null, ShipperState.IDLE));
    }

    public void createTruck(String name) {
        drawPanel.createShipper(name, new GraphicTruck(null, null, ShipperState.IDLE));
    }

    public void removeShipper(String name){
        drawPanel.removeShipper(name);
    }

    public void changeShipperLocation(String name, Dimension location){
        drawPanel.changeShipperLocation(name, reel2Window(location));
    }

    public void changeShipperTargetLocation(String name, Dimension target){
        drawPanel.changeShipperTargetLocation(name, reel2Window(target));
    }

    public void changeShipperLocation(String s) {
        drawPanel.changeShipperLocation(s);
    }

    public void refresh(){
        this.repaint();
    }
}
