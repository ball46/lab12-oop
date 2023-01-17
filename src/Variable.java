import java.util.Map;

public class Variable implements Expr{
    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public void prettyPrint(StringBuilder s) {
        s.append(name);
    }

    @Override
    public int eval(Map<String, Integer> bindings) throws EvalError {
        if(bindings.containsKey(name)) return bindings.get(name);
        throw new EvalError("undefined variable: " + name);
    }
}
