package Java_Basics_5;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Assignment5_2_2 {

    // Given a list of non-negative integers, return an integer list of the
    // rightmost digits. (Note: use %)

    // rightDigit([1, 22, 93]) → [1, 2, 3]
    // rightDigit([16, 8, 886, 8, 1]) → [6, 8, 6, 8, 1]
    // rightDigit([10, 0]) → [0, 0]

    public static List<Integer> rightDigit(List<Integer> nums) {
        return nums.stream().map(n -> n % 10).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(16);
        list.add(8);
        list.add(886);
        list.add(8);
        list.add(1);
        rightDigit(list).forEach(n -> System.out.println(n));
    }
}
