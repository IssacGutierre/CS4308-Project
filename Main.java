import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.charset.*;

/*to run make sure the t1.decaf file is in the project directory 
 * 1) compile ($ javac Main.java)
 * 2) execute ($ java Main t1.decaf)
 * NOTE: -the column numbers are wrong, 
 *       -does not pick up certain characters
 *       -string constant and int constant not implemented yet
*/

public class Main {
    public static void main(String args[]) throws IOException {
        String content = Files.readString(Paths.get(args[0]), StandardCharsets.UTF_8);

        Lex lexicalA = new Lex(content);
        System.out.println(lexicalA);
    }
}