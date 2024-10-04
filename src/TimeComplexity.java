import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;



public class TimeComplexity {
    public static void main(String[] args){
//        Scanner sc = new Scanner(System.in);
//
//        System.out.println("Number of vertices: ");
//        int numVert = sc.nextInt();
//        System.out.println("Density of graph: (0.0 to 1.0)");
//        double density = sc.nextDouble();
//        // range x < 0.5 -- sparse; x >= 0.5 -- dense;
//        System.out.println("Max possible weight for edges: ");
//        int maxWeight = sc.nextInt();
//        System.out.println("Source vertex: ");
//        int beginning = sc.nextInt();

        int numVert = 1000;
        double density = 1;
        int maxWeight = 10000;
        int beginning = 0;


        File outfile = new File("TimeComparisions.CSV");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(outfile, false);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        PrintStream ps = new PrintStream(fos);


        ps.println("Density = 1, increasing number of vertices intervals of 100");
        // ps.println("minHeap PQ:        " + "array PQ:");
        ps.println("Number of Vertices, Min-Heap PQ Time (s), Array PQ Time (s)");

        DijkstraAlgoMatrix graph = new DijkstraAlgoMatrix();

        for (int i = 100; i < numVert; i+=100) {

            double avgMin = 0;
            double avgArray = 0;

            int[][] adjMatrix = new int[i][i];
            AdjacencyList adjList = new AdjacencyList(i);

            // repeat to get avg (repeat 20 times)
            for (int j = 0; j < 20; j++){
                // generate graph for adjMatrix and adjList
                GenerateGraph.generator(i, density, maxWeight, adjMatrix, adjList);

                long minHeapStart = System.nanoTime();
                int[] minHeapDist = adjList.Dijkstra(beginning);
                //adjList.printMinHeapResult(minHeapDist);
                long minHeapEnd = System.nanoTime();
                double minHeapElapsedTime = (double)((minHeapEnd - minHeapStart)) / 1000000000;
                //System.out.println("Time for adjacency list with min Heap priority queue: " + minHeapElapsedTime);
                // ps.print(minHeapElapsedTime);
                // ps.print("     ");
                avgMin += minHeapElapsedTime;
                long arrayStart = System.nanoTime();
                graph.dijkstra(adjMatrix,beginning);
                long arrayEnd = System.nanoTime();
                double arrayElapsedTime = (double)((arrayEnd - arrayStart)) / 1000000000;
                //System.out.println("Time for adjacency matrix with array priority queue : " + arrayElapsedTime);
                // ps.println(arrayElapsedTime);
                avgArray += arrayElapsedTime;

            }
            avgMin = (avgMin)/20;
            avgArray = (avgArray)/20;
            ps.printf("%d,%.6f,%.6f%n", i, avgMin, avgArray);

            adjMatrix = null;
            adjList = null;
            System.gc(); // Force garbage collection after nullifying large objects
        }
    }

//        // initialise adj list and matrix
//        DijkstraAlgoMatrix graph = new DijkstraAlgoMatrix();
//        int[][] adjMatrix = new int[numVert][numVert];
//        AdjacencyList adjList = new AdjacencyList(numVert);
//
//
//        // generate graph for adjMatrix and adjList
//        GenerateGraph.generator(numVert, density, maxWeight, adjMatrix, adjList);
//
//
//        File outfile = new File("TimeComparisions.CSV");
//        FileOutputStream fos;
//        try {
//            fos = new FileOutputStream(outfile, false);
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        PrintStream ps = new PrintStream(fos);
//
//
//
//        ps.println("Time for adjacency list with min Heap priority queue:     " + "Time for adjacency matrix with array priority queue:");
//
//
//        long minHeapStart = System.nanoTime();
//        int[] minHeapDist = adjList.Dijkstra(beginning);
//        adjList.printMinHeapResult(minHeapDist);
//        long minHeapEnd = System.nanoTime();
//        double minHeapElapsedTime = (double)((minHeapEnd - minHeapStart)) / 1000000000;
//        //System.out.println("Time for adjacency list with min Heap priority queue: " + minHeapElapsedTime);
//        ps.print(minHeapElapsedTime);
//        ps.print("     ");
//        long arrayStart = System.nanoTime();
//        graph.dijkstra(adjMatrix,beginning);
//        long arrayEnd = System.nanoTime();
//        double arrayElapsedTime = (double)((arrayEnd - arrayStart)) / 1000000000;
//        //System.out.println("Time for adjacency matrix with array priority queue : " + arrayElapsedTime);
//        ps.println(arrayElapsedTime);
//    }
}
