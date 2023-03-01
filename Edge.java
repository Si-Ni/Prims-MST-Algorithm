import java.util.ArrayList;

public class Edge {
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

    // Methode welche die Funktionalität der 'priority queue' implementiert
    public static Edge getNextBestEdgeFromList(ArrayList<Edge> priorityQueue) {
        // Beliebiges Element (in diesem Fall das Erste) aus der Liste wird entnommen
        Edge minimalEdge = priorityQueue.get(0);
        int smallestIndex = 0;

        // Iteration über alle Elemente aus der Liste von bisher gefundenen Kanten:
        for (int i = 1; i < priorityQueue.size(); i++) {
            // Wenn die aktuelle Kante leichter ist als die bisher minimal gewichtete Kante,
            // soll die bisher leichteste Kante durch die Neue/Leichtere ersetzt werden
            // und ihr Index gespeichert werden.
            if (priorityQueue.get(i).getWeight() < minimalEdge.getWeight()) {
                minimalEdge = priorityQueue.get(i);
                smallestIndex = i;
            }
        }
        // Die leichteste gefundene Kante wird als genutzt markiert, aus der priority queue entfernt
        // und als ergebnis des Methodenaufrufs zurückgegeben
        minimalEdge.setUsed(true);
        priorityQueue.remove(smallestIndex);

        return minimalEdge;
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
            // Die Kante wird zu jedem der beiden Knoten sowie der Liste 'edges' hinzugefügt
            firstNode.addEdge(edge);
            secondNode.addEdge(edge);
            edges.add(edge);
            // Gleichzeitig wird der ungerichtete Graph in einen gerichteten Graphen konvertiert, bei dem jede Kante
            // sowohl von dem Knoten hin, als auch von ihm weg zeigt.
            // Dazu wird die 'gleiche' Kante nochmal andersherum erzeugt und jedem Knoten zugewiesen und in der
            // Liste 'edges' gespeichert
            Edge edgeBackwards = new Edge(secondNode, firstNode, weight);
            firstNode.addEdge(edgeBackwards);
            secondNode.addEdge(edgeBackwards);
            edges.add(edgeBackwards);
        }
        return edges;
    }
}
