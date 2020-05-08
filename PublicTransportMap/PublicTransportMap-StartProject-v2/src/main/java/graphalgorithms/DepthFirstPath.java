package graphalgorithms;

import model.TransportGraph;

import java.util.ArrayList;
import java.util.Stack;

public class DepthFirstPath extends AbstractPathSearch {
    private boolean[] marked; // Has dfs() been called for this vertex?
    private int[] edgeTo; // last vertex on known path to this vertex
    private int s; // source

    public DepthFirstPath(TransportGraph graph, String start, String end) {
        super(graph, start, end);
        marked = new boolean[graph.getNumberOfStations()];
        edgeTo = new int[graph.getNumberOfStations()];
        dfs(graph, s);
    }

    @Override
    public void search() {
        dfs(graph, startIndex);
        for (int i = 0; i < marked.length; i++) {
            if (marked[i]) {
                nodesVisited.add(graph.getStation(i));
            }
        }
    }

    private void dfs(TransportGraph G, int v) {
        marked[v] = true;
        for (int w : G.getAdjacentVertices(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                pathTo(v);
                dfs(G, w);
            }
        }
    }

}