import java.util.Arrays;

public class DijkstraAlgoMatrix {
    private static final int INF = Integer.MAX_VALUE;
    public static int[] d;
    public static int[] pi;
    public static boolean[] visited;

    
    private static void initialise(int V, int source){
        d = new int[V];
        pi = new int[V];
        visited = new boolean[V];

        // initialise arrays
        Arrays.fill(d, INF);
        Arrays.fill(pi, -1);
        Arrays.fill(visited, false);

        d[source] = 0;
    }

    private static int findNext(int[] d, boolean[] visited){
        int min = INF, minIndex = -1;

        for (int v = 0; v < d.length; v++){
            if (!visited[v] && d[v] <= min){
                min = d[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    private static void printArrayResult(int[] d, int[] pi){
        System.out.println("Vertex \t Distance from Source \t Predecessor");
        for (int i = 0; i < d.length; i++){
            System.out.println(i + " \t\t " + d[i] + " \t\t " + pi[i]);
        }
    }

    public void dijkstra(int[][] graph, int source){
        int V = graph.length;
        initialise(V, source);

        // Priority Queue (array)
        for (int i = 0; i < V-1; i++){
            // find shortest distance from current node
            int u = findNext(d, visited);
            visited[u] = true;
            
            // update d and pi
            for (int j = 0; j < V; j++){
                // vertex j not visited yet
                // there exists an edge btwn s and j
                // distance to vertex j is finite
                // if new dist frm source to v thru s is shorter than current dist to v
                if (!visited[j] && graph[u][j] != 0 && d[u] != INF && d[u] + graph[u][j] < d[j]){
                    d[j] = d[u] + graph[u][j];
                    pi[j] = u;
                }
            }
        }

        // print result
        //printArrayResult(d, pi);
    }

}
