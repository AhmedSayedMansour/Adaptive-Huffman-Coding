public class Node {

    public Node parent = null;          //for parent
    public Node left = null;            //for left
    public Node right = null;           //for right

    protected boolean isNYT = false;
    protected boolean isroot = false;

    public char symbol;
    public int code;
    public int number;
    public int count;

    public Node(Node parent, Node left, Node right, char symbol, int code, int Number, int count) {
        this.parent = parent;
        this.left = left;
        this.right = right;

        this.symbol = symbol;
        this.code = code;
        this.number = number;
        this.count = count;
    }

    public Node() {

    }
}
