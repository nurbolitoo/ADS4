import java.util.HashMap;
import java.util.Map;

public class WeightedGraph<V> {
    private Map<V, Vertex<V>> vertices;

    public WeightedGraph() {
        this.vertices = new HashMap<>();
    }

    public void addVertex(V data) {
        vertices.putIfAbsent(data, new Vertex<>(data));
    }

    public void addEdge(V sourceData, V destData, double weight) {
        Vertex<V> source = vertices.get(sourceData);
        Vertex<V> dest = vertices.get(destData);

        if (source == null || dest == null) {
            throw new IllegalArgumentException("Both vertices must be part of the graph");
        }

        source.addAdjacentVertex(dest, weight);
    }

    public Vertex<V> getVertex(V data) {
        return vertices.get(data);
    }

    public Map<V, Vertex<V>> getVertices() {
        return vertices;
    }
}
