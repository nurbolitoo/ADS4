public class Main {
    public static void main(String[] args) {
        WeightedGraph<String> graph = new WeightedGraph<>();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");

        graph.addEdge("A", "B", 1);
        graph.addEdge("A", "C", 4);
        graph.addEdge("B", "C", 2);
        graph.addEdge("B", "D", 5);
        graph.addEdge("C", "D", 1);
        graph.addEdge("D", "E", 3);

        // BFS
        BFS<String> bfs = new BFS<>(graph, "A");
        System.out.println("BFS Path from A to E: " + bfs.pathTo("E"));

        // Dijkstra
        DijkstraS<String> dijkstra = new DijkstraS<>(graph, "A");
        System.out.println("Dijkstra Path from A to E: " + dijkstra.pathTo("E"));




    }
}