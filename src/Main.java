import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Erzeugen einer Liste von Strings, einer Liste von Knoten
        // und einer Liste von Kanten aus den Daten der txt-Datei
        ArrayList<String> fileData = readFile("MST_Prim1.txt");
        ArrayList<Node> nodes = Node.createNodes(fileData);
        // Erzeugen von 2 'leeren' MST aus der Liste von Knoten
        MST mst = new MST(new ArrayList<Node>(nodes));
        MST eagerMST = new MST(new ArrayList<Node>(nodes));
        ArrayList<Edge> edges = Edge.createAndAddEdges(fileData, nodes);
        // Erzeugen eines Graphens aus der Liste von Knoten und Kanten
        Graph graph = new Graph(nodes, edges);

        // Erzeugen des MST aus dem Graphen mithilfe des 'lazy' Algorithmus von Prim und Ausgabe dessen auf der Konsole
        mst.primsAlgorithm(graph);
        System.out.println("MST found by the lazy prim's algorithm: ");
        System.out.println(mst.toString());

        for (Edge edge: edges) {
            edge.setUsed(false);
        }

        // Erzeugen des MST aus dem Graphen mithilfe des 'eager' Algorithmus von Prim und Ausgabe dessen auf der Konsole
        eagerMST.eagerPrimsAlgorithm(graph);
        System.out.println("MST found by the eager prim's algorithm: ");
        System.out.println(eagerMST.toString());
    }

    // Methode zum Auslesen der Textdatei und Übertragen der Daten in das Format einer Liste von Strings
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
            br.close();
            return fileData;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return fileData;
    }
}