import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> fileData = readFile("MST_Prim1.txt");
        ArrayList<Node> nodes = Node.createNodes(fileData);
        MST mst = new MST(new ArrayList<Node>(nodes));
        ArrayList<Edge> edges = Edge.createAndAddEdges(fileData, nodes);
        Graph graph = new Graph(nodes, edges);

        mst.primsAlgorithm(graph);
        System.out.println(mst.toString());


        MST eagerMST = new MST(new ArrayList<Node>(nodes));
        eagerMST.eagerPrimsAlgorithm(graph);
        System.out.println(eagerMST.toString());
    }

    private static ArrayList<String> readFile(String filename) {
        ArrayList<String> fileData = new ArrayList<>();
        try {
            File inputFile = new File(filename);
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            String contentLine = br.readLine();
            while (contentLine != null) {
                fileData.add(contentLine);
                contentLine = br.readLine();
            }
            return fileData;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return fileData;
    }
}