class Node {
    String hexCode; //an color 
    Node left, right; // mga anak hit root
    int data; // frequency

    Node(){
        
    }

    //para inen han mga child
    Node(String hexCode, int data) {
        this.hexCode = hexCode;
        this.data = data;
        this.left = null;
        this.right = null;
    }

    //para inen han root node
    Node(int data) {
        this.hexCode = null;    
        this.data = data;
        this.left = null;
        this.right = null;
    }
}
