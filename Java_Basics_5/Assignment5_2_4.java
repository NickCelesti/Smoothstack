package Java_Basics_5;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Assignment5_2_4 {

    // Given a list of strings, return a list where each string has all its "x"
    // removed.

    // noX(["ax", "bb", "cx"]) → ["a", "bb", "c"]
    // noX(["xxax", "xbxbx", "xxcx"]) → ["a", "bb", "c"]
    // noX(["x"]) → [""]

    public static List<String> removeX(List<String> strings) {
        return strings.stream().map(s -> s.replace("x", "")).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("xxax");
        list.add("xbxbx");
        list.add("xxcx");

        removeX(list).forEach(n -> System.out.println(n));
    }
}
