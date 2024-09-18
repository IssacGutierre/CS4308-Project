import java.util.*;

public class Lex {

    // Token codes
    public static enum Type {
        T_AND, // &&
        T_ASSIGN, // =
        T_BOOLTYPE, // bool
        T_BREAK, // break
        T_CHARCONSTANT, // char_lit
        T_COMMA, // ,
        T_COMMENT, // comment
        T_CONTINUE, // continue
        T_DIV, // /
        T_DOT, // .
        T_ELSE, // else
        T_EQ, // ==
        T_EXTERN, // extern
        T_FALSE, // false
        T_FOR, // for
        T_FUNC, // func
        T_GEQ, // >=
        T_GT, // >
        T_ID, // identifier
        T_IF, // if
        T_INTCONSTANT, // int_lit
        T_INTTYPE, // int
        T_LCB, // {
        T_LEFTSHIFT, // <<
        T_LEQ, // <=
        T_LPAREN, // (
        T_LSB, // [
        T_LT, // <
        T_MINUS, // -
        T_MOD, // %
        T_MULT, // *
        T_NEQ, // !=
        T_NOT, // !
        T_NULL, // null
        T_OR, // ||
        T_PACKAGE, // package
        T_PLUS, // +
        T_RCB, // }
        T_RETURN, // return
        T_RIGHTSHIFT, // >>
        T_RPAREN, // )
        T_RSB, // ]
        T_SEMICOLON, // ;
        T_STRINGCONSTANT, // string_lit
        T_STRINGTYPE, // string
        T_TRUE, // true
        T_VAR, // var
        T_VOID, // void
        T_WHILE, // while
        T_WHITESPACE; // whitespace
    }

    public static class Token {
        public final Type t;
        public final String c;

        //Constructor
        public Token(Type t, String c) {
            this.t = t;
            this.c = c;
        }

        public String toString() {
            if (t == Type.T_STRINGCONSTANT) {
                return "T_STRINGCONSTANT " + "(value= " + c + ")";
            }
            return t.toString();
        }
    }

    //example: .getChar("void main() {", 5) --> returns "main"
    public String getChar(String s, int i) {
        int j = i;
        for (; j < s.length();) { //iterate until you hit an index that is NOT a letter
            if (Character.isLetter(s.charAt(j))) {
                j++;
            } else {
                return s.substring(i, j);
            }
        }
        return s.substring(i, j);
    }

    //method that returns a list containing Token objects 
    public List<Token> lex(String input) {
        List<Token> result = new ArrayList<Token>();
        for(int i = 0; i < input.length(); ) {
            switch(input.charAt(i)) {
            case '&&':
                result.add(new Token(Type.T_AND, "&&"));
                i++;
                break;
            case '=':
                result.add(new Token(Type.T_ASSIGN, "="));
                i++;
                break;
            case 'bool':
                result.add(new Token(Type.T_BOOLTYPE, "bool"));
                i++;
                break;
            case 'break':
                result.add(new Token(Type.T_BREAK, "break"));
                i++;
                break;
            case 'char_lit':
                result.add(new Token(Type.T_CHARCONSTANT, "char_lit"));
                i++;
                break;
            case ',':
                result.add(new Token(Type.T_COMMA, ","));
                i++;
                break;
            case 'continue':
                result.add(new Token(Type.T_CONTINUE, "continue"));
                i++;
                break;
            case '/':
                result.add(new Token(Type.T_DIV, "/"));
                i++;
                break;
            default: //ingoring white space 
                if(Character.isWhitespace(input.charAt(i))) {
                    i++;
                }
                break;
            }
        }
        return result;
    }
}
