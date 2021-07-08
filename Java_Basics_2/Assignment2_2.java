// Nick Celesti

package Java_Basics_2;

import java.util.Random;

public class Assignment2_2 {
    public static void main(String[] args) {
        int a[] = new int[20];
        Random rand = new Random();
        // generate 2D array with random values [0-100]
        for (int i = 0; i < a.length; i++) {
            a[i] = rand.nextInt(101);
        }

        int maxValue = 0;
        int pos = 0;
        // find max value and its corresponding position in the array
        for (int i = 0; i < a.length; i++) {
            if (a[i] > maxValue) {
                maxValue = a[i];
                pos = i;
            }
        }

        System.out.println("Max number: " + maxValue);
        System.out.println("Position: " + pos);
    }
}
