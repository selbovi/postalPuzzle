package com.selbovi;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BusinessTest {

    @Test
    public void resultTestCase3() {
        List<String> aList = Arrays.asList("abc", "d", "efgh");
        List<String> bList = Arrays.asList("ab", "cd", "efgh");
        List<String> resSet = new ArrayList<>();

        Puzzle.calculateSequences(
                aList, bList, IntStream.rangeClosed(0, bList.size()).boxed().collect(Collectors.toList()),
                new ArrayList<>(), new ArrayList<>(), resSet
        );

        assertEquals("abcd", resSet.get(0));
    }

    @Test
    public void resultTestCase4() {
        List<String> aList = Arrays.asList("a", "b", "c");
        List<String> bList = Arrays.asList("ab", "bb", "cc");
        List<String> resSet = new ArrayList<>();

        Puzzle.calculateSequences(
                aList, bList, IntStream.rangeClosed(0, bList.size()).boxed().collect(Collectors.toList()),
                new ArrayList<>(), new ArrayList<>(), resSet
        );

        assertTrue(resSet.size() == 0);
    }

    @Test
    public void resultTestCase1() {
        List<String> aList = Arrays.asList("dear", "alan", "how", "are", "you");
        List<String> bList = Arrays.asList("de", "arala", "nhoware", "yo", "u");
        List<String> resSet = new ArrayList<>();

        Puzzle.calculateSequences(
                aList, bList, IntStream.rangeClosed(0, bList.size()).boxed().collect(Collectors.toList()),
                new ArrayList<>(), new ArrayList<>(), resSet
        );

        assertEquals("dearalanhowareyou", resSet.get(0));
    }

    @Test
    public void resultTestCase2() {
        List<String> aList = Arrays.asList("i", "enj", "oyc", "or", "resp", "ond", "ing", "hello");
        List<String> bList = Arrays.asList("ie", "njo", "y", "c", "orres", "pon", "ding", "hi");
        List<String> resSet = new ArrayList<>();

        Puzzle.calculateSequences(
                aList, bList,
                IntStream.rangeClosed(0, bList.size()).boxed().collect(Collectors.toList()),
                new ArrayList<>(), new ArrayList<>(), resSet
        );

        assertEquals("ienjoycorresponding", resSet.get(0));
    }


}
