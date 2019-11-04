import java.util.ArrayList;
import java.util.HashMap;
public class Tree {
    public HashMap<Character,String> dic = new HashMap<Character, String>();    //Short code
    public HashMap<Character,Node> content = new HashMap<Character,Node>();     //each inserted character and its inserted node
    public Node NYT;
    public Node root;

    public Tree(){
        root = new Node(null,null,null,' ',"-1",100,0);
        NYT=root;
        for ( int i = 0 ; i < 128 ; i++){
            String code=Integer.toBinaryString(i);
            while(code.length()<8)
                code='0'+code;
            dic.put( ((char)i ) ,code);
        }
    }

    public String InsertSymbol(char symbol ,String out) {
        if (content.isEmpty()){
            out += dic.get(symbol);
            root.right = new Node(root ,null,null,symbol,"1",NYT.number - 1,1);
            content.put(symbol,NYT.right);
            root.left = new Node(root ,null,null,' ',"0",NYT.number - 2,0);
            NYT = root.left;
        }
        else if ( content.containsKey(symbol) ){    //Symbol exist
            Node SNode = content.get(symbol);
            out += SNode.code;
            SNode.setCount(SNode.count + 1);
        }
        else {      //symbol not found
            out = out + NYT.code + dic.get(symbol);
            NYT.right = new Node(NYT ,null ,null ,symbol ,NYT.code+"1" ,NYT.number - 1 ,1);
            content.put(symbol,NYT.right);
            NYT.left = new Node(NYT ,null ,null ,' ' ,NYT.code+"0"  ,NYT.number - 2 ,0);
            NYT = NYT.left;
        }
        return out;
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

    public void UpdateInsert(Node cur){
        Node r = root;
        Boolean alg1 = false;
        //Algorithm 1 **Longest Swap**
        while( !(r.right == cur || r.left == cur) ) {
            if(r.right.symbol != ' '){
                if(r.right.number > cur.number && cur.count > r.right.number){
                    alg1=true;
                    //swap
                    break;
                }
                r = r.left;
            }
            else if(r.left.symbol != ' '){
                if(r.left.number > cur.number && cur.count > r.left.number){
                    alg1=true;
                    //swap
                    break;
                }
                r = r.right;
            }
        }
        //Algorithm 2 **shortest Swap**
        Node c =cur;
        while(cur != root){
            if(c.parent.right == c){

            }
        }
    }
}
