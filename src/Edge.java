public class Edge {
    private int initial;
    private int destination;
    //private int weight;
    public int weight;

    public Edge(int initial, int destination, int weight) {
        this.initial = initial;
        this.destination = destination;
        this.weight = weight;
    }

    public int getInitial() {
        return this.initial;
    }

    public int getDestination() {
        return this.destination;
    }

    public int getWeight() {
        return this.weight;
    }


    public void setInitial(int initial) {
        this.initial = initial;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void printEdge() {
        System.out.println("Edge: " + this.getInitial() + ", "  + this.getDestination() + ", " +this.getWeight());
    }

    public String toString() {
        return initial + ", " + destination + ", " + weight;
    }

}
