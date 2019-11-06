import java.util.ArrayList;
import java.util.HashMap;
public class Tree {
    public HashMap<Character,String> dic = new HashMap<Character, String>();    //Short code
    public HashMap<Character,Node> content = new HashMap<Character,Node>();     //each inserted character and its inserted node
    public Node root,NYT;

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
            root.count = 1;
            NYT = root.left;
        }
        else if ( content.containsKey(symbol) ){    //Symbol exist
            Node SNode = content.get(symbol);
            out += SNode.code;
            SNode.setCount(SNode.count + 1);
            UpdateInsert(SNode);
        }
        else {      //symbol not found
            out = out + NYT.code + dic.get(symbol);
            NYT.right = new Node(NYT ,null ,null ,symbol ,NYT.code+"1" ,NYT.number - 1 ,1);
            content.put(symbol,NYT.right);
            NYT.left = new Node(NYT ,null ,null ,' ' ,NYT.code+"0"  ,NYT.number - 2 ,0);
            NYT = NYT.left;
            UpdateInsert(NYT.parent.right);

        }
        return out;
    }

    public void UpdateInsert(Node cur){
        Node r = root;
        Boolean alg1 = false;
        Increment();
        //Algorithm 1 **Longest Swap**
        while( !(r.right == cur || r.left == cur) ) {
            if(r.right.symbol != ' '){
                if(r.right.number > cur.number && cur.count > r.right.count){
                    alg1=true;

                    //swap symbol
                    char temp0 = cur.symbol;
                    cur.symbol = r.right.symbol;
                    r.right.symbol = temp0;

                    //swap count
                    Integer temp2 = cur.count;
                    cur.count = r.right.count;
                    r.right.count = temp2;

                    content.put(r.right.symbol,r.right);
                    content.put(cur.symbol,cur);
                    Increment();
                    break;
                }
                r = r.left;
            }
            else if(r.left.symbol != ' '){
                if(r.left.number > cur.number && cur.count > r.left.count){
                    alg1=true;

                    //swap symbol
                    char temp0 = cur.symbol;
                    cur.symbol = r.left.symbol;
                    r.left.symbol = temp0;

                    //swap count
                    Integer temp2 = cur.count;
                    cur.count = r.left.count;
                    r.left.count = temp2;

                    content.put(r.left.symbol,r.left);
                    content.put(cur.symbol,cur);
                    Increment();
                    break;
                }
                r = r.right;
            }
        }

        if (alg1) return;
        //Algorithm 2 **shortest Swap**
        while(cur != root){
            if(cur.parent.left.count > cur.parent.right.count ){

                //swap Nodes
                Node temp = cur.parent.left;
                cur.parent.left = cur.parent.right;
                cur.parent.right = temp;

                //swap number
                Integer temp1 = cur.parent.left.number;
                cur.parent.left.number = cur.parent.right.number;
                cur.parent.right.number = temp1;

                //swap code
                String temp3 = cur.parent.left.code;
                cur.parent.left.code = cur.parent.right.code;
                cur.parent.right.code = temp3;

                FixCode(cur);
                return;
            }
            cur = cur.parent;
        }
    }

    public void Increment(){
        Node r = NYT;
        while ( !(r ==  root ) ) {
            r.parent.count = r.parent.left.count + r.parent.right.count;
            r = r.parent;
        }
    }

    public void FixCode(Node cur){
        if(cur.parent.right.symbol == ' ')  cur = cur.parent.right;
        else cur = cur.parent.left;
        while ( cur != NYT ) {
            //Fix code
            cur.right.code = cur.code + cur.right.code.substring(cur.right.code.length()-1);
            cur.left.code  = cur.code + cur.left.code.substring(cur.left.code.length()-1);

            cur = cur.left;     //or right any child
            if(cur.parent.right.symbol == ' ')  cur = cur.parent.right;
            else cur = cur.parent.left;
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
            if(P.right.symbol == ' ')  P = P.right;
            else P = P.left;
        }
    }

    public char ShortCodeKey (String shortcode){
        for (char i : dic.keySet()) {
            if (dic.get(i).matches(shortcode)) return i;
        }
        return ' ';
    }

    public char getcharfromcode (String code){
        for (char i : content.keySet()) {
            if (content.get(i).code.matches(code)) return i;
        }
        return ' ';
    }
}