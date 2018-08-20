package com.selbovi;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertTrue;

public class PuzzleTest {

    @Test
    public void testCase1() throws ExecutionException, InterruptedException {
        //given:
        InputStream is = getCase1Input();
        //when:
        String result = Puzzle.go(is);
        //then:
        assertTrue(result.contains(": dearalanhowareyou"));

    }

    @Test
    public void testCase2() {
        //Thread.sleep(40000);
        //given:
        InputStream is = getCase2Input();
        //when:
        String result = Puzzle.go(is);
        //then:
        assertTrue(result.contains(": ienjoycorresponding"));
    }

    @Test
    public void testCase3() throws ExecutionException, InterruptedException {
        //given:
        InputStream is = getCase3Input();
        //when:
        String result = Puzzle.go(is);
        //then:
        assertTrue(result.contains(": abcd"));
    }

    @Test
    public void testCase4() throws ExecutionException, InterruptedException {
        //given:
        InputStream is = getCase4Input();
        //when:
        String result = Puzzle.go(is);
        //then:
        assertTrue(result.contains(": IMPOSSIBLE"));

    }

    @Test
    public void testCase5() {
        //given:
        InputStream is = getCase5Input();
        //when:
        String result = Puzzle.go(is);
        //then:
        assertTrue(result.contains(": ienjoycorresponding"));
    }

    @Test
    public void testCase6() {
        //given:
        InputStream is = getCase6Input();
        //when:
        String result = Puzzle.go(is);
        //then:
        assertTrue(result.contains(": xxxxyxxx"));
    }

    @Test
    public void testCase7() {
        //given:
        InputStream is = getCase7Input();
        //when:
        String result = Puzzle.go(is);
        //then:
        assertTrue(result.contains(": bzaa"));
    }

    private InputStream getCase1Input() {
        StringBuilder builder = new StringBuilder();
        builder.append("5");
        builder.append("\n");
        builder.append("are yo");
        builder.append("\n");
        builder.append("you u");
        builder.append("\n");
        builder.append("how nhoware");
        builder.append("\n");
        builder.append("alan arala");
        builder.append("\n");
        builder.append("dear de");
        String myString = builder.toString();

        System.err.println(myString);

        return new ByteArrayInputStream(StandardCharsets.UTF_8.encode(myString).array());
    }

    private InputStream getCase2Input() {
        StringBuilder builder = new StringBuilder();
        builder.append("8");
        builder.append("\n");
        builder.append("i ie");
        builder.append("\n");
        builder.append("ing ding");
        builder.append("\n");
        builder.append("resp orres");
        builder.append("\n");
        builder.append("ond pon");
        builder.append("\n");
        builder.append("oyc y");
        builder.append("\n");
        builder.append("hello hi");
        builder.append("\n");
        builder.append("enj njo");
        builder.append("\n");
        builder.append("or c");
        String myString = builder.toString();

        System.err.println(myString);

        return new ByteArrayInputStream(StandardCharsets.UTF_8.encode(myString).array());
    }

    private InputStream getCase5Input() {
        StringBuilder builder = new StringBuilder();
        builder.append("11");
        builder.append("\n");
        builder.append("i ie");
        builder.append("\n");
        builder.append("ing ding");
        builder.append("\n");
        builder.append("resp orres");
        builder.append("\n");
        builder.append("ond pon");
        builder.append("\n");
        builder.append("oyc y");
        builder.append("\n");
        builder.append("hello hi");
        builder.append("\n");
        builder.append("enj njo");
        builder.append("\n");
        builder.append("or c");

        builder.append("\n");
        builder.append("asdfgh  asdfghasdfghasdfgh");
        builder.append("\n");
        builder.append("ejhflhefljrhewl ejhflhefljrhewlejhflhefljrhewlejhflhefljrhewlejhflhefljrhewl");

        builder.append("\n");
        builder.append("ejhflhefljrhewl123 ejhflhefljrhewl123lejhflhefljrhewlejhflhefljrhewlejhflhefljrhewl");
        String myString = builder.toString();

        System.err.println(myString);

        return new ByteArrayInputStream(StandardCharsets.UTF_8.encode(myString).array());
    }

    private InputStream getCase6Input() {
        StringBuilder builder = new StringBuilder();
        builder.append("11");
        builder.append("\n");
        builder.append("i ie");
        builder.append("\n");
        builder.append("ing ding");
        builder.append("\n");
        builder.append("resp orres");
        builder.append("\n");
        builder.append("ond pon");
        builder.append("\n");
        builder.append("oyc y");
        builder.append("\n");
        builder.append("hello hi");
        builder.append("\n");
        builder.append("enj njo");
        builder.append("\n");
        builder.append("or c");

        builder.append("\n");
        builder.append("xxxx  xxxxy");
        builder.append("\n");
        builder.append("yxxx xxx");

        builder.append("\n");
        builder.append("ejhflhefljrhewl123 ejhflhefljrhewl123lejhflhefljrhewlejhflhefljrhewlejhflhefljrhewl");
        String myString = builder.toString();

        System.err.println(myString);

        return new ByteArrayInputStream(StandardCharsets.UTF_8.encode(myString).array());
    }

    private InputStream getCase7Input() {
        StringBuilder builder = new StringBuilder();
        builder.append("4");
        builder.append("\n");
        builder.append("b bz");
        builder.append("\n");
        builder.append("zaa aa");

        builder.append("\n");
        builder.append("xxxx  xxxxy");
        builder.append("\n");
        builder.append("yxxx xxx");

        String myString = builder.toString();

        System.err.println(myString);

        return new ByteArrayInputStream(StandardCharsets.UTF_8.encode(myString).array());
    }

    private InputStream getCase3Input() {
        StringBuilder builder = new StringBuilder();
        builder.append("3");
        builder.append("\n");
        builder.append("efgh efgh");
        builder.append("\n");
        builder.append("d cd");
        builder.append("\n");
        builder.append("abc ab");
        String myString = builder.toString();

        System.err.println(myString);

        return new ByteArrayInputStream(StandardCharsets.UTF_8.encode(myString).array());
    }

    private InputStream getCase4Input() {
        StringBuilder builder = new StringBuilder();
        builder.append("3");
        builder.append("\n");
        builder.append("a ab");
        builder.append("\n");
        builder.append("b bb");
        builder.append("\n");
        builder.append("c cc");
        String myString = builder.toString();

        System.err.println(myString);

        return new ByteArrayInputStream(StandardCharsets.UTF_8.encode(myString).array());
    }
}
