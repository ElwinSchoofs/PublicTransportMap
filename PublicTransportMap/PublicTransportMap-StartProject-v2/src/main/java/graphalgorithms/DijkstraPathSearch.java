package graphalgorithms;

import model.Connection;
import model.Station;
import model.TransportGraphB;

import java.util.ArrayList;

public class DijkstraPathSearch {

    private ArrayList<Station> visited;
    private ArrayList<Station> nonVisited;
    private ArrayList<Station> solutionPath;
    Station startStation, goalStation, currentStation;
    TransportGraphB graph;

    public DijkstraPathSearch(TransportGraphB graph, Station startStation, Station goalStation) {
        this.graph = graph;
        this.startStation = startStation;
        this.goalStation = goalStation;
        visited = new ArrayList<>();
        visited.add(startStation);
        nonVisited = new ArrayList<>(graph.getStationList());
        nonVisited.remove(startStation);
        solutionPath = new ArrayList<>();
        currentStation = startStation;
    }

    public void run(){
        while (nonVisited.size()!= 0){
            search();
        }
    }

    public void search(){

    }

    public Station findLowestWeight(){

        Station nextStation =null;
        double lowestWeight = 5000;         //will never be exceeded
        for (Connection connection:graph.getConnectionList()) {
            if (connection.getFrom().equals(currentStation))
                if (connection.getWeight()<lowestWeight){
                    lowestWeight = connection.getWeight();
                    nextStation = connection.getTo();
                }
        }
        return nextStation;
    }
}
