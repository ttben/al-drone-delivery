package fr.unice.polytech.si5.al.projet.s3.truck;


import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * @author Etienne Strobbe (31/10/2015).
 */
public class Node {

    private LinkedList<Node> children;
    private String name;
    protected ListIterator<Node> currentIterator;
    protected Node current;
    private boolean done;

    public Node(String name, List<Node> packages) {
        this.name = name;
        this.children = new LinkedList<>(packages);
        done = false;
        currentIterator = this.children.listIterator();
    }

    public boolean isDone(){
        return this.done;
    }

    protected void done(){
        this.done = true;
    }

    public void doNext(){
        // first pass
        if(current == null){
            if(currentIterator.hasNext()){
                current = currentIterator.next();
                current.doNext();
            }
        }

        // others passes
        else {
            if(!current.isDone()){
                if(!current.currentIterator.hasNext()){
                    current.done();
                    System.out.println("Fin de "+current.getName());
                }
                else {
                    current.doNext();
                }
            }
            else {
                if(currentIterator.hasNext()){
                    current = currentIterator.next();
                    current.doNext();
                }
                else {
                    this.done();
                    System.out.println("Fin de "+getName());
                }
            }
        }

    }


    public String getName(){
        return this.name;
    }
}
