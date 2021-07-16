package Java_Basics_5;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Assignment5_1_3 {

    static List<String> makeStringList(List<String> list) {
        // filters and collects as list
        return list.stream().filter(s -> s.charAt(0) == 'a' && s.length() == 3).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        strings.add("apple");
        strings.add("banana");
        strings.add("ape");
        strings.add("apricot");
        strings.add("aaa");
        strings.add("a");
        strings.add("abc");

        makeStringList(strings).forEach(s -> System.out.println(s));
    }
}
