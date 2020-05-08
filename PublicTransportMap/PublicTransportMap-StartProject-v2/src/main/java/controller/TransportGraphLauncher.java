package controller;

import graphalgorithms.AbstractPathSearch;
import graphalgorithms.BreadthFirstPath;
import graphalgorithms.DepthFirstPath;
import model.TransportGraph;
import model.TransportGraphB;

public class TransportGraphLauncher {

    public static void main(String[] args) {
        String[] redLine = {"red", "metro", "A", "B", "C", "D"};
        String[] blueLine = {"blue", "metro", "E", "B", "F", "G"};
        String[] greenLine = {"green", "metro", "H", "I", "C", "G", "J"};
        String[] yellowLine = {"yellow", "bus", "A", "E", "H", "D", "G", "A"};

        // TODO Use the builder to build the graph from the String array.

//        Uncomment to test the builder:
        TransportGraph transportGraph = new TransportGraph.Builder()
                .addLine(redLine).addLine(blueLine).addLine(greenLine)
                .addLine(yellowLine).buildStationSet().addLinesToStations().buildConnections().build();
        System.out.println(transportGraph);

//        Uncommented to test the DepthFirstPath algorithm
        DepthFirstPath dfpTest = new DepthFirstPath(transportGraph, "E", "J");
        dfpTest.search();
        System.out.println(dfpTest);
        dfpTest.printNodesInVisitedOrder();
        System.out.println();

//        Uncommented to test the BreadthFirstPath algorithm
        BreadthFirstPath bfsTest = new BreadthFirstPath(transportGraph, "E", "J");
        bfsTest.search();
        System.out.println(bfsTest);
        bfsTest.printNodesInVisitedOrder();
    }
}
