package LMS_Final_Assignment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * this handles inputs from the user to clean up other functions
 */
public class InputHandler {

    public static int getIntInput(int minAcceptable, int maxAcceptable) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int ans = 0; // all lists in this program start from 1 so the user must enter a valid input

        while (true) {
            try {
                ans = Integer.parseInt(br.readLine());
            } catch (Exception e) {

            }

            if (ans >= minAcceptable && ans <= maxAcceptable) {
                break;
            } else {
                System.out.println("Invalid Input, please enter again");
            }
        }
        System.out.println("\n-------------------------------------\n");

        return ans;
    }

    public static String getStringInput() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String ans = ""; // all lists in this program start from 1 so the user must enter a valid input

        while (true) {
            try {
                ans = br.readLine();
                break;
            } catch (IOException e) {
                System.out.println("Invalid Input, please enter again");
            }
        }
        System.out.println("\n-------------------------------------\n");

        return ans;
    }

    public static int getCardInput() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String ans = "";
        int n = 0; // all lists in this program start from 1 so the user must enter a valid input

        while (true) {
            try {
                ans = br.readLine();
                if (ans.length() == 6)
                    n = Integer.parseInt(ans);
                break;
            } catch (Exception e) {

            }
            System.out.println("Invalid Input, please enter 6 digit card number");
        }
        System.out.println("Accepted!");
        System.out.println("\n-------------------------------------\n");

        return n;
    }
}
