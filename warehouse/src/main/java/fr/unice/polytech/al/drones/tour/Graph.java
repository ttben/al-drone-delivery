package fr.unice.polytech.al.drones.tour;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 22/01/2016.
 */
public class Graph {

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public ArrayList<Dependency> getContent() {
        return content;
    }

    public void setContent(ArrayList<Dependency> content) {
        this.content = content;
    }

    private String root;
    private ArrayList<Dependency> content;

    public Graph(String root, ArrayList<Dependency> content){
        this.root = root;
        this.content = content;
    }

    public Graph(){

    }

    @Override
    public String toString() {
        return "root : "+root + ",content: " + content.toString();
    }
}
