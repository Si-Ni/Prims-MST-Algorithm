import java.util.ArrayList;

public class IPQ {

    // Implementierung der Datenstruktur: 'indexed priority queue'
    // Diese IPQ ist wie aus einer Liste von Tupeln aufgebaut.
    public class NodeWithEdgePointingAtIt {
        // Das erste Element ist ein Knoten und bildet hierbei den 'Index' der IPQ.
        // Das 2. Element ist die bisher minimal gewichtete Kante, welche auf diesen Knoten zeigt.
        private Node node;
        private Edge edge;
        public NodeWithEdgePointingAtIt(Node node, Edge edge) {
            this.node = node;
            this.edge = edge;
        }

        public Node getNode() {
            return node;
        }

        public Edge getEdge() {
            return edge;
        }

        public void setEdge(Edge edge) {
            this.edge = edge;
        }
    }

    private ArrayList<NodeWithEdgePointingAtIt> nodes;

    public IPQ() {
        this.nodes = new ArrayList<>();
    }

    // Methode zum Hinzufügen eines neuen Datensatzes(Knoten, Kante) zu der IPQ
    public void insert(Node node, Edge edge) {
        nodes.add(new NodeWithEdgePointingAtIt(node, edge));
    }

    // Methode zum Überprüfen, ob ein Knoten bereits in der IPQ vorhanden ist
    public boolean contains(Node node) {
        return nodes.stream().filter(o -> o.getNode().equals(node)).findFirst().isPresent();
    }

    // Methode, welche überprüft, ob die übergebene Kante leichter ist, als die bisher gespeicherte
    public void decreaseKey(Node updatedNode, Edge edge) {
        // Suche den übergebenen Knoten aus der IPQ
        NodeWithEdgePointingAtIt node = nodes.stream().filter(o -> o.getNode().equals(updatedNode)).findFirst().orElse(null);
        // Wenn die übergebene Kante leichter ist, als die Bisherige,
        // so setze die leichtere Kante als neue Kante für den Knoten
        if (edge.getWeight() < node.getEdge().getWeight()) {
            node.setEdge(edge);
        }
    }

    // Methode zum Überprüfen, ob die IPQ leer ist
    public boolean isEmpty() {
        return nodes.isEmpty();
    }

    // Methode um das höchst priorisierte Element (Kante mit minimaler Gewichtung) aus der IPQ zu entnehmen
    public Edge dequeue() {
        // Entnehme beliebiges Element (hier das Erste) aus der IPQ
        Edge minimalEdge = nodes.get(0).getEdge();
        int smallestIndex = 0;

        // Iteration über alle Elemente in der IPQ:
        for (int i = 1; i < nodes.size(); i++) {
            // Wenn die aktuelle Kante leichter ist als die bisher minimal gewichtete Kante,
            // soll die bisher leichteste Kante durch die Neue/Leichtere ersetzt werden
            // und ihr Index gespeichert werden.
            if (nodes.get(i).getEdge().getWeight() < minimalEdge.getWeight()) {
                minimalEdge = nodes.get(i).getEdge();
                smallestIndex = i;
            }
        }
        // Die leichteste gefundene Kante wird als genutzt markiert, aus der priority queue entfernt
        // und als ergebnis des Methodenaufrufs zurückgegeben
        minimalEdge.setUsed(true);
        nodes.remove(smallestIndex);

        return minimalEdge;
    }
}
