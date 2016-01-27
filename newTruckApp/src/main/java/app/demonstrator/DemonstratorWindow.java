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
        if(reel == null) return null;
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

    public void changeShipperTargetLocation(String name, Dimension target){
        drawPanel.changeShipperTargetLocation(name, reel2Window(target));
    }

    public void changeShipperLocation(String s) {
        drawPanel.changeShipperLocation(s);
    }

    public void changeShipperState(String name, ShipperState state){
        drawPanel.setShipperState(name, state);
    }

    public void shipperDrop(String composite, String name) {
        GraphicTruck shipper = (GraphicTruck) drawPanel.getShipper(composite);
        shipper.removeComposite(name);
        shipper.addQuitComposite(drawPanel.getShipper(name));
        drawPanel.getShipper(name).setLocation(shipper.getActualPosition());
    }

    public void shipperCollect(String composite, String name) {
        GraphicTruck shipper = (GraphicTruck) drawPanel.getShipper(composite);
        shipper.removeQuitComposite(name);
        shipper.addComposite(drawPanel.getShipper(name));
        drawPanel.getShipper(name).setLocation(null);
    }

    public void refresh(){
        this.repaint();
    }
}
