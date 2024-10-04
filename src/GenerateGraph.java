import java.util.Scanner;
import java.util.Random;

public class GenerateGraph {
    private static final int INF = Integer.MAX_VALUE;

    public static void generator(int numVert, double density, int maxWeight, int[][] adjMatrix, AdjacencyList adjList){
        Random rand = new Random();
        int counter = 0;
        for (int i = 0; i < numVert; i++){
            for (int j = i; j < numVert; j++){
                // check edge existence
                if (i != j && rand.nextDouble() < density){
                    // random weight btwn 1 and maxWeight
                    int weight = rand.nextInt(maxWeight) + 1;
                    adjMatrix[i][j] = weight;
                    adjMatrix[j][i] = weight;
                    Edge edge = new Edge(i, j, weight);
                    //edge.printEdge();
                    //counter++;
                    adjList.addEdge(edge);
                }
                // edge DNE ----- dble cfm if input is INF
                else {
                    if (i != j) {
//                        adjMatrix[i][j] = INF;
//                        adjMatrix[j][i] = INF; // undirected
//                        Edge edgeN = new Edge(i, j, INF);
//                        adjList.addEdge(edgeN);
                    }
                }    
            }
        }
        //System.out.println(counter);
    }
    public static void printGraph(int[][] matrix, AdjacencyList list){
        System.out.println("Adjacency Matrix:");
        for (int[] row : matrix){
            for (int value : row){
                if (value == INF){
                    System.out.print("INF\t");
                }
                else {
                    System.out.print(value + "\t");
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("Adjacency List: ");
        list.print();
        
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.println("Number of vertices: ");
        int numVert = sc.nextInt();
        System.out.println("Density of graph: (0.0 to 1.0)");
        double density = sc.nextDouble();
        // range x < 0.5 -- sparse; x >= 0.5 -- dense;
        System.out.println("Max possible weight for edges: ");
        int maxWeight = sc.nextInt();

        // initialise adj list and matrix
        int[][] adjMatrix = new int[numVert][numVert];
        AdjacencyList adjList = new AdjacencyList(numVert);

        // generate graph for adjMatrix and adjList
        generator(numVert, density, maxWeight, adjMatrix, adjList);
        printGraph(adjMatrix, adjList);

        sc.close();
    }

}
