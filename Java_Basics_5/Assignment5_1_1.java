package Java_Basics_5;

import java.util.Arrays;
import java.util.Comparator;

class StringComp implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        if ((o1.contains("e") || o1.contains("E")) && (o2.contains("e") || o2.contains("E"))) {
            return 0;
        } else if (o1.contains("e") || o1.contains("E")) {
            return -1;
        } else {
            return 1;
        }

    }

}

public class Assignment5_1_1 {
    public static void main(String[] args) {
        // Lambdas and funtional interfaces and streams
        // -----------------------------------------------------
        String[] names = { "Fred", "Eve", "Avery", "Maria", "Adam", "Nicole", "Xavier" };
        // -----------------------------------------------------
        // 1.

        // sort by length
        Arrays.sort(names, (o1, o2) -> (o1.length() - o2.length()));
        System.out.println("Shortest to longest: ");
        for (String s : names) {
            System.out.println(s);
        }

        System.out.println();

        // sort by reverse length
        Arrays.sort(names, (o1, o2) -> (o2.length() - o1.length()));
        System.out.println("Longest to shortest: ");
        for (String s : names) {
            System.out.println(s);
        }
        System.out.println();

        // sort by first character only
        Arrays.sort(names, (o1, o2) -> (o1.charAt(0) - o2.charAt(0)));
        System.out.println("Alphabetically first letter only: ");
        for (String s : names) {
            System.out.println(s);
        }
        System.out.println();

        // sort by whether string contains 'e'
        Arrays.sort(names, (o1, o2) -> {
            // brings all of the e's to the front, unordered otherwise
            if ((o1.contains("e") || o1.contains("E")) && (o2.contains("e") || o2.contains("E"))) {
                return 0;
            } else if (o1.contains("e") || o1.contains("E")) {
                return -1;
            } else {
                return 1;
            }
        });
        System.out.println("Contains 'e' first, everything else after: ");
        for (String s : names) {
            System.out.println(s);
        }

        System.out.println();

        // same as the last but with a helper method
        StringComp Utils = new StringComp();
        Arrays.sort(names, (o1, o2) -> Utils.compare(o1, o2));
        System.out.println("Contains 'e' first, everything else after: ");
        for (String s : names) {
            System.out.println(s);
        }

        System.out.println();

    }

}
