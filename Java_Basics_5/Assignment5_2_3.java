package Java_Basics_5;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Assignment5_2_3 {
    // Given a list of integers, return a list where each integer is multiplied by
    // 2.

    // doubling([1, 2, 3]) → [2, 4, 6]
    // doubling([6, 8, 6, 8, -1]) → [12, 16, 12, 16, -2]
    // doubling([]) → []

    public static List<Integer> multBy2(List<Integer> nums) {
        return nums.stream().map(n -> n * 2).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(6);
        list.add(8);
        list.add(6);
        list.add(8);
        list.add(-1);
        multBy2(list).forEach(n -> System.out.println(n));
    }
}
