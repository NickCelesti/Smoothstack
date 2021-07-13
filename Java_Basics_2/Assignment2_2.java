// Nick Celesti

package Java_Basics_2;

import java.util.Random;

public class Assignment2_2 {
    public static void main(String[] args) {
        int a[][] = new int[5][5];
        Random rand = new Random();
        // generate 2D array with random values [0-100]
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                a[i][j] = rand.nextInt(101);
            }
        }

        int maxValue = 0;
        int posI = 0;
        int posJ = 0;
        // find max value and its corresponding position in the array
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if (a[i][j] > maxValue) {
                    maxValue = a[i][j];
                    posI = i;
                    posJ = j;
                }
            }

        }

        System.out.println("Max number: " + maxValue);
        System.out.println("Position: [" + posI + "][" + posJ + "]");
    }
}
