package com.selbovi;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Puzzle {
    public static void main(String[] args) {
        String result = go(System.in);
        System.out.print(result);
    }

    public static String go(InputStream in) {
        Kattio reader = new Kattio(in);

        List<List<Pair>> pairsList = new ArrayList<>();
        int i = 0;
        List<Pair> pairs = null;
        while (reader.hasMoreTokens()) {
            if (i == 0) {
                String word = reader.getWord();
                Integer count = numberOrNot(word);
                if (count != null) {
                    i = count;
                    pairs = new ArrayList<>(count);
                    pairsList.add(pairs);
                    continue;
                }
            }
            String a = reader.getWord().trim();
            String b = reader.getWord().trim();

            pairs.add(new Pair(a, b));
            i--;
        }

        StringBuilder sb = executeAndWaitForResult(pairsList);

        String result = sb.toString();

        System.err.println("result = " + result);

        return result;
    }

    private static StringBuilder executeAndWaitForResult(List<List<Pair>> pairsList) {

        int i = 1;
        StringBuilder sb = new StringBuilder();
        for (List<Pair> pairs : pairsList) {
            String res = go(i, pairs);
            sb.append(res);
            //sb.append("\r");
            sb.append("\n");
            i++;
        }

        return sb;
    }

    public static Set<String> go2(List<Pair> original) {


        Set<String> results = new HashSet<>();

        List<List<Pair>> newPairs = new ArrayList<>();

        for (Pair pair : original) {
            if (pair.getA().startsWith(pair.getB()) || pair.getB().startsWith(pair.getA())) {
                if (!pair.getA().equals(pair.getB())) {
                    newPairs.add(new ArrayList<>(Arrays.asList(pair)));
                }
            }
        }

        while (!newPairs.isEmpty()) {

            List<List<Pair>> temp = new ArrayList<>(newPairs);

            newPairs.clear();

            for (List<Pair> pairs : temp) {

                List<Pair> leftPairs = new ArrayList<>(original);
                leftPairs.removeAll(pairs);

                for (Pair nextPair : leftPairs) {
                    String nextA = nextPair.getA();
                    String nextB = nextPair.getB();

                    if (nextA.equals(nextB)) continue;

                    String aseq = pairs.stream().map(Pair::getA).collect(Collectors.joining()) + nextA;
                    String bseq = pairs.stream().map(Pair::getB).collect(Collectors.joining()) + nextB;

                    if (aseq.startsWith(bseq) || bseq.startsWith(aseq)) {
                        if (aseq.equals(bseq)) {
                            results.add(aseq);
                        }
                        pairs.add(nextPair);
                        newPairs.add(pairs);
                    }
                }

            }

        }

        System.err.println("results = " + results);
        return results;
    }

    public static class Pair implements Map.Entry<String, String> {

        private String a;
        private String b;

        public Pair(String a, String b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public String getKey() {
            return a;
        }

        @Override
        public String getValue() {
            return b;
        }

        public String getA() {
            return getKey();
        }

        public String getB() {
            return getValue();
        }

        @Override
        public String setValue(String value) {
            b = value;
            return getA();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return Objects.equals(a, pair.a) &&
                    Objects.equals(b, pair.b);
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b);
        }
    }

    public static String go(int caseNum, List<Pair> pairs) {

       /* System.err.println(tokensA);
        System.err.println(tokensB);*/

        Set<String> resList = go2(pairs);

        return "Case " + caseNum + ": " + chooseResult(resList);
    }

    public static String chooseResult(Set<String> resList) {
        if (resList.size() == 0) {
            return "IMPOSSIBLE";
        }
        ArrayList<String> list = new ArrayList<>(resList);
        list.sort(new PuzzleComparator());

        return list.get(0);
    }


    static Integer numberOrNot(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException ex) {
        }
        return null;
    }


    private static class PuzzleComparator implements Comparator<String>, Serializable {

        @Override
        public int compare(String o1, String o2) {
            int length1 = o1.length();
            int length2 = o2.length();

            int compare = Integer.compare(length1, length2);
            if (compare == 0) {
                return o1.compareTo(o2);
            }
            return compare;
        }
    }

    private static class Kattio extends PrintWriter {

        public Kattio(InputStream i) {
            super(new BufferedOutputStream(System.out));
            r = new BufferedReader(new InputStreamReader(i));
        }

        public boolean hasMoreTokens() {
            return peekToken() != null;
        }

        public String getWord() {
            return nextToken();
        }

        private BufferedReader r;
        private String line;
        private StringTokenizer st;
        private String token;

        private String peekToken() {
            if (token == null)
                try {
                    while (st == null || !st.hasMoreTokens()) {
                        line = r.readLine();
                        if (line == null) return null;
                        st = new StringTokenizer(line);
                    }
                    token = st.nextToken();
                } catch (IOException e) {
                }
            return token;
        }

        private String nextToken() {
            String ans = peekToken();
            token = null;
            return ans;
        }
    }
}
