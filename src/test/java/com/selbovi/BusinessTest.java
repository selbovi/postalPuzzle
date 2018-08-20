package com.selbovi;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;

public class BusinessTest {

    @Test
    public void resultTestCase3() {
        List<String> aList = Arrays.asList("abc", "d", "efgh");
        List<String> bList = Arrays.asList("ab", "cd", "efgh");

        Set<String> set = Puzzle.go2(toPairs(aList, bList));

        assertTrue(set.contains("abcd"));
        ;
    }

    @Test
    public void resultTestCase4() {
        List<String> aList = Arrays.asList("a", "b", "c");
        List<String> bList = Arrays.asList("ab", "bb", "cc");

        Set<String> set = Puzzle.go2(toPairs(aList, bList));

        assertTrue(set.size() == 0);
    }

    @Test
    public void resultTestCase1() {
        List<String> aList = Arrays.asList("dear", "alan", "how", "are", "you");
        List<String> bList = Arrays.asList("de", "arala", "nhoware", "yo", "u");

        Set<String> set = Puzzle.go2(toPairs(aList, bList));

        assertTrue(set.contains("dearalanhowareyou"));
    }

    @Test
    public void resultTestCase2() {
        List<String> aList = Arrays.asList("i", "enj", "oyc", "or", "resp", "ond", "ing", "hello");
        List<String> bList = Arrays.asList("ie", "njo", "y", "c", "orres", "pon", "ding", "hi");

        Set<String> set = Puzzle.go2(toPairs(aList, bList));

        assertTrue(set.contains("ienjoycorresponding"));
    }

    @Test
    public void resultTestCaseLong() {
        List<String> aList = Arrays.asList(
                "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj",
                "ccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc",
                "ddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd",
                "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee",
                "ggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggg",
                "hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh",
                "iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii",
                "kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk",
                "fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff"
        );
        List<String> bList = Arrays.asList(
                "abbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "ijjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj",
                "bcccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc",
                "cdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd",
                "deeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee",
                "fgggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggg",
                "ghhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh",
                "hiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii",
                "jkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk",
                "effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff"
        );

        Set<String> set = Puzzle.go2(toPairs(aList, bList));

        assertTrue(set.contains("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbcccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeefffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggghhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiijjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk"));
    }

    @Test
    public void resultTestCaseDuplicates() {
        List<String> aList = Arrays.asList("i", "i", "i", "i", "enj", "oyc", "or", "resp", "ond", "ond", "ing", "hello");
        List<String> bList = Arrays.asList("ie", "ie", "ie", "ie", "njo", "y", "c", "orres", "pon", "pon", "ding", "hi");

        Set<String> set = Puzzle.go2(toPairs(aList, bList));

        assertTrue(set.contains("ienjoycorresponding"));
    }

    @Test
    public void resultTestCaseDuplicates2() {
        List<String> aList = Arrays.asList("efgh", "efgh", "efgh", "efgh", "efgh");
        List<String> bList = Arrays.asList("efgh", "efgh", "efgh", "efgh", "efgh");

        Set<String> set = Puzzle.go2(toPairs(aList, bList));

        assertTrue(set.size() == 0);
    }


    @Test
    public void remove() {
        List<String> aList = Arrays.asList("dear", "alan", "how", "are", "you", "that", "this");
        List<String> bList = Arrays.asList("de", "arala", "nhoware", "yo", "u", "de", "de");

        Set<String> set = Puzzle.go2(toPairs(aList, bList));

        System.out.println("aList = " + aList);
    }

    List<Puzzle.Pair> toPairs(List<String> alist, List<String> bList) {
        List<Puzzle.Pair> res = new ArrayList<>();
        for (int i = 0; i < alist.size(); i++) {
            res.add(new Puzzle.Pair(alist.get(i), bList.get(i)));
        }
        return res;
    }
}
