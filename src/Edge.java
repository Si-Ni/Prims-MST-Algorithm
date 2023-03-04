import java.util.ArrayList;

public class Edge implements Comparable<Edge> {
    // Implementierung der Datenstruktur "Kante"
    // bestehend aus einem Ausgangs- und einem Zielknoten
    // sowie einer Gewichtung und einer Information darüber,
    // ob die Kante bereits genutzt wurde
    private Node sourceNode;
    private Node destNode;
    private int weight;
    private boolean used;

    public Edge(Node firstNode, Node secondNode, int weight) {
        this.sourceNode = firstNode;
        this.destNode = secondNode;
        this.weight = weight;
        this.used = false;
    }

    public Node getSourceNode() {
        return sourceNode;
    }

    public Node getDestNode() {
        return destNode;
    }

    public int getWeight() {
        return weight;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    // Methode welche es der priority queue ermöglicht Objekte vom Typ 'Edge' zu vergleichen
    @Override
    public int compareTo(Edge e) {
        if (weight < e.getWeight())
            return -1;
        else if (weight > e.getWeight())
            return 1;
        return 0;
    }

    // Methode zur Erzeugung einer Liste von Kanten aus einer aus der gegebenen Datei
    // erzeugten Liste von Zeilen (Strings) und Knoten (Nodes)
    public static ArrayList<Edge> createAndAddEdges(ArrayList<String> data, ArrayList<Node> nodes) {
        ArrayList<Edge> edges = new ArrayList<>();
        for (String line : data) {
            String[] s = line.split("\\s\\s");
            // Die 2 von der Kante verbundenen Knoten werden aus der
            // übergebenen Liste (nodes) herausgesucht und gespeichert
            Node firstNode = nodes.stream().filter(node -> Integer.parseInt(s[0]) == node.getId()).findFirst().orElse(null);
            Node secondNode = nodes.stream().filter(node -> Integer.parseInt(s[1]) == node.getId()).findFirst().orElse(null);
            // Zusätzlich wird auch die Gewichtung der Kante in einer Variable weight gespeichert
            Integer weight = Integer.parseInt(s[2]);
            // Das Kantenobjekt wird aus den vorher gesammelten Daten erstellt
            Edge edge = new Edge(firstNode, secondNode, weight);
            // Die Kante wird zu dem ersten der beiden Knoten sowie der Liste 'edges' hinzugefügt
            firstNode.addEdge(edge);
            edges.add(edge);
            // Gleichzeitig wird der ungerichtete Graph in einen gerichteten Graphen konvertiert, bei dem jede Kante
            // sowohl von dem Knoten hin, als auch von ihm weg zeigt.
            // Dazu wird die gleiche Kante nochmal 'andersherum' erzeugt
            // und in der Liste 'edges' gespeichert
            Edge edgeBackwards = new Edge(secondNode, firstNode, weight);
            secondNode.addEdge(edgeBackwards);
            edges.add(edgeBackwards);
            // Jeder der beiden Knoten speichert die von ihm ausgehenden Kanten
        }
        return edges;
    }
}
