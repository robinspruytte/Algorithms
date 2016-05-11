package algorithms.HoehelKoarten;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.algorithm.Dijkstra;

import java.util.Scanner;

/**
 * This program calculates the easiest route between two cities, based on defined roads.
 * Roads are dynamic and will change while advancing (accidents, more traffic), which can cause a new best route.
 *
 * This program is mostly made with the Graphstream libraries.
 * More info on http://graphstream-project.org/
 */

/**
 * Created by Robin on 2/05/2016.
 */


public class BelgiumHighways {
    //only attribute is the graph which will be used to make the nodes (cities) and to connect them (roads).
    private Graph graph;

    /**
     * Creates a graph with autocreate settings, which means that unknown nodes will be automatically made by the program.
     * This means we wont need to make the nodes ourselves.
     *
     * Also, by using the method createRoadBetween(), we create our desired graph.
     *
     * Finally, a label is added to all nodes to make their Id visible on the graph.
     */
    public BelgiumHighways() {
        graph = new SingleGraph("Belgium Highways");
        graph.setStrict(false);
        graph.setAutoCreate(true);

        this.createRoadBetween("Brugge", "Kortrijk", 56);
        this.createRoadBetween("Brugge", "Gent", 50);
        this.createRoadBetween("Brugge", "Antwerpen", 95);
        this.createRoadBetween("Kortrijk", "Bergen", 83);
        this.createRoadBetween("Gent", "Antwerpen", 60);
        this.createRoadBetween("Gent", "Brussel", 50);
        this.createRoadBetween("Antwerpen", "Brussel", 44);
        this.createRoadBetween("Antwerpen", "Hasselt", 80 );
        this.createRoadBetween("Brussel", "Bergen", 78);
        this.createRoadBetween("Brussel", "Waver", 30);
        this.createRoadBetween("Brussel", "Leuven", 30);
        this.createRoadBetween("Bergen", "Namen", 75);
        this.createRoadBetween("Waver", "Namen", 40);
        this.createRoadBetween("Leuven", "Hasselt", 59);
        this.createRoadBetween("Leuven", "Luik", 82);
        this.createRoadBetween("Hasselt", "Luik", 53);
        this.createRoadBetween("Luik", "Namen", 65);
        this.createRoadBetween("Luik", "Neufchateau", 110);
        this.createRoadBetween("Namen", "Neufchateau", 90);
        this.createRoadBetween("Neufchateau", "Aarlen", 37 );

        for (Node node : graph) {
            node.addAttribute("label", node.getId());
        }
    }

    /**
     * Creates a road (edge) between 2 cities (nodes).
     * We dont need to create the nodes because of autocreate settings.
     * Every road contains 2 unidirected edges. One for both directions.
     * Roads get a few attributes:
     * "weight", which refers to how busy a road is.
     * "label", which makes the weight visible on the graph.
     * "accident", which checks if an accident has already happened on this road or not? (see checkForAccidents())
     * @param cityA Starting position
     * @param cityB Destination
     * @param weight weight of the road. Counts for both directions.
     */
    public void createRoadBetween(String cityA , String cityB, int weight) {
        graph.addEdge(cityA + "-" + cityB, cityA, cityB, true);
        graph.getEdge(cityA + "-" + cityB).addAttribute("weight", weight);
        graph.getEdge(cityA + "-" + cityB).addAttribute("label", "" + weight);
        graph.getEdge(cityA + "-" + cityB).addAttribute("accident", false);

        graph.addEdge(cityB + "-" + cityA, cityB, cityA, true);
        graph.getEdge(cityB + "-" + cityA).addAttribute("weight", weight);
        graph.getEdge(cityB + "-" + cityA).addAttribute("label", "" + weight);
        graph.getEdge(cityB + "-" + cityA).addAttribute("accident", false);
    }

    /**
     * Simulates the situation where roads get busier while time goes on.
     * Every road has 1/4 chance to get it's weight increased by one.
     */
    public void add1kCarsOnRandomRoads() {
        for (Edge e : graph.getEachEdge()) {
            int i = (int) (Math.random() * 4);
            if(i >= 3) {
                int newWeight = e.getAttribute("weight") ;
                newWeight++;
                System.out.println("1000 cars added on road " + e.getId());
                e.setAttribute("weight", newWeight);
            }
        }
    }

