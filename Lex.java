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
        public final int line;
        public final int columnStart;
        public final int columnEnd;

        // Constructor
        public Token(Type t, String c, int line, int columnStart, int columnEnd) {
            this.t = t;
            this.c = c;
            this.line = line;
            this.columnStart = columnStart;
            this.columnEnd = columnEnd;

        }

        public String toString() {
            // if (t == Type.T_STRINGCONSTANT) {
            // return "T_STRINGCONSTANT " + "(value= " + c + ")";
            // }
            // return t.toString();
            if ((c.toString()).length() == 1) {
                char cToChar = c.charAt(0);
                if (Character.isLetter(cToChar)) {
                    return c + "    " + "line " + line + " Cols " + columnStart + " - " + columnEnd + " is " + t;
                } else {
                    return c + "    " + "line " + line + " Cols " + columnStart + " - " + columnEnd + " is " + "\'" + c
                            + "\'";
                }
            } else {
                return c + "    " + "line " + line + " Cols " + columnStart + " - " + columnEnd + " is " + t;
            }
        }
    }

    // EXAMPLE: .getChar("void main() {", 5) --> returns "main"
    public String getChar(String s, int i) {
        int j = i;
        for (; j < s.length();) { // iterate until you hit an index that is NOT a letter
            if (Character.isLetter(s.charAt(j))) {
                j++;
            } else {
                return s.substring(i, j);
            }
        }
        return s.substring(i, j);
    }

    // method that returns a list containing Token objects
    public List<Token> lex(String input) {
        int line = 1;
        int column = 1;
        List<Token> result = new ArrayList<Token>();

        for (int i = 0; i < input.length();) {

            if (input.charAt(i) == '\n') {
                line++;
                column = 1;
                i++;
                continue;
            }
            int columnStart = column;

            switch (input.charAt(i)) {
                case '=':
                    result.add(new Token(Type.T_ASSIGN, "=", line, column, column));
                    i++;
                    column++;
                    break;
                case ',':
                    result.add(new Token(Type.T_COMMA, ",", line, column, column));
                    i++;
                    column++;
                    break;
                case '/':
                    result.add(new Token(Type.T_DIV, "/", line, column, column));
                    i++;
                    column++;
                    break;
                case '.':
                    result.add(new Token(Type.T_DOT, ".", line, column, column));
                    i++;
                    column++;
                    break;
                case '>':
                    result.add(new Token(Type.T_GT, ">", line, column, column));
                    i++;
                    column++;
                    break;
                case '{':
                    result.add(new Token(Type.T_LCB, "{", line, column, column));
                    i++;
                    column++;
                    break;
                case '(':
                    result.add(new Token(Type.T_LPAREN, "(", line, column, column));
                    i++;
                    column++;
                    break;
                case '[':
                    result.add(new Token(Type.T_LSB, "[", line, column, column));
                    i++;
                    column++;
                    break;
                case '<':
                    result.add(new Token(Type.T_LT, "<", line, column, column));
                    i++;
                    column++;
                    break;
                case '-':
                    result.add(new Token(Type.T_MINUS, "-", line, column, column));
                    i++;
                    column++;
                    break;
                case '%':
                    result.add(new Token(Type.T_MOD, "%", line, column, column));
                    i++;
                    column++;
                    break;
                case '*':
                    result.add(new Token(Type.T_MULT, "*", line, column, column));
                    i++;
                    column++;
                    break;
                case '!':
                    result.add(new Token(Type.T_NOT, "!", line, column, column));
                    i++;
                    column++;
                    break;
                case '+':
                    result.add(new Token(Type.T_PLUS, "+", line, column, column));
                    i++;
                    column++;
                    break;
                case '}':
                    result.add(new Token(Type.T_RCB, "}", line, column, column));
                    i++;
                    column++;
                    break;
                case ')':
                    result.add(new Token(Type.T_RPAREN, ")", line, column, column));
                    i++;
                    column++;
                    break;
                case ';':
                    result.add(new Token(Type.T_SEMICOLON, ";", line, column, column));
                    i++;
                    column++;
                    break;
                default: // ingoring white space
                    if (Character.isWhitespace(input.charAt(i))) {
                        i++;
                        column++;
                    } else {
                        String nonCharacter = getChar(input, i);
                        i += nonCharacter.length();

                        switch (nonCharacter) {
                            case "&&":
                                column += nonCharacter.length();
                                result.add(new Token(Type.T_AND, nonCharacter, line, columnStart, column));
                                i++;
                                break;
                            case "bool":
                                column += nonCharacter.length();
                                result.add(new Token(Type.T_BOOLTYPE, nonCharacter, line, columnStart, column));
                                i++;
                                break;
                            case "break":
                                column += nonCharacter.length();
                                result.add(new Token(Type.T_BREAK, nonCharacter, line, columnStart, column));
                                i++;
                                break;
                            case "char_lit":
                                column += nonCharacter.length();
                                result.add(new Token(Type.T_CHARCONSTANT, nonCharacter, line, columnStart, column));
                                i++;
                                break;
                            case "continue":
                                column += nonCharacter.length();
                                result.add(new Token(Type.T_CONTINUE, nonCharacter, line, columnStart, column));
                                i++;
                                break;
                            case "else":
                                column += nonCharacter.length();
                                result.add(new Token(Type.T_ELSE, nonCharacter, line, columnStart, column));
                                i++;
                                break;
                            case "==":
                                column += nonCharacter.length();
                                result.add(new Token(Type.T_EQ, nonCharacter, line, columnStart, column));
                                i++;
                                break;
                            case "extern":
                                column += nonCharacter.length();
                                result.add(new Token(Type.T_EXTERN, nonCharacter, line, columnStart, column));
                                i++;
                                break;
                            case "false":
                                column += nonCharacter.length();
                                result.add(new Token(Type.T_FALSE, nonCharacter, line, columnStart, column));
                                i++;
                                break;
                            case "for":
                                column += nonCharacter.length();
                                result.add(new Token(Type.T_FOR, nonCharacter, line, columnStart, column));
                                i++;
                                break;
                            case "func":
                                column += nonCharacter.length();
                                result.add(new Token(Type.T_FUNC, nonCharacter, line, columnStart, column));
                                i++;
                                break;
                            case ">=":
                                column += nonCharacter.length();
                                result.add(new Token(Type.T_GEQ, nonCharacter, line, columnStart, column));
                                i++;
                                break;
                            case "identifier":
                                column += nonCharacter.length();
                                result.add(new Token(Type.T_ID, nonCharacter, line, columnStart, column));
                                i++;
                                break;
                            case "if":
                                column += nonCharacter.length();
                                result.add(new Token(Type.T_IF, nonCharacter, line, columnStart, column));
                                i++;
                                break;
                            case "int_lit":
                                column += nonCharacter.length();
                                result.add(new Token(Type.T_INTCONSTANT, nonCharacter, line, columnStart, column));
                                i++;
                                break;
                            case "int":
                                column += nonCharacter.length();
                                result.add(new Token(Type.T_INTTYPE, nonCharacter, line, columnStart, column));
                                i++;
                                break;
                            case "<<":
                                column += nonCharacter.length();
                                result.add(new Token(Type.T_LEFTSHIFT, nonCharacter, line, columnStart, column));
                                i++;
                                break;
                            case "<=":
                                column += nonCharacter.length();
                                result.add(new Token(Type.T_LEQ, nonCharacter, line, columnStart, column));
                                i++;
                                break;
                            case "!=":
                                column += nonCharacter.length();
                                result.add(new Token(Type.T_NEQ, nonCharacter, line, columnStart, column));
                                i++;
                                break;
                            case "null":
                                column += nonCharacter.length();
                                result.add(new Token(Type.T_NULL, nonCharacter, line, columnStart, column));
                                i++;
                                break;
                            case "||":
                                column += nonCharacter.length();
                                result.add(new Token(Type.T_OR, nonCharacter, line, columnStart, column));
                                i++;
                                break;
                            case "package":
                                column += nonCharacter.length();
                                result.add(new Token(Type.T_PACKAGE, nonCharacter, line, columnStart, column));
                                i++;
                                break;
                            case "return":
                                column += nonCharacter.length();
                                result.add(new Token(Type.T_RETURN, nonCharacter, line, columnStart, column));
                                i++;
                                break;
                            case ">>":
                                column += nonCharacter.length();
                                result.add(new Token(Type.T_RIGHTSHIFT, nonCharacter, line, columnStart, column));
                                i++;
                                break;
                            case "string_lit":
                                column += nonCharacter.length();
                                result.add(new Token(Type.T_STRINGCONSTANT, nonCharacter, line, columnStart, column));
                                i++;
                                break;
                            case "string":
                                column += nonCharacter.length();
                                result.add(new Token(Type.T_STRINGTYPE, nonCharacter, line, columnStart, column));
                                i++;
                                break;
                            case "true":
                                column += nonCharacter.length();
                                result.add(new Token(Type.T_TRUE, nonCharacter, line, columnStart, column));
                                i++;
                                break;
                            case "var":
                                column += nonCharacter.length();
                                result.add(new Token(Type.T_VAR, nonCharacter, line, columnStart, column));
                                i++;
                                break;
                            case "void":
                                column += nonCharacter.length();
                                result.add(new Token(Type.T_VOID, nonCharacter, line, columnStart, column));
                                i++;
                                break;
                            case "while":
                                column += nonCharacter.length();
                                result.add(new Token(Type.T_WHILE, nonCharacter, line, columnStart, column));
                                i++;
                                break;
                            default:
                                column += nonCharacter.length();
                                result.add(new Token(Type.T_IDENTIFIER, nonCharacter, line, columnStart, column));
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
