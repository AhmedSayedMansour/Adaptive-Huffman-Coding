import java.util.HashMap;
public class Tree {
    public HashMap<Character,Node> content = new HashMap<Character,Node>();
    public Node NYT;
    public Node root;

    public Tree(){
        root = new Node(null,null,null,' ',"-1",100,0);
        NYT=root;
    }

    public void InsertSymbol(char symbol) {
        if (content.isEmpty()){
            root.right = new Node(root ,null,null,symbol,"1",NYT.number - 1,1);
            content.put(symbol,NYT.right);
            root.left = new Node(root ,null,null,' ',"0",NYT.number - 2,0);
            NYT = root.left;
        }
        else if ( content.containsKey(symbol) ){    //Symbol exist
            System.out.println("NOT2");
            Node SNode = content.get(symbol);
            SNode.setCount(SNode.count + 1);
        }
        else {      //symbol not found
            NYT.right = new Node(NYT ,null ,null ,symbol ,NYT.code+"1" ,NYT.number - 1 ,1);
            content.put(symbol,NYT.right);
            NYT.left = new Node(NYT ,null ,null ,' ' ,NYT.code+"0"  ,NYT.number - 2 ,0);
            NYT = NYT.left;
        }
    }

    public void PrintNode(Node P){
        System.out.println(P.symbol);
        System.out.println(P.code);
        System.out.println(P.number);
        System.out.println(P.count);
    }

    public void PrintTree(){
        Node P = root;
        PrintNode(P);
        System.out.println();
        while(P != NYT){
            PrintNode(P.right);
            System.out.println();
            PrintNode(P.left);
            System.out.println();
            P=P.left;
        }
    }

    public void UpdateInsert(){
        //Algorithm 1 **Longest Swap**

        //Algorithm 2 **shortest Swap**

    }
}
