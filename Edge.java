import java.util.ArrayList;

public class Edge {
    private Node sourceNode;
    private Node destNode;
    private int weight;
    private boolean visited;

    public Edge(Node firstNode, Node secondNode, int weight) {
        this.sourceNode = firstNode;
        this.destNode = secondNode;
        this.weight = weight;
        this.visited = false;
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

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public static Edge getNextBestEdgeFromList(ArrayList<Edge> priorityQueue) {
        Edge minimalEdge = priorityQueue.get(0);

        int smallestIndex = 0;
        for (int i = 1; i < priorityQueue.size(); i++) {
            if (priorityQueue.get(i).getWeight() < minimalEdge.getWeight()) {
                minimalEdge = priorityQueue.get(i);
                smallestIndex = i;
            }
        }
        minimalEdge.setVisited(true);
        priorityQueue.remove(smallestIndex);

        return minimalEdge;
    }

    public static ArrayList<Edge> createAndAddEdges(ArrayList<String> data, ArrayList<Node> nodes) {
        ArrayList<Edge> edges = new ArrayList<>();
        for (String line : data) {
            String[] s = line.split("\\s\\s");
            Node firstNode = nodes.stream().filter(node -> Integer.parseInt(s[0]) == node.getId()).findFirst().orElse(null);
            Node secondNode = nodes.stream().filter(node -> Integer.parseInt(s[1]) == node.getId()).findFirst().orElse(null);
            Integer weight = Integer.parseInt(s[2]);
            Edge edge = new Edge(firstNode, secondNode, weight);
            firstNode.addEdge(edge);
            secondNode.addEdge(edge);
            edges.add(edge);
            Edge edgeBackwards = new Edge(secondNode, firstNode, weight);
            firstNode.addEdge(edgeBackwards);
            secondNode.addEdge(edgeBackwards);
            edges.add(edgeBackwards);
        }
        return edges;
    }
}
