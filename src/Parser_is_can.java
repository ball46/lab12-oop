import java.util.NoSuchElementException;

public class Parser_is_can implements Parser {
    private final Tokenizer tkz;
    public Parser_is_can(Tokenizer tkz){
        this.tkz = tkz;
    }

    /**
     * This method is call parseE
     * @parameter : none
     * @throw : SyntaxError if Exception is wrong
     * @return : the answer of the question*/
    @Override
    public Expr parse() throws SyntaxError {
        try{
            Expr ans = parseE();
            if(tkz.hasNextToken()) throw new SyntaxError("token is not null : " + tkz.peek());
            return ans;
        }catch (SyntaxError e){
            throw e;
        }
    }

    /**
     * This method is call parseT
     * @parameter : none
     * @throw : SyntaxError if Exception is wrong
     * @return : Expr e*/
    private Expr parseE() throws SyntaxError{
        try{
            Expr e = parseT();
            while (tkz.hasNextToken() && (tkz.peek("+") || tkz.peek("-"))){
                if(tkz.peek("+")){
                    tkz.consume();
                    e = new BinaryArithExpr(e, "+", parseT());
                } else if (tkz.peek("-")) {
                    tkz.consume();
                    e = new BinaryArithExpr(e, "-", parseT());
                }
            }
            return e;
        } catch (IllegalArgumentException | NoSuchElementException e){
            throw new SyntaxError(e.getMessage());
        }
    }

    /**
     * This method is call parseF
     * @parameter : none
     * @throw : SyntaxError if Exception is wrong
     * @return : Expr e*/
    private Expr parseT() throws SyntaxError{
        try{
            Expr e = parseF();
            while (tkz.hasNextToken() &&(tkz.peek("*") || tkz.peek("/") || tkz.peek("%"))){
                if(tkz.peek("*")){
                    tkz.consume();
                    e = new BinaryArithExpr(e, "*", parseF());
                } else if (tkz.peek("/")) {
                    tkz.consume();
                    e = new BinaryArithExpr(e, "/", parseF());
                } else if (tkz.peek("%")) {
                    tkz.consume();
                    e = new BinaryArithExpr(e, "%", parseF());
                }
            }
            return e;
        }catch (IllegalArgumentException | NoSuchElementException e){
            throw new SyntaxError(e.getMessage());
        }
    }

    /**
     * This method is checked tkz
     * @parameter : none
     * @throw : SyntaxError if Exception is wrong
     * @return : In if case return new IntLit but in else case return Expr e*/
    private Expr parseF() throws SyntaxError{
        try{
            if(Character.isDigit(tkz.peek().charAt(0))){
                return new IntLit(Integer.parseInt(tkz.consume()));
            }else if(Character.isAlphabetic(tkz.peek().charAt(0))){
                return new Variable(tkz.consume());
            } else {
                Expr e = null;
                if(tkz.peek("(")) {
                    tkz.consume("(");
                    e = parseE();
                    tkz.consume(")");
                }
                return e;
            }
        }catch (NoSuchElementException | NumberFormatException | SyntaxError | EvalError s){
            throw new SyntaxError(s.getMessage());
        }
    }
}
