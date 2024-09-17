import java.util.Scanner;
import java.nio.file.Paths;

public class Main {
    public static void main(String args[]) {
        try (Scanner scan = new Scanner(Paths.get(args[0]))) {
            //variable to hold input data
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }
    }
}