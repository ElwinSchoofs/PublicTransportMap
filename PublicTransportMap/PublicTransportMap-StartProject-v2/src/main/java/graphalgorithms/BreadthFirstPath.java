package graphalgorithms;

import model.TransportGraph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BreadthFirstPath extends AbstractPathSearch {
    private boolean[] marked; // Is a shortest path to this vertex known?
    private int[] edgeTo; // last vertex on known path to this vertex
    private final String s; // source
    private final String e; // end
    private TransportGraph G;
    Queue<Integer> queue = new LinkedList<Integer>();
    public BreadthFirstPath(TransportGraph G, String s, String e)
    {
        super(G,s,e);
        marked = new boolean[G.getNumberOfStations()];
        edgeTo = new int[G.getNumberOfStations()];
        this.s = s;
        this.e = e;
        this.G = G;

    }
    public void search() {
        marked[G.getIndexOfStationByName(s)] = true; // Mark the source
        queue.add(G.getIndexOfStationByName(s)); // and put it on the queue.
        while (!queue.isEmpty())
        {
            int v = queue.remove(); // Remove next vertex from the queue.
            nodesVisited.add(graph.getStation(v));
            for (int w : this.G.getAdjacentVertices(v)){
                if (!marked[w]) { // For every unmarked adjacent vertex,

                    edgeTo[w] = v; // save last edge on a shortest path,
                    marked[w] = true; // mark it because path is known,
                    queue.add(w); // and add it to the queue.
                    if (w == G.getIndexOfStationByName(e)){
                        pathTo(G.getIndexOfStationByName(e));
                    }
                }
            }
        }
    }

}