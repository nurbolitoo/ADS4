
import java.util.*;

public class BFS<V> {
    private Set<Vertex<V>> marked;
    private Map<Vertex<V>, Vertex<V>> edgeTo;
    private Vertex<V> source;

    public BFS(WeightedGraph<V> graph, V sourceData) {
        this.marked = new HashSet<>();
        this.edgeTo = new HashMap<>();
        this.source = graph.getVertex(sourceData);
        bfs(graph, this.source);
    }

    private void bfs(WeightedGraph<V> graph, Vertex<V> source) {
        Queue<Vertex<V>> queue = new LinkedList<>();
        queue.add(source);
        marked.add(source);

        while (!queue.isEmpty()) {
            Vertex<V> v = queue.poll();
            for (Vertex<V> w : v.getAdjacentVertices().keySet()) {
                if (!marked.contains(w)) {
                    edgeTo.put(w, v);
                    marked.add(w);
                    queue.add(w);
                }
            }
        }
    }

    public boolean hasPathTo(V destinationData) {
        Vertex<V> destination = getVertexByData(destinationData);
        return marked.contains(destination);
    }

    public Iterable<V> pathTo(V destinationData) {
        if (!hasPathTo(destinationData)) return null;

        Stack<V> path = new Stack<>();
        Vertex<V> destination = getVertexByData(destinationData);

        for (Vertex<V> x = destination; x != source; x = edgeTo.get(x)) {
            path.push(x.getData());
        }
        path.push(source.getData());
        return path;
    }

    private Vertex<V> getVertexByData(V data) {
        for (Vertex<V> v : marked) {
            if (v.getData().equals(data)) {
                return v;
            }
        }
        return null;
    }
}