    /**
     * Using the attribute "accident", this method simulates accidents.
     * If an accident happens for the first time, then "weight" is increased by 30. Also, the attribute "accident" becomes true
     * If it's not the first accident on that road, then "weight" is increased by 50
     * Chances of an accident happening are calculated using "weight", in which 1 weight is equal to 0.1% chance.
     */
    public void checkForAccidents() {
        for (Edge e : graph.getEachEdge()) {
            int chance = e.getAttribute("weight");
            double chance2 = ((double) chance )/ 1000;
            if ( (Math.random() + chance2) > 1.0) {
                System.out.println("An accident has happened on the road " + e.getId());
                if (e.getAttribute("accident") == false) {
                    int newWeight = e.getAttribute("weight");
                    newWeight = newWeight + 30;
                    e.setAttribute("weight", newWeight);
                    e.setAttribute("accident", true);
                }
                if (e.getAttribute("accident") == true) {
                    int newWeight = e.getAttribute("weight");
                    newWeight = newWeight + 50;
                    e.setAttribute("weight", newWeight);
                }
            }
        }
    }

    /**
     * Simulates a step in time.
     * Roads get busier and accidents may have happened.
     */
    public void updateMap() {
        this.add1kCarsOnRandomRoads();
        this.checkForAccidents();
        for (Edge e : graph.getEachEdge()) {
            e.setAttribute("label", "" + e.getAttribute("weight"));
        }
    }

    /**
     * Returns the graph
     * @return graph
     */
    public Graph getGraph() {
        return graph;
    }

    /**
     * Application for program above.
     * Buttun a and b are used to set starting point and destination.
     * When both are filled in, c can be pressed to calculate the shortest route.
     * At any given moment, you can update the map, which will check for accidents and recalculate best route if needed.
     * @param args
     */
    public static void main(String[] args) {
        BelgiumHighways main = new BelgiumHighways();
        String cityA = null;
        String cityB = null;

        boolean setCityA = false;
        boolean setCityB = false;

        System.setProperty("gs.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        main.getGraph().addAttribute("ui.quality");
        main.getGraph().addAttribute("ui.antialias");
        main.getGraph().addAttribute("ui.stylesheet", "edge {shape: cubic-curve;}");
        main.getGraph().display();
        Dijkstra dijkstra = new Dijkstra(Dijkstra.Element.EDGE, null, "weight");

        System.out.println("Press a to set your starting point");
        System.out.println("Press b to set your destination");
        System.out.println("Press c to calculate easiest route");
        System.out.println("Accidents may appear and density on roads may change");
        System.out.println("You can press u to update and recalculate your route");
        System.out.println("Choose from following cities: Brugge, Kortrijk, Gent, Bergen, Brussel,");
        System.out.println("Antwerpen, Leuven, Namen, Waver, Hasselt, Luik, Neufchateau, Aarlen");
        Scanner scanner = new Scanner(System.in);

        while(true) {
            String command = scanner.next();

            if( command.equals("a") ) {
                System.out.println("What is your starting point");
                setCityA = true;
            }

            if( setCityA && !command.equals("a") ) {
                cityA = command;
                setCityA = false;
            }

            if( command.equals("b") ) {
                System.out.println("What is your destination");
                setCityB = true;
            }

            if( setCityB && !command.equals("b")) {
                cityB = command;
                setCityB = false;
            }

            if( (command.equals("u") || command.equals("c")) && (cityA == null || cityB == null )) {
                System.out.println("Please fill in both starting point and destination");
            }

            if( command.equals("c") && cityA != null && cityB != null ) {
                dijkstra.init(main.getGraph());
                dijkstra.setSource(main.getGraph().getNode(cityA));
                dijkstra.compute();
                System.out.println("" + dijkstra.getPath(main.getGraph().getNode(cityB)));
            }

            if( command.equals("u")) {
                main.updateMap();
                System.out.println(dijkstra.getPath(main.getGraph().getNode(cityB)));
            }
        }
    }
}
