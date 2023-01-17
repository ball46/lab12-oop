import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String [] a = {"A", "B", "C", "D", "E", "F", "G"};
        Integer [] b = {1, 2, 3, 4, 5, 6, 7};
        String [] c = {"A+1", "B*2", "C-3", "D/4", "E*2+5", "F*7-8", "G+5*2/3"};
        StringBuilder s = new StringBuilder();
        try {
            for (int i = 0; i < c.length; i++) {
                Parser p = new Parser_is_can(new Tokenizer_is_can(c[i]));
                Expr e = p.parse();
                e.prettyPrint(s);
                System.out.print(s + " = ");
                Map<String, Integer> aa = new HashMap<>();
                aa.put(a[i], b[i]);
                System.out.println(e.eval(aa));
                s.setLength(0);
            }
        } catch (SyntaxError x) {
            System.out.println(x.getMessage());
        }
    }
}