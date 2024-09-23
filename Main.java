import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.charset.*;

public class Main {
    public static void main(String args[]) throws IOException {
        String content = Files.readString(Paths.get(args[0]), StandardCharsets.UTF_8);

        Lex lexicalA = new Lex(content);
        System.out.println(lexicalA);
    }
}