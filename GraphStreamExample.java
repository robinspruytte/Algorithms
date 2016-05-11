package algorithms;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

/**
 * Created by Robin on 25/04/2016.
 */
public class GraphStreamExample {

    public static void main(String[] args) {
    	Graph g = new SingleGraph("Graph example");
    	g.addNode("A");
    	g.addNode("B");
    	g.addNode("C");
    	g.addEdge("AB", "A", "B");
    	g.addEdge("BC", "B", "C");
    	g.addEdge("AC", "A", "C");
    	g.display();
    }
}
