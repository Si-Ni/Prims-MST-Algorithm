import java.util.ArrayList;

public class MST {
    // Implementierung der Datenstruktur: 'Minimum Spanning Tree' (MST)
    // bestehend aus einer Liste von Knoten und einer Liste von Kanten
    // sowie einem Integer cost, welcher die gesamten Kosten / die gesamte Gewichtung des Baumes enthält.
    private ArrayList<Node> nodes;
    private ArrayList<Edge> edges;
    private int cost;

    public MST(ArrayList<Node> nodes) {
        this.nodes = nodes;
        this.edges = new ArrayList<>();
        this.cost = 0;
    }

    public void addEdge(Edge edge) {
        this.edges.add(edge);
    }

    // Methode: Implementiert den Algorithmus von Prim (lazy)
    public void primsAlgorithm(Graph graph) {
        // Erzeugung einer Liste aller Knoten aus den Knoten den übergebenen Graphens
        ArrayList<Node> allNodes = graph.getNodes();
        // Erzeugung einer leeren Liste für die bereits besuchten Knoten
        ArrayList<Node> visitedNodes = new ArrayList<>();
        // Erzeugung einer leeren priority queue (Liste) von Kanten
        ArrayList<Edge> priorityQueue = new ArrayList<>();

        // Der erste Knoten des Graphens wird betrachtet/besucht und zu den besuchten Knoten hinzugefügt
        Node visitingNode = allNodes.get(0);
        visitedNodes.add(visitingNode);
        // Die Methode addEdges wird aufgerufen:
        addEdges(priorityQueue, visitingNode);

        // Solange die priority queue nicht leer ist und die Anzahl von besuchten Knoten kleiner bzw. ungleich der
        // Gesamtanzahl von Knoten ist, führe Folgendes aus:
        while (!priorityQueue.isEmpty() && visitedNodes.size() != allNodes.size()) {
            // Über den Methodenaufruf 'getNextBestEdgeFromList' wird die aktuell minimal gewichtete Kante aus
            // der priority queue entnommen
            Edge minimalEdge = Edge.getNextBestEdgeFromList(priorityQueue);
            // Der Knoten, auf welchen die Kante zeigt, wird zum neuen betrachteten/besuchten Knoten
            visitingNode = minimalEdge.getDestNode();

            // wenn dieser Knoten bereits besucht wurde, dann wird der folgende Teil übersprungen
            // und zum Anfang der Schleife zurückgekehrt
            if (visitedNodes.contains(visitingNode)) {
                continue;
            }

            // Füge die Kante zu den Kanten des MST hinzu
            // und addiere die Kosten der Kante zu den gesamten Kosten des MST
            this.addEdge(minimalEdge);
            this.cost += minimalEdge.getWeight();

            // Füge den aktuell betrachteten Knoten zu den besuchten Knoten hinzu
            // und rufe mit ihm die Methode addEdges auf, um seine Kanten zur PQ hinzuzufügen
            visitedNodes.add(visitingNode);
            addEdges(priorityQueue, visitingNode);
        }
    }

    // Methode zum Hinzufügen aller Kanten des aktuell betrachteten Knotens zu der priority queue
    private void addEdges(ArrayList<Edge> priorityQueue, Node visitingNode) {
        for (Edge edge : visitingNode.getEdges()) {
            // Wenn die Kante noch nicht genutzt wird, wird sie zur PQ hinzugefügt
            if (!edge.isUsed()) priorityQueue.add(edge);
        }
    }

    // Methode: Implementiert eine optimierte Version des Algorithmus von Prim (eager)
    public void eagerPrimsAlgorithm(Graph graph) {
        // Erzeugung einer Liste aller Knoten aus den Knoten den übergebenen Graphens
        ArrayList<Node> allNodes = graph.getNodes();
        // Erzeugung einer leeren Liste für die bereits besuchten Knoten
        ArrayList<Node> visitedNodes = new ArrayList<>();
        // Erzeugung einer leeren 'indexed priority queue'
        IPQ priorityQueue = new IPQ();

        // Der erste Knoten des Graphens wird betrachtet/besucht
        Node visitingNode = allNodes.get(0);
        // Die Methode relaxEdgesAtNode wird aufgerufen:
        relaxEdgesAtNode(priorityQueue, visitedNodes, visitingNode);

        // Solange die priority queue nicht leer ist und die Anzahl von besuchten Knoten kleiner bzw. ungleich der
        // Gesamtanzahl von Knoten ist, führe Folgendes aus:
        while (!priorityQueue.isEmpty() && visitedNodes.size() != allNodes.size()) {
            // Über den Methodenaufruf 'dequeue' wird die aktuell minimal gewichtete Kante aus
            // der IPQ entnommen
            Edge minimalEdge = priorityQueue.dequeue();
            // Der Knoten, auf welchen die Kante zeigt, wird zum neuen betrachteten/besuchten Knoten
            visitingNode = minimalEdge.getDestNode();

            // wenn dieser Knoten bereits besucht wurde, dann wird der folgende Teil übersprungen
            // und zum Anfang der Schleife zurückgekehrt
            if (visitedNodes.contains(visitingNode)) {
                continue;
            }

            // Füge die Kante zu den Kanten des MST hinzu
            // und addiere die Kosten der Kante zu den gesamten Kosten des MST
            this.addEdge(minimalEdge);
            this.cost += minimalEdge.getWeight();

            // Aufruf der Methode relaxEdgesAtNode mit dem 'neu' betrachteten Knoten
            relaxEdgesAtNode(priorityQueue, visitedNodes, visitingNode);
        }
    }

    // Methode zum Aktualisieren der IPQ für den übergebenen Knoten
    private void relaxEdgesAtNode(IPQ priorityQueue, ArrayList<Node> visitedNodes, Node visitingNode) {
        // Knoten wird zu den besuchten Knoten hinzugefügt
        visitedNodes.add(visitingNode);

        // Iteration über jede Kante des übergebenen Knotens
        for (Edge edge : visitingNode.getEdges()) {
            // Zielknoten der Kante wird als destNode gespeichert
            Node destNode = edge.getDestNode();

            // Wenn dieser Knoten bereits betrachtet wurde, wird Iteration für diese eine Kante übersprungen
            if (visitedNodes.contains(destNode)) continue;

            // Wenn die IPQ den Knoten nicht enthält, wird er mit der aktuellen Kante zur IPQ hinzugefügt
            if (!priorityQueue.contains(destNode)) {
                priorityQueue.insert(destNode, edge);
            } else {
                // Anderenfalls wird die Methode decreaseKey aufgerufen
                // und überprüft, ob die aktuelle Kante leichter ist, als die bisher gespeicherte Kante
                priorityQueue.decreaseKey(destNode, edge);
            }
        }
    }

    // Methode zum Konvertieren des MST in einen String (für Konsolenausgabe)
    public String toString() {
        String output = "";
        output += "Total cost of MST: " + String.valueOf(cost) + "\n";
        output += String.format("Edge         Weight \n");
        for (Edge edge : edges) {
            String firstNode = String.valueOf(edge.getSourceNode().getId());
            String secondNode = String.valueOf(edge.getDestNode().getId());
            String weight = String.valueOf(edge.getWeight());
            output += String.format("%-2s - %-2s      %s \n", firstNode, secondNode, weight);
        }
        return output;
    }

}
