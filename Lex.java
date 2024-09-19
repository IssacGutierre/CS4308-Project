import java.util.*;

public class Lex {

    private List<Token> tokens;

    public Lex(String input) {
        tokens = lex(input);
    }

    // Token codes
    public static enum Type {
        T_AND, T_ASSIGN, T_BOOLTYPE, T_BREAK, T_CHARCONSTANT, T_COMMA, T_COMMENT,
        T_CONTINUE, T_DIV, T_DOT, T_ELSE, T_EQ, T_EXTERN, T_FALSE, T_FOR,
        T_FUNC, T_GEQ, T_GT, T_ID, T_IF, T_INTCONSTANT, T_INTTYPE, T_LCB, 
        T_LEFTSHIFT, T_LEQ, T_LPAREN, T_LSB, T_LT, T_MINUS, T_MOD, T_MULT,
        T_NEQ, T_NOT, T_NULL, T_OR, T_PACKAGE, T_PLUS, T_RCB, T_RETURN, 
        T_RIGHTSHIFT, T_RPAREN, T_RSB, T_SEMICOLON, T_STRINGCONSTANT, 
        T_STRINGTYPE, T_TRUE, T_VAR, T_VOID, T_WHILE, T_WHITESPACE, 
        T_IDENTIFIER; 
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
            //if (t == Type.T_STRINGCONSTANT) {
            //    return "T_STRINGCONSTANT " + "(value= " + c + ")";
            //}
            //return t.toString();
            return c + "    " + "***coordinates***" + " is " + t;
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
            case '=':
                result.add(new Token(Type.T_ASSIGN, "="));
                i++;
                break;
            case ',':
                result.add(new Token(Type.T_COMMA, ","));
                i++;
                break;
            case '/':
                result.add(new Token(Type.T_DIV, "/"));
                i++;
                break;
            case '.':
                result.add(new Token(Type.T_DOT, "."));
                i++;
                break;
            case '>':
                result.add(new Token(Type.T_GT, ">"));
                i++;
                break;
            case '{':
                result.add(new Token(Type.T_LCB, "{"));
                i++;
                break;
            case '(':
                result.add(new Token(Type.T_LPAREN, "("));
                i++;
                break;
            case '[':
                result.add(new Token(Type.T_LSB, "["));
                i++;
                break;
            case '<':
                result.add(new Token(Type.T_LT, "<"));
                i++;
                break;
            case '-':
                result.add(new Token(Type.T_MINUS, "-"));
                i++;
                break;
            case '%':
                result.add(new Token(Type.T_MOD, "%"));
                i++;
                break;
            case '*':
                result.add(new Token(Type.T_MULT, "*"));
                i++;
                break;
            case '!':
                result.add(new Token(Type.T_NOT, "!"));
                i++;
                break;
            case '+':
                result.add(new Token(Type.T_PLUS, "+"));
                i++;
                break;
            case '}':
                result.add(new Token(Type.T_RCB, "}"));
                i++;
                break;
            case ')':
                result.add(new Token(Type.T_RPAREN, ")"));
                i++;
                break;
            case ';':
                result.add(new Token(Type.T_SEMICOLON, ";"));
                i++;
                break;
            default: //ingoring white space 
                if(Character.isWhitespace(input.charAt(i))) {
                    i++;
                } else {
                    String nonCharacter = getChar(input, i);
                    i += nonCharacter.length();
                    
                    switch(nonCharacter) {
                    case "&&":
                        result.add(new Token(Type.T_AND, nonCharacter));
                        i++;
                        break;
                    case "bool":
                        result.add(new Token(Type.T_BOOLTYPE, nonCharacter));
                        i++;
                        break;
                    case "break":
                        result.add(new Token(Type.T_BREAK, nonCharacter));
                        i++;
                        break;
                    case "char_lit":
                        result.add(new Token(Type.T_CHARCONSTANT, nonCharacter));
                        i++;
                        break;
                    case "continue":
                        result.add(new Token(Type.T_CONTINUE, nonCharacter));
                        i++;
                        break;
                    case "else":
                        result.add(new Token(Type.T_ELSE, nonCharacter));
                        i++;
                        break;
                    case "==":
                        result.add(new Token(Type.T_EQ, nonCharacter));
                        i++;
                        break;
                    case "extern":
                        result.add(new Token(Type.T_EXTERN, nonCharacter));
                        i++;
                        break;
                    case "false":
                        result.add(new Token(Type.T_FALSE, nonCharacter));
                        i++;
                        break;
                    case "for":
                        result.add(new Token(Type.T_FOR, nonCharacter));
                        i++;
                        break;
                    case "func":
                        result.add(new Token(Type.T_FUNC, nonCharacter));
                        i++;
                        break;
                    case ">=":
                        result.add(new Token(Type.T_GEQ, nonCharacter));
                        i++;
                        break;
                    case "identifier":
                        result.add(new Token(Type.T_ID, nonCharacter));
                        i++;
                        break;
                    case "if":
                        result.add(new Token(Type.T_IF, nonCharacter));
                        i++;
                        break;
                    case "int_lit":
                        result.add(new Token(Type.T_INTCONSTANT, nonCharacter));
                        i++;
                        break;
                    case "int":
                        result.add(new Token(Type.T_INTTYPE, nonCharacter));
                        i++;
                        break;
                    case "<<":
                        result.add(new Token(Type.T_LEFTSHIFT, nonCharacter));
                        i++;
                        break;
                    case "<=":
                        result.add(new Token(Type.T_LEQ, nonCharacter));
                        i++;
                        break;
                    case "!=":
                        result.add(new Token(Type.T_NEQ, nonCharacter));
                        i++;
                        break;
                    case "null":
                        result.add(new Token(Type.T_NULL, nonCharacter));
                        i++;
                        break;
                    case "||":
                        result.add(new Token(Type.T_OR, nonCharacter));
                        i++;
                        break;
                    case "package":
                        result.add(new Token(Type.T_PACKAGE, nonCharacter));
                        i++;
                        break;
                    case "return":
                        result.add(new Token(Type.T_RETURN, nonCharacter));
                        i++;
                        break;
                    case ">>":
                        result.add(new Token(Type.T_RIGHTSHIFT, nonCharacter));
                        i++;
                        break;
                    case "string_lit":
                        result.add(new Token(Type.T_STRINGCONSTANT, nonCharacter));
                        i++;
                        break;
                    case "string":
                        result.add(new Token(Type.T_STRINGTYPE, nonCharacter));
                        i++;
                        break;
                    case "true":
                        result.add(new Token(Type.T_TRUE, nonCharacter));
                        i++;
                        break;
                    case "var":
                        result.add(new Token(Type.T_VAR, nonCharacter));
                        i++;
                        break;
                    case "void":
                        result.add(new Token(Type.T_VOID, nonCharacter));
                        i++;
                        break;
                    case "while":
                        result.add(new Token(Type.T_WHILE, nonCharacter));
                        i++;
                        break;
                    default:
                        result.add(new Token(Type.T_IDENTIFIER, nonCharacter));
                        i++;
                        break;
                    }
                }
                break;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Token t : tokens) {
            sb.append(t).append("\n"); // Append each token's string representation
        }
        return sb.toString(); // Return the final string
    }
}
