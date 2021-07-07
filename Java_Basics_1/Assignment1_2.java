// Nick Celesti
package Java_Basics_1;

import java.util.Random;
import java.util.Scanner;

public class Assignment1_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // used to get input
        Random rand = new Random(); // random number generator
        int randomInt; // random number
        int guess; // user's guess
        int i = 1; // counter

        randomInt = rand.nextInt(101); // generates number [0-100]

        while (true) { // loops until the counter hits 5 or the guess is within 10 of the correct
                       // answer
            System.out.print("Guess: ");
            guess = sc.nextInt();

            if (Math.abs(randomInt - guess) <= 10) { // if guess is within 10
                System.out.println("Correct answer: " + randomInt);
                break;
            } else {
                if (i >= 5) {
                    System.out.println("Sorry, " + randomInt);
                    break;
                } else {
                    System.out.println("Try again");
                }
            }
            i++;
        }
        sc.close();
    }
}
