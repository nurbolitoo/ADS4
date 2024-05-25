import java.util.*;

public class DijkstraS<V> {
    private Map<Vertex<V>, Double> distTo;
    private Map<Vertex<V>, Vertex<V>> edgeTo;
    private PriorityQueue<VertexDist<V>> pq;
    private Vertex<V> source;

    public DijkstraS(WeightedGraph<V> graph, V sourceData) {
        this.distTo = new HashMap<>();
        this.edgeTo = new HashMap<>();
        this.pq = new PriorityQueue<>(Comparator.comparingDouble(VertexDist::getDist));
        this.source = graph.getVertex(sourceData);

        for (Vertex<V> v : graph.getVertices().values()) {
            distTo.put(v, Double.POSITIVE_INFINITY);
        }
        distTo.put(source, 0.0);
        pq.add(new VertexDist<>(source, 0.0));

        while (!pq.isEmpty()) {
            Vertex<V> v = pq.poll().getVertex();
            for (Map.Entry<Vertex<V>, Double> entry : v.getAdjacentVertices().entrySet()) {
                relax(v, entry.getKey(), entry.getValue());
            }
        }
    }

    private void relax(Vertex<V> v, Vertex<V> w, double weight) {
        if (distTo.get(w) > distTo.get(v) + weight) {
            distTo.put(w, distTo.get(v) + weight);
            edgeTo.put(w, v);
            pq.add(new VertexDist<>(w, distTo.get(w)));
        }
    }

    public boolean hasPathTo(V destinationData) {
        Vertex<V> destination = getVertexByData(destinationData);
        return distTo.getOrDefault(destination, Double.POSITIVE_INFINITY) < Double.POSITIVE_INFINITY;
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
        for (Vertex<V> v : distTo.keySet()) {
            if (v.getData().equals(data)) {
                return v;
            }
        }
        return null;
    }

    private static class VertexDist<V> {
        private final Vertex<V> vertex;
        private final double dist;

        public VertexDist(Vertex<V> vertex, double dist) {
            this.vertex = vertex;
            this.dist = dist;
        }

        public Vertex<V> getVertex() {
            return vertex;
        }

        public double getDist() {
            return dist;
        }
    }
}
