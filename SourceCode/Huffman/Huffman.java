import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;


public class Huffman {

    Image extraxtHexCode = new Image();
    Map<String, String> huffmanMap = new HashMap<>();
    String treePath = "C:\\Users\\jbmon\\OneDrive\\Desktop\\Kinit-main\\Data\\huffman_tree.HUFF";

    Node root;
    private PriorityQueue<Node> createNode(String[] hexCode, int[] hexCodeFreq, int n) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(n, new MyComparator());
        for (int i = 0; i < n; i++) {
            Node node = new Node(hexCode[i], hexCodeFreq[i]);
            priorityQueue.add(node);
        }
        return priorityQueue;
    }

    //
    private Node createRootNode(PriorityQueue<Node> priorityQueue) {
        while (priorityQueue.size() > 1) {
            Node x = priorityQueue.poll();
            Node y = priorityQueue.poll();
            Node parent = new Node(x.data + y.data);
            parent.left = x;
            parent.right = y;
            priorityQueue.add(parent);
        }
        return priorityQueue.poll();
    }

    //pag create han dot huffmanTree na file
    public void printHuffmanCode(Node root, String s, BufferedWriter writer) throws IOException {
        if (root.left == null && root.right == null && root.hexCode != null) {
            writer.write(root.hexCode + "," + s + "\n");
            return;
        }
        if (root.left != null) printHuffmanCode(root.left, s + "0", writer);
        if (root.right != null) printHuffmanCode(root.right, s + "1", writer);
    }

    public void createHuffanTree() {
        extraxtHexCode.trainImage();
        if (extraxtHexCode.hexFrequencyMap.isEmpty()) {
            System.err.println("Frequency map is empty. Cannot compress.");
            return;
        }
        String[] hexCodeArray = new String[extraxtHexCode.hexFrequencyMap.size()];
        int[] hexCodeFreq = new int[extraxtHexCode.hexFrequencyMap.size()];
    
        int index = 0;
        for (Map.Entry<String, Integer> entry : extraxtHexCode.hexFrequencyMap.entrySet()) {
            hexCodeArray[index] = entry.getKey();
            hexCodeFreq[index] = entry.getValue();
            index++;
        }
        PriorityQueue<Node> priorityQueue = createNode(hexCodeArray, hexCodeFreq, extraxtHexCode.hexFrequencyMap.size());
        root = createRootNode(priorityQueue);
    
        File huffmanCodeFile = new File(treePath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(huffmanCodeFile, false))) {
            printHuffmanCode(root, "", writer);
            System.out.println("\n\n******************************************************************");
            System.out.println("*   Huffman codes successfully written to huffman_tree.HUFF");
            System.out.println("******************************************************************");
        } catch (IOException e) {
            System.err.println("Error writing to huffman_tree.txt: " + e.getMessage());
        }
    
    }

}
