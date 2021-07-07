package Java_Basics_2;

public class Assignment2_1 {

    public static void main(String[] args) {
        int total = 0;
        for (String string : args) {
            total += Integer.parseInt(string);
        }
        System.out.println(total);
    }
}
