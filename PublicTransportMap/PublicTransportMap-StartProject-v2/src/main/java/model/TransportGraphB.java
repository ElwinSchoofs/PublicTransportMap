package model;

import java.util.*;

public class TransportGraphB {

    private int numberOfStations;
    private int numberOfConnections;
    private List<Station> stationList;
    private Map<String, Integer> stationIndices;
    private List<Integer>[] adjacencyLists;
    private Connection[][] connections;
    private List<Connection> connectionList;

    public TransportGraphB(int size) {
        this.numberOfStations = size;
        stationList = new ArrayList<>(size);
        stationIndices = new HashMap<>();
        connections = new Connection[size][size];
        adjacencyLists = (List<Integer>[]) new List[size];
        for (int vertex = 0; vertex < size; vertex++) {
            adjacencyLists[vertex] = new LinkedList<>();
        }
    }

    /**
     * @param vertex Station to be added to the stationList
     *               The method also adds the station with it's index to the map stationIndices
     */
    public void addVertex(Station vertex) {
        stationList.add(vertex);
        stationIndices.put(vertex.getStationName(), this.stationList.indexOf(vertex));
//        this.numberOfStations++;
    }


    /**
     * Method to add an edge to a adjancencyList. The indexes of the vertices are used to define the edge.
     * Method also increments the number of edges, that is number of Connections.
     * The grap is bidirected, so edge(to, from) should also be added.
     *
     * @param from
     * @param to
     */
    private void addEdge(int from, int to) {
        if (adjacencyLists[from].contains(to)) {
            adjacencyLists[from].add(to);
            this.numberOfConnections += 1;
        }
//            adjacencyLists[from] = new LinkedList<Integer>(adjacencyLists[from])
        if (adjacencyLists[to].contains(from)) {
            adjacencyLists[to].add(from);
        }
//        adjacencyLists[to] = new LinkedList<Integer>(adjacencyLists[to]);

//        if (!adjacencyLists[from].contains(to)){
//            adjacencyLists[from].add(to);
//            adjacencyLists[to].add(from);
//        }

        // TODO

    }


    /**
     * Method to add an edge in the form of a connection between stations.
     * The method also adds the edge as an edge of indices by calling addEdge(int from, int to).
     * The method adds the connection to the connections 2D-array.
     * The method also builds the reverse connection, Connection(To, From) and adds this to the connections 2D-array.
     *
     * @param connection The edge as a connection between stations
     */
    public void addEdge(Connection connection) {
        // TODO error. Still searching for a solution.
//        adjacencyLists[stationIndices.get(connection.getFrom().getStationName())] = new LinkedList<Integer>(adjacencyLists[stationIndices.get(connection.getFrom().getStationName())]);
        adjacencyLists[stationIndices.get(connection.getFrom().getStationName())].add(stationIndices.get(connection.getTo().getStationName()));

//        adjacencyLists[stationIndices.get(connection.getTo().getStationName())] = new LinkedList<Integer>(adjacencyLists[stationIndices.get(connection.getTo().getStationName())]);
        adjacencyLists[stationIndices.get(connection.getTo().getStationName())].add(stationIndices.get(connection.getFrom().getStationName()));
//        addEdge(stationIndices.get(connection.getFrom().getStationName()), stationIndices.get(connection.getTo().getStationName()));
        this.numberOfConnections += 1;
        connections[connections.length - 1][connections.length - 1] = connection;

    }

    public List<Integer> getAdjacentVertices(int index) {
        return adjacencyLists[index];
    }

    public Connection getConnection(int from, int to) {
        return connections[from][to];
    }

    public Connection[][] getConnections() {
        return connections;
    }

    public int getIndexOfStationByName(String stationName) {
        return stationIndices.get(stationName);
    }

    public Station getStation(int index) {
        return stationList.get(index);
    }

    public int getNumberOfStations() {
        return numberOfStations;
    }

    public List<Station> getStationList() {
        return stationList;
    }

    public int getNumberEdges() {
        return numberOfConnections;
    }

    public void setConnectionList(List<Connection> connectionList) {
        this.connectionList = connectionList;
    }

    public List<Connection> getConnectionList() {
        return connectionList;
    }

