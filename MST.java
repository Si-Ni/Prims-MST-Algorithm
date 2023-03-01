import java.util.ArrayList;

public class MST {
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

    public void primsAlgorithm(Graph graph) {
        ArrayList<Node> allNodes = graph.getNodes();
        ArrayList<Node> visitedNodes = new ArrayList<>();
        ArrayList<Edge> priorityQueue = new ArrayList<>();

        Node visitingNode = allNodes.get(0);
        visitedNodes.add(visitingNode);
        addEdges(priorityQueue, visitingNode);

        while (!priorityQueue.isEmpty() && visitedNodes.size() != allNodes.size()) {
            Edge minimalEdge = Edge.getNextBestEdgeFromList(priorityQueue);
            visitingNode = minimalEdge.getDestNode();

            if (visitedNodes.contains(visitingNode)) {
                continue;
            }

            this.addEdge(minimalEdge);
            this.cost += minimalEdge.getWeight();

            visitedNodes.add(visitingNode);
            addEdges(priorityQueue, visitingNode);
        }
    }

    private void addEdges(ArrayList<Edge> priorityQueue, Node visitingNode) {
        for (Edge edge : visitingNode.getEdges()) {
            if (!edge.isVisited()) priorityQueue.add(edge);
        }
    }

    public void eagerPrimsAlgorithm(Graph graph) {
        ArrayList<Node> allNodes = graph.getNodes();
        ArrayList<Node> visitedNodes = new ArrayList<>();
        IPQ priorityQueue = new IPQ();

        Node visitingNode = allNodes.get(0);
        relaxEdgesAtNode(priorityQueue, visitedNodes, visitingNode);

        while (!priorityQueue.isEmpty() && visitedNodes.size() != allNodes.size()) {
            Edge minimalEdge = priorityQueue.dequeue();
            visitingNode = minimalEdge.getDestNode();

            if (visitedNodes.contains(visitingNode)) {
                continue;
            }

            this.addEdge(minimalEdge);
            this.cost += minimalEdge.getWeight();

            relaxEdgesAtNode(priorityQueue, visitedNodes, visitingNode);
        }
    }

    private void relaxEdgesAtNode(IPQ priorityQueue, ArrayList<Node> visitedNodes, Node visitingNode) {
        visitedNodes.add(visitingNode);
        for (Edge edge : visitingNode.getEdges()) {
            Node destNode = edge.getDestNode();

            if (visitedNodes.contains(destNode)) continue;

            if (!priorityQueue.contains(destNode)) {
                priorityQueue.insert(destNode, edge);
            } else {
                priorityQueue.decreaseKey(destNode, edge);
            }
        }
    }

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
