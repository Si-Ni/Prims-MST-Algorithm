import java.util.ArrayList;

public class IPQ {
    public class NodeWithEdgePointingAtIt {
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

    public void insert(Node node, Edge edge) {
        nodes.add(new NodeWithEdgePointingAtIt(node, edge));
    }

    public boolean contains(Node node) {
        return nodes.stream().filter(o -> o.getNode().equals(node)).findFirst().isPresent();
    }

    public void decreaseKey(Node updatedNode, Edge edge) {
        NodeWithEdgePointingAtIt node = nodes.stream().filter(o -> o.getNode().equals(updatedNode)).findFirst().orElse(null);
        if (edge.getWeight() < node.getEdge().getWeight()) {
            node.setEdge(edge);
        }
    }

    public boolean isEmpty() {
        return nodes.isEmpty();
    }

    public Edge dequeue() {
        Edge minimalEdge = nodes.get(0).getEdge();

        int smallestIndex = 0;
        for (int i = 1; i < nodes.size(); i++) {
            if (nodes.get(i).getEdge().getWeight() < minimalEdge.getWeight()) {
                minimalEdge = nodes.get(i).getEdge();
                smallestIndex = i;
            }
        }
        minimalEdge.setVisited(true);
        nodes.remove(smallestIndex);

        return minimalEdge;
    }
}