    @Override
    public String toString() {
        StringBuilder resultString = new StringBuilder();
        resultString.append(String.format("Graph with %d vertices and %d edges: \n", numberOfStations, numberOfConnections));
        for (int indexVertex = 0; indexVertex < numberOfStations; indexVertex++) {
            resultString.append(stationList.get(indexVertex) + ": ");
            int loopsize = adjacencyLists[indexVertex].size() - 1;
            for (int indexAdjacent = 0; indexAdjacent < loopsize; indexAdjacent++) {
                resultString.append(stationList.get(adjacencyLists[indexVertex].get(indexAdjacent)).getStationName() + "-");
            }
            resultString.append(stationList.get(adjacencyLists[indexVertex].get(loopsize)).getStationName() + "\n");
        }
        return resultString.toString();
    }


    /**
     * A Builder helper class to build a TransportGraph by adding lines and building sets of stations and connections from these lines.
     * Then build the graph from these sets.
     */
    public static class Builder {

        private Set<Station> stationSet;
        private List<Line> lineList;
        private Set<Connection> connectionSet;

        public Builder() {
            lineList = new ArrayList<>();
            stationSet = new HashSet<>();
            connectionSet = new HashSet<>();
        }

        private Line addOrGetLine(Line line) {
            for (Line l : this.lineList) {
                if (l.equals(line)) {
                    return l;
                }
            }
            this.lineList.add(line);
            return line;
        }

        private Station addOrGetStation(Station station) {
            for (Station s : this.stationSet) {
                if (s.equals(station)) {
                    return s;
                }
            }
            this.stationSet.add(station);
            return station;
        }

        /**
         * Method to add a line to the list of lines and add stations to the line.
         *
         * @param lineDefinition String array that defines the line. The array should start with the name of the line,
         *                       followed by the type of the line and the stations on the line in order.
         * @return
         */
        public Builder addLine(String[] lineDefinition) {
            // TODO
            Line line = new Line(lineDefinition[1], lineDefinition[0]);
            for (int i = 2; i < lineDefinition.length; i++) {
                line.addStation(new Station(lineDefinition[i]));

            }
            addOrGetLine(line);
            return this;
        }


        /**
         * Method that reads all the lines and their stations to build a set of stations.
         * Stations that are on more than one line will only appear once in the set.
         *
         * @return
         */
        public Builder buildStationSet() {
            // TODO
            for (Line line : lineList) {
                for (int i = 0; i < line.getStationsOnLine().size(); i++) {
                    addOrGetStation(line.getStationsOnLine().get(i));
                }
            }
            return this;
        }

        /**
         * For every station on the set of station add the lines of that station to the lineList in the station
         *
         * @return
         */
        public Builder addLinesToStations() {
            // TODO
            for (Station station : stationSet) {
                for (Line line : lineList) {
                    if (!station.hasLine(line)) {
                        station.addLine(line);
                    }
                }
            }
            return this;
        }

        /**
         * Method that uses the list of Lines to build connections from the consecutive stations in the stationList of a line.
         *
         * @return
         */
        public Builder buildConnections() {
            // TODO changed connections to second constructor. weight is magic number now. Don't know how to fill that in right now

//            for (Line line : lineList) {
//                for (int i = 0; i < line.getStationsOnLine().size(); i++) {
//                    if (i > 0)
//                        connectionSet.add(
//                                new Connection(line.getStationsOnLine().get(i), line.getStationsOnLine().get(i - 1)));
//
//                    if (i < line.getStationsOnLine().size() - 1)
//                        connectionSet.add(
//                                new Connection(line.getStationsOnLine().get(i), line.getStationsOnLine().get(i + 1)));
//                }
//            }
            for (Line line : lineList) {
                for (int i = 1; i < line.getStationsOnLine().size(); i++) {
                    Connection newConnection = new Connection(line.getStationsOnLine().get(i),
                            line.getStationsOnLine().get(i - 1),(Math.random() * 15) + 1, line);
                }
            }
            return this;
        }

        /**
         * Method that builds the graph.
         * All stations of the stationSet are addes as vertices to the graph.
         * All connections of the connectionSet are addes as edges to the graph.
         *
         * @return
         */
        public TransportGraphB build() {
            TransportGraphB graph = new TransportGraphB(stationSet.size());
            // TODO
            buildStationSet();
            buildConnections();
            graph.setConnectionList(new ArrayList<>(connectionSet));
            for (Station s : stationSet) {
                graph.addVertex(s);
            }
            for (Connection c : connectionSet) {
                graph.addEdge(c);
            }
            return graph;
        }


    }
}
