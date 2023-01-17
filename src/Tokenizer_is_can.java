import java.util.NoSuchElementException;

public class Tokenizer_is_can implements Tokenizer {
    private final String src;
    private String next;
    private int pos;
    public Tokenizer_is_can(String src){
        this.src = src;
        pos = 0;
        computeNext();
    }

    /**
     * This method is checked next token
     * @parameter : none
     * @return : true if there is more token*/
    @Override
    public boolean hasNextToken() {
        return next != null;
    }

    /**
     * This method is return String next
     * @parameter : none
     * @throw : NoSuchElementException if next token is null
     * @return : the next token in the input stream*/
    @Override
    public String peek() throws NoSuchElementException {
        if(!hasNextToken()) throw new NoSuchElementException("no more tokens");
        return next;
    }

    /**
     * This method is return next and change next to the next token
     * @parameter : none
     * @throw : NoSuchElementException if next token is null
     * @sideeffect : next is change to the next token
     * @return : the next token in the input stream*/
    @Override
    public String consume() throws NoSuchElementException {
        if(!hasNextToken()) throw new NoSuchElementException("no more tokens");
        String result = next;
        computeNext();
        return result;
    }

    /**
     * This method is checked next is equal or unequal s
     * @parameter : String s
     * @return : true if the next token is s*/
    @Override
    public boolean peek(String s) {
        return peek().equals(s);
    }

    /**
     * This method is consumes the next token if it s
     * @parameter : String s
     * @throw : SyntaxError if next token is not s
     * @sideeffect : next is change to the next token
     * @return : the next token*/
    @Override
    public void consume(String s) throws SyntaxError {
        if(peek(s)) consume();
        else throw new SyntaxError(s + " expected");
    }

    /**
     * This method is computed the token to filter
     * @parameter : none
     * @throw : IllegalArgumentException if Character in src is unknown
     * @sideeffect : next is change
     * @return : none*/
    private void computeNext() throws IllegalArgumentException{
        StringBuilder s = new StringBuilder();
        while (pos < src.length() && Character.isWhitespace(src.charAt(pos))) pos++;
        if(pos == src.length()) {
            next = null;
            return;
        }
        char c = src.charAt(pos);
        if(Character.isDigit(c)){
            s.append(c);
            for(pos++; pos < src.length() && Character.isDigit(src.charAt(pos)); pos++) {
                s.append(src.charAt(pos));
            }
        }else if(c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c == '(' || c == ')'){
            s.append(c);
            pos++;
        }else if(Character.isAlphabetic(c)){
            s.append(c);
            for(pos++; pos < src.length() && Character.isAlphabetic(src.charAt(pos)); pos++) {
                s.append(src.charAt(pos));
            }
        } else throw new IllegalArgumentException("Illegal character: " + c);
        next = s.toString();
    }
}
