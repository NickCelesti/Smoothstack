package Java_Basics_5;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Assignment5_1_2 {

    static String makeString(List<Integer> list) {

        return list.stream().map(n -> {
            if (n % 2 == 0) {
                return "e" + n;
            } else {
                return "o" + n;
            }
        }).collect(Collectors.joining(", "));
    }

    public static void main(String[] args) {
        List<Integer> ints = new ArrayList<>();
        ints.add(15);
        ints.add(3);
        ints.add(24);
        ints.add(0);

        System.out.println(makeString(ints));
    }
}
