package com.selbovi;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class ComparatorTest {

    @Test
    public void alphabetOrder() {
        List<String> aList = Arrays.asList("azc", "abd", "abj");

        String result = Puzzle.chooseResult(aList);

        assertEquals("abd", result);
    }

    @Test
    public void shortest() {
        List<String> aList = Arrays.asList("aaa", "zz");

        String result = Puzzle.chooseResult(aList);

        assertEquals("zz", result);
    }
}
