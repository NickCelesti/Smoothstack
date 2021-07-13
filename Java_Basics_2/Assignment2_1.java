// Nick Celesti

package Java_Basics_2;

public class Assignment2_1 {
    public static void main(String[] args) {
        int total = 0;
        for (String string : args) {
            try {
                total += Integer.parseInt(string);
            } catch (NumberFormatException e) { // parseInt throws this type of error if there is no parsable int
                e.printStackTrace();
            }
        }
        System.out.println(total);
    }
}
