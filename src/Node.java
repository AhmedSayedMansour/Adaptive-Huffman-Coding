import java.text.DateFormatSymbols;

public class Node {

    public Node parent = null;          //for parent
    public Node left = null;            //for left
    public Node right = null;           //for right

    public char symbol;
    public String code;
    public int number;
    public int count;

    public Node(Node parent, Node left, Node right, char symbol, String code, int Number, int count) {
        this.parent = parent;
        this.left = left;
        this.right = right;

        this.symbol = symbol;
        this.code = code;
        this.number = Number;
        this.count = count;
    }

    public Node(Node parent) {
        this.parent = parent;
    }

    /*Setters*/
    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setCount(int count) {
        this.count = count;
    }
}