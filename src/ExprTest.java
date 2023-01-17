import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ExprTest {
    @Test
    void IntLit_print() throws SyntaxError{
        StringBuilder s = new StringBuilder();
        String[] test = {"1","25","300","49","99"};
        int i = 0;
        for(String str : test){
            Parser p = new Parser_is_can(new Tokenizer_is_can(str));
            Expr e = p.parse();
            //Test for pretty print
            e.prettyPrint(s);
            assertEquals(test[i],s.toString());
            s.setLength(0);
            i++;
        }
    }
    @Test
    void IntLit_eval() throws SyntaxError{
        Map<String, Integer> a = new HashMap<>();
        String[] test = {"1","25","300","49","99"};
        int[] expected = {1,25,300,49,99};
        int i = 0;
        for(String str : test){
            Parser p = new Parser_is_can(new Tokenizer_is_can(str));
            Expr e = p.parse();
            //Test for eval
            assertEquals(expected[i],e.eval(a));
            i++;
        }
    }
    @Test
    void Variable_print() throws SyntaxError{
        StringBuilder s = new StringBuilder();
        String [] b = {"A", "B", "C", "D", "E", "F", "G"};
        int i = 0;
        for(String str : b){
            Parser p = new Parser_is_can(new Tokenizer_is_can(str));
            Expr e = p.parse();
            e.prettyPrint(s);
            //Test for pretty print
            assertEquals(b[i],s.toString());
            s.setLength(0);
            i++;
        }
    }
    @Test
    void Variable_eval() throws SyntaxError{
        StringBuilder s = new StringBuilder();
        Map<String, Integer> a = new HashMap<>();
        String [] b = {"A", "B", "C", "D", "E", "F", "G"};
        Integer [] c = {1, 2, 3, 4, 5, 6, 7};
        int[] expected = {1,2,3,4,5,6,7};
        int i = 0;
        for(String str : b){
            Parser p = new Parser_is_can(new Tokenizer_is_can(str));
            Expr e = p.parse();
            e.prettyPrint(s);
            a.put(str, c[i]);
            //Test for pretty print
            assertEquals(expected[i],e.eval(a));
            i++;
        }
    }
    @Test
    void BinaryArithExpr_noVariable() throws SyntaxError{
        String[] test = {"1+1", "1+1*7", "1/1", "(5-4)*2", "1+2*3+4", "1+2*3+4/5", "1+2*3+4*5*6"};
        String[] ans = {"(1+1)", "(1+(1*7))", "(1/1)", "((5-4)*2)", "((1+(2*3))+4)", "((1+(2*3))+(4/5))", "((1+(2*3))+((4*5)*6))"};
        int[] evalAns = {2, 8, 1, 2, 11, 7, 127};
        int i = 0;
        for (String str : test) {
            Parser p = new Parser_is_can(new Tokenizer_is_can(str));
            StringBuilder s = new StringBuilder();
            Map<String, Integer> a = new HashMap<>();
            Expr e = p.parse();
            e.prettyPrint(s);
            //Test for pretty print
            assertEquals(ans[i], s.toString());
            //Test for eval
            assertEquals(evalAns[i], e.eval(a));
            i++;
        }
    }
    @Test
    void BinaryArithExpr_Variable() throws SyntaxError{
        Map<String, Integer> a = new HashMap<>();
        String [] b = {"A", "B", "C", "D", "E", "F", "G"};
        Integer [] c = {1, 2, 3, 4, 5, 6, 7};
        String[] test = {"A+B", "A-C", "B*D", "E/F", "G*G", "A+B%C", "A+A*A/G+B"};
        String[] ans = {"(A+B)", "(A-C)", "(B*D)", "(E/F)", "(G*G)", "(A+(B%C))", "((A+((A*A)/G))+B)"};
        int[] evalAns = {3, -2, 8, 0, 49, 3, 3};
        for(int j = 0; j< b.length; j++) a.put(b[j], c[j]);
        int i = 0;
        for (String str : test) {
            Parser p = new Parser_is_can(new Tokenizer_is_can(str));
            StringBuilder s = new StringBuilder();
            Expr e = p.parse();
            e.prettyPrint(s);
            //Test for pretty print
            assertEquals(ans[i], s.toString());
            //Test for eval
            assertEquals(evalAns[i], e.eval(a));
            i++;
        }
    }
    @Test
    void BinaryArithExpr() throws SyntaxError{
        Map<String, Integer> a = new HashMap<>();
        String [] b = {"A", "B", "C", "D", "E", "F", "G"};
        Integer [] c = {1, 2, 3, 4, 5, 6, 7};
        String[] test = {"A+2*B+1", "D/B+C*2", "B*B-C*C", "G*A/2-1", "B-2*A+1", "C%2"};
        String[] ans = {"((A+(2*B))+1)", "((D/B)+(C*2))", "((B*B)-(C*C))", "(((G*A)/2)-1)", "((B-(2*A))+1)", "(C%2)"};
        int[] evalAns = {6, 8, -5, 2, 1, 1};
        for(int j = 0; j< b.length; j++) a.put(b[j], c[j]);
        int i = 0;
        for (String str : test) {
            Parser p = new Parser_is_can(new Tokenizer_is_can(str));
            StringBuilder s = new StringBuilder();
            Expr e = p.parse();
            e.prettyPrint(s);
            //Test for pretty print
            assertEquals(ans[i], s.toString());
            //Test for eval
            assertEquals(evalAns[i], e.eval(a));
            i++;
        }
    }
    @Test
    void BinaryArithExpr_Error() throws SyntaxError{
        Map<String, Integer> a = new HashMap<>();
        String[] test = {"A+2*B+1", "D/B+C*2", "B*B-C*C", "G*A/2-1", "B-2*A+1", "C%2"};
        for (String str : test) {
            Parser p = new Parser_is_can(new Tokenizer_is_can(str));
            StringBuilder s = new StringBuilder();
            Expr e = p.parse();
            e.prettyPrint(s);
            assertThrows(EvalError.class, () -> e.eval(a));
        }
    }
}