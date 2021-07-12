// Nick Celesti
package Java_Basics_3;

import java.io.File;
import java.util.Scanner;

public class Assignment3_1 {

    static void printDirectory(File[] arr, int tabLevel) {

        for (int i = 0; i < arr.length; i++) {
            // tabbing
            for (int j = 0; j < tabLevel; j++) {
                System.out.print("\t");
            }

            // files
            if (arr[i].isFile())
                System.out.println(arr[i].getName());

            // folders
            else if (arr[i].isDirectory()) {
                System.out.println("[" + arr[i].getName() + "]");

                // recursion
                printDirectory(arr[i].listFiles(), tabLevel + 1);
            }
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String path;

        System.out.print("Enter path (leave empty for project directory): ");
        path = sc.nextLine();
        // File object
        File directory = new File(path);

        if (!directory.isDirectory()) {
            String cwPath = new File("").getAbsolutePath();
            directory = new File(cwPath);

        }

        System.out.println("Directory: " + directory);

        printDirectory(directory.listFiles(), 0);

        sc.close();
    }

}
