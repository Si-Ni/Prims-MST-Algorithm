import java.util.ArrayList;

public class Node {

    // Implementierung der Datenstruktur "Knoten"
    // bestehend aus einer eindeutigen Nummer
    // und einer Liste von ausgehenden und hinführenden Kanten
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

    // Methode zur Erzeugung einer Liste von Knoten aus einer aus der gegebenen Datei
    // erzeugten Liste von Zeilen (Strings)
    public static ArrayList<Node> createNodes(ArrayList<String> data) {
        ArrayList<Integer> nodeIDs = new ArrayList<>();
        // Auslesen der Knotennummern:
        for (String line : data)
        {
            String[] s = line.split("\\s\\s");
            int id = Integer.parseInt(s[0]);
            int id2 = Integer.parseInt(s[1]);
            // falls einer der in dieser Zeile vorkommenden Knoten noch nicht
            // in der Liste nodeIDs gespeichert wurde, wird seine Nummer hier hinzugefügt
            if (!nodeIDs.contains(id)) nodeIDs.add(id);
            if (!nodeIDs.contains(id2)) nodeIDs.add(id2);
        }

        // Erzeugung der Liste von Knoten mit den vorher ausgelesenen Knotennummern:
        ArrayList<Node> nodes = new ArrayList<>();
        for (int id : nodeIDs) {
            nodes.add(new Node(id));
        }
        return nodes;
    }
}
