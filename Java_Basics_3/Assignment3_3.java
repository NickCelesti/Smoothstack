// Nick Celesti
package Java_Basics_3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Assignment3_3 {

    // Driver Method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> text = new LinkedList<>();
        char testChar;
        int count = 0;

        // if there is at least one argument then testChar is set to the first character
        // of the first argument
        if (args.length > 0) {
            testChar = args[0].charAt(0);
        } else {
            testChar = 'e';
        }
        System.out.println("Reading char '" + testChar + "'");

        try {
            text = Files.readAllLines(Paths.get("Java_Basics_3/testFile.txt"));
            // loop through all lines, then through all characters in each line
            for (String s : text) {
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == testChar) {
                        count++;
                    }
                }
            }
            System.out.println("Count: " + count);
        } catch (IOException e) {
            e.printStackTrace();
        }

        sc.close();
    }

}
