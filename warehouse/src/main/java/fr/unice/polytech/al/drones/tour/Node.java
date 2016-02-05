package fr.unice.polytech.al.drones.tour;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 29/01/2016.
 */
public class Node {
    private String action;

    public ArrayList<String> getParams() {
        return params;
    }

    public void setParams(ArrayList<String> params) {
        this.params = params;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    private ArrayList<String> params;

    public Node(){

    }

    public Node(String action, ArrayList<String> params){
        this.action = action;
        this.params = params;
    }

    @Override
    public String toString() {
        return "action : " + action+" ,["+params.toString()+"]";
    }
}
