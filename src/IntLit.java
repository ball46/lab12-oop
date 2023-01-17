import java.util.Map;

public class IntLit implements Expr{
    private final int val;
    public IntLit(int val){
        this.val = val;
    }
    @Override
    public void prettyPrint(StringBuilder s) {
        s.append(val);
    }

    @Override
    public int eval(Map<String, Integer> bindings) {
        return val;
    }
}
