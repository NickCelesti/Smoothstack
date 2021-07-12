// Nick Celesti
package Java_Basics_3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class Assignment3_2 {

    // Driver Method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text;
        System.out.print("Enter text to append: ");
        text = sc.nextLine();

        // appends text to the end of testFile.txt
        try {
            Files.write(Paths.get("Java_Basics_3/testFile.txt"), text.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }

        sc.close();
    }

}
