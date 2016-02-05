package fr.unice.polytech.al.drones.tour;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 06/11/2015.
 */
public class Tour {

    public ArrayList<NodeParent> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<NodeParent> nodes) {
        this.nodes = nodes;
    }

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    private ArrayList<NodeParent> nodes;
    private Graph graph;

    public Tour(ArrayList<NodeParent> node, Graph graph){
        this.nodes = node;
        this.graph = graph;
    }

    public Tour(){

    }
    @Override
    public String toString() {
        String res = "";
        for (NodeParent n : nodes){
            res += n.toString();
        }
        return res + "," + graph.toString();
    }
}
