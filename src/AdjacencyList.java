import java.util.*;

public class AdjacencyList {
    int size;
    LinkedList<Edge> [] connections;


    AdjacencyList(int size) {
        this.size = size;
        this.connections = new LinkedList[size];

        for (int i = 0 ; i < size; i++) {
            assert false;
            connections[i] = new LinkedList<Edge>();
        }
    }

    public void addEdge(Edge edge) {
        int index1 = edge.getInitial();
        int index2 = edge.getDestination();
        Edge undirectedEdge = new Edge(edge.getDestination(), edge.getInitial(), edge.getWeight());
        if (index1 == index2 && connections[index1] == null) {
            LinkedList<Edge> newList = new LinkedList<Edge>();
            edge.setWeight(0);
            newList.add(edge);
            return;
        }
        else if (index1 == index2 && connections[index1] != null) {
            edge.setWeight(0);
            connections[index1].add(edge);
            return;
        }

        if (connections[index1] == null) {
            LinkedList<Edge> newList1 = new LinkedList<Edge>();
            newList1.add(edge);
        }
        else {
            connections[index1].add(edge);
        }
        if (connections[index2] == null) {
            LinkedList<Edge> newList2 = new LinkedList<Edge>();
            newList2.add(undirectedEdge);
        }
        else {
            connections[index2].add(undirectedEdge);
        }
    }


    public void print() {
        for(int i = 0; i < size; i++) {
            System.out.print("Node " + i + ": ");
            for(Edge edge : connections[i]) {
                System.out.print("(" + edge.getDestination() + "," + edge .getWeight() + ")  ");
            }
            System.out.println(" ");
        }
    }

public int[] Dijkstra(int startingVertex) {
    int[] distances = new int[size];
    boolean[] visited = new boolean[size];
    PriorityQueue<Edge> minHeap = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.weight));
    Arrays.fill(distances, Integer.MAX_VALUE);
    distances[startingVertex] = 0;
    minHeap.add(new Edge(startingVertex, startingVertex, 0));

    while (!minHeap.isEmpty()) {
        Edge currentEdge = minHeap.poll();
        int currentVertex = currentEdge.getDestination(); // Correctly fetch the initial vertex

        if (visited[currentVertex]) {
            continue;
        }

        visited[currentVertex] = true;

        for (Edge neighbor : connections[currentVertex]) {
            if (!visited[neighbor.getDestination()]) {
                int newDist = distances[currentVertex] + neighbor.getWeight();
                if (newDist < distances[neighbor.getDestination()]) {
                    distances[neighbor.getDestination()] = newDist;
                    minHeap.add(new Edge(currentVertex, neighbor.getDestination(), newDist));
                }
            }
        }
    }
    return distances;
    }

    public void printMinHeapResult(int[] distances) {
        System.out.println("Vertex \t Distance from Source \t Predecessor");
        for (int i = 0; i < distances.length; i++) {
            System.out.println(i + " \t\t " + distances[i]);
        }
    }

}





