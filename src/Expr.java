import java.util.Map;

public interface Expr {
    void prettyPrint(StringBuilder s);
    int eval(Map<String, Integer> bindings);
}
