import java.util.Map;

public class BinaryArithExpr implements Expr{
    private final Expr left, right;
    private final String op;
    public BinaryArithExpr(Expr left, String op, Expr right){
        this.left = left;
        this.op = op;
        this.right = right;
    }
    @Override
    public void prettyPrint(StringBuilder s) {
        s.append("(");
        left.prettyPrint(s);
        s.append(op);
        right.prettyPrint(s);
        s.append(")");
    }

    @Override
    public int eval(Map<String, Integer> bindings) throws ArithmeticException {
        int lv = left.eval(bindings);
        int rv = right.eval(bindings);
        switch (op) {
            case "+" -> {
                return lv + rv;
            }
            case "-" -> {
                return lv - rv;
            }
            case "*" -> {
                return lv * rv;
            }
            case "/" -> {
                if (rv == 0) throw new ArithmeticException("input is error : " + lv + " / " + rv);
                return lv / rv;
            }
            case "%" -> {
                if (rv == 0) throw new ArithmeticException("input is error : " + lv + " % " + rv);
                return lv % rv;
            }
        }
        throw new ArithmeticException("input is error");
    }
}
