package com.selbovi;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertTrue;

public class BusinessTest {

    @Test
    public void resultTestCase3() {
        List<String> aList = Arrays.asList("abc", "d", "efgh");
        List<String> bList = Arrays.asList("ab", "cd", "efgh");

        Set<String> set = Puzzle.go2(
                aList, bList);

        assertTrue(set.contains("abcd"));
        ;
    }

    @Test
    public void resultTestCase4() {
        List<String> aList = Arrays.asList("a", "b", "c");
        List<String> bList = Arrays.asList("ab", "bb", "cc");

        Set<String> set = Puzzle.go2(aList, bList);

        assertTrue(set.size() == 0);
    }

    @Test
    public void resultTestCase1() {
        List<String> aList = Arrays.asList("dear", "alan", "how", "are", "you");
        List<String> bList = Arrays.asList("de", "arala", "nhoware", "yo", "u");
        List<String> resSet = new ArrayList<>();

        Set<String> set = Puzzle.go2(aList, bList);

        assertTrue(set.contains("dearalanhowareyou"));
    }

    @Test
    public void resultTestCase2() {
        List<String> aList = Arrays.asList("i", "enj", "oyc", "or", "resp", "ond", "ing", "hello");
        List<String> bList = Arrays.asList("ie", "njo", "y", "c", "orres", "pon", "ding", "hi");

        Set<String> set = Puzzle.go2(aList, bList);

        assertTrue(set.contains("ienjoycorresponding"));
    }


}
