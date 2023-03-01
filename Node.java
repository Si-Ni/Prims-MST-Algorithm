import java.util.ArrayList;

public class Node {
    private int id;
    private ArrayList<Edge> edges;

    public Node(int id) {
        this.id = id;
        this.edges = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public void addEdge(Edge edge) {
        this.edges.add(edge);
    }

    public static ArrayList<Node> createNodes(ArrayList<String> data) {
        ArrayList<Integer> nodeIDs = new ArrayList<>();
        for (String line : data)
        {
            String[] s = line.split("\\s\\s");
            int id = Integer.parseInt(s[0]);
            int id2 = Integer.parseInt(s[1]);
            if (!nodeIDs.contains(id)) nodeIDs.add(id);
            if (!nodeIDs.contains(id2)) nodeIDs.add(id2);
        }
        ArrayList<Node> nodes = new ArrayList<>();
        for (int id : nodeIDs) {
            nodes.add(new Node(id));
        }
        return nodes;
    }
}
