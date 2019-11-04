import java.security.Principal;
import java.util.ArrayList;

public class AdaptiveHuffmanCoding {
    public static void main(String[] args){
        Compress("ABCCCAAAA");
    }

    public static void Compress(String S){
        Tree Streamer = new Tree ();
        String out = "";
        for(int i = 0 ; i < S.length() ; ++i){
            out = Streamer.InsertSymbol(S.charAt(i) ,out) + " ";
        }
        System.out.println(out);
        //Streamer.PrintTree();
    }

    public static void Decompress(String S){

    }
}