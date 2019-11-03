import java.security.Principal;

public class AdaptiveHuffmanCoding {
    public static void main(String[] args){
        Tree Streamer = new Tree ();
        String code = "ABCAABBCC";
        for(int i = 0 ; i < code.length() ; ++i){
            Streamer.InsertSymbol(code.charAt(i));
        }
        Streamer.PrintTree();
    }
}
