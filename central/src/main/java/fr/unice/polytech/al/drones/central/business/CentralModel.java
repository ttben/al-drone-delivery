package fr.unice.polytech.al.drones.central.business;

/**
 * Created by sebastien on 11/11/15.
 */
public class CentralModel {

    private static CentralModel m;
    private WarehouseChooser w;

    public CentralModel() {
        w = new WarehouseChooser();
    }

    public CentralModel getInstance(){
        if(m == null)
            m = new CentralModel();
        return m;
    }


}
