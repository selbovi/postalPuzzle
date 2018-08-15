package com.selbovi;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import static junit.framework.TestCase.assertEquals;

public class FileTest {

    @Test
    public void inOut() throws ExecutionException, InterruptedException {
        InputStream in = this.getClass().getResourceAsStream("/sample-01.in");
        InputStream ans = this.getClass().getResourceAsStream("/sample-01.ans");
        System.setIn(in);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);

        Puzzle.main(null);

        Scanner s = new Scanner(ans).useDelimiter("\\A");
        String result = s.hasNext() ? s.next() : "";

        assertEquals(baos.toString(), result);

    }


}
