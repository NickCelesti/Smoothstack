package Java_Basics_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@FunctionalInterface
interface PerformOperation {
    boolean check(int a);
}

class NumberChecker {
    public static boolean checker(PerformOperation p, int num) {
        return p.check(num);
    }

    PerformOperation isOdd() {
        return n -> n % 2 == 0 ? false : true;
    }

    PerformOperation isPrime() {
        return n -> {
            if (n == 1)
                return true;
            else {
                for (int i = 2; i < Math.sqrt(n); i++)
                    if (n % i == 0)
                        return false;
                return true;
            }
        };
    }

    PerformOperation isPalindrome() {
        return n -> {
            String string = String.valueOf(n);
            String reverseString = new StringBuffer(string).reverse().toString();
            return string.equals(reverseString);
        };
    }
}

public class Assignment5_2_1 {

    public static void main(String[] args) throws IOException {
        NumberChecker ob = new NumberChecker();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
        while (count > 0) {

            String[] s = br.readLine().trim().split(" ");
            int type = Integer.parseInt(s[0]);
            int number = Integer.parseInt(s[1]);
            if (type == 1) {
                if (NumberChecker.checker(ob.isOdd(), number)) {
                    System.out.println("ODD");
                } else {
                    System.out.println("EVEN");
                }
            } else if (type == 2) {
                if (NumberChecker.checker(ob.isPrime(), number)) {
                    System.out.println("PRIME");
                } else {
                    System.out.println("COMPOSITE");
                }
            } else if (type == 3) {
                if (NumberChecker.checker(ob.isPalindrome(), number)) {
                    System.out.println("PALINDROME");
                } else {
                    System.out.println("NOT PALINDROME");
                }
            }
            count--;
        }
    }
}
