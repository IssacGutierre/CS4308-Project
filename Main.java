import java.util.Scanner;

//import Lex.Token;

import java.nio.file.Paths;

public class Main {
    public static void main(String args[]) {
        /*try (Scanner scan = new Scanner(Paths.get(args[0]))) {
            //variable to hold input data
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return;
        } */

        //String test = "s = \"hello\""; //quotes mess it up

        //String test = "c = test(4, 5);"; //does not print out ints

        Lex lexicalA = new Lex(test);

        System.out.println(lexicalA);

    }
}