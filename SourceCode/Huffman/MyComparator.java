
import java.util.Comparator;
//This comparison class will help us on arranging the order of the nodes
//Basically, this will help us on constructing our huffman tree

public class MyComparator implements Comparator<Node>{

    public int compare(Node x, Node y){
        return x.data-y.data;
    }
    
}
