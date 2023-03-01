import java.util.ArrayList;

public class Graph {

    // Implementierung der Datenstruktur: "Graph"
    // bestehend aus einer Liste von Knoten und einer Liste dazugehöriger Kanten
    private ArrayList<Node> nodes;
    private ArrayList<Edge> edges;

    public Graph(ArrayList<Node> nodes, ArrayList<Edge> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }
}
