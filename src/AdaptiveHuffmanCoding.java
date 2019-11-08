import java.security.Principal;
import java.util.ArrayList;

public class AdaptiveHuffmanCoding {
    public static void main(String[] args){
        System.out.println( Compress("ABCCCAAAA") );
        System.out.println( Decompress( Compress("ABCCCAAAA") ) );
    }

    public static String Compress(String S){
        Tree comp = new Tree ();
        String out = "";
        for(int i = 0 ; i < S.length() ; ++i){
            out = comp.InsertSymbol(S.charAt(i) ,out);
        }
        return out;
    }

    public static String Decompress(String S){
        String out ="";
        Tree decom = new Tree ();
        String x = ""; //don't care

        /*first symbol*/
        char symbol = decom.ShortCodeKey(S.substring(0 ,8) );
        x = decom.InsertSymbol(symbol ,x);
        out +=symbol;

        /*remaining*/
        int i=8; //cursor on the String
        Boolean flag = false;
        while(!flag){
            if (S.length()-i >=decom.NYT.code.length()){
                x = S.substring(i ,i+decom.NYT.code.length());
                i+=decom.NYT.code.length();
            }
            else{
                x=S.substring(i);
                i+=x.length();
            }

            if(decom.NYT.code.matches(x)){
                String Shortcode = S.substring(i,i+8);
                i+=8;
                symbol = decom.ShortCodeKey(Shortcode);
                out +=symbol;
                x = decom.InsertSymbol(symbol ,x);
            }

            else{
                while (true){
                    symbol = decom.getcharfromcode(x);
                    if (symbol !=' ')   {
                        x = decom.InsertSymbol(symbol ,x);
                        out +=symbol;
                        break;
                    }
                    else{
                        i--;
                        x=x.substring(0 ,x.length()-1);
                    }
                }
            }
            if (i == S.length()) flag = true;
        }
        return out;
    }
}