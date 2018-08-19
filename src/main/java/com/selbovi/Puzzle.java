package com.selbovi;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Puzzle {
    public static void main(String[] args) throws InterruptedException {
        String result = go(System.in);
        System.out.print(result);
    }

    public static String go(InputStream in) throws InterruptedException {
        Kattio reader = new Kattio(in);

        LinkedHashMap<List<String>, List<String>> map = new LinkedHashMap<>();
        int i = 0;
        List<String> tokensA = null;
        List<String> tokensB = null;
        while (reader.hasMoreTokens()) {
            if (i == 0) {
                String word = reader.getWord();
                Integer count = numberOrNot(word);
                if (count != null) {
                    i = count;
                    tokensA = new ArrayList<>(count);
                    tokensB = new ArrayList<>(count);
                    map.put(tokensA, tokensB);
                    continue;
                }
            }
            String a = reader.getWord().trim();
            String b = reader.getWord().trim();

            tokensA.add(a);
            tokensB.add(b);
            i--;
        }

        StringBuilder sb = executeAndWaitForResult(map);

        return sb.toString();
    }

    private static StringBuilder executeAndWaitForResult(LinkedHashMap<List<String>, List<String>> map) throws InterruptedException {

        int i = 1;
        List<String> results = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<List<String>, List<String>> entry : map.entrySet()) {
            String res = go(i, entry.getKey(), entry.getValue());
            results.add(res);
            i++;
        }


        for (String result : results) {
            sb.append(result);
            sb.append("\n");
        }

        return sb;
    }

    public static Set<String> go2(List<String> tokensA, List<String> tokensB) {


        Set<String> results = new HashSet<>();


        List<List<String>> newseqsa = new ArrayList<>();
        List<List<String>> newseqsb = new ArrayList<>();

        int size = tokensA.size();
        for (int i = 0; i < size; i++) {
            String a = tokensA.get(i);
            String b = tokensB.get(i);
            if (a.startsWith(b) || b.startsWith(a) && !a.equals(b)) {
                newseqsa.add(new ArrayList<>(Arrays.asList(a)));
                newseqsb.add(new ArrayList<>(Arrays.asList(b)));
            }
        }


        while (!newseqsa.isEmpty()) {

            ArrayList<List<String>> seqsa = new ArrayList<>(newseqsa);
            ArrayList<List<String>> seqsb = new ArrayList<>(newseqsb);

            newseqsa.clear();
            newseqsb.clear();

            for (int z = 0; z < seqsa.size(); z++) {
                List<String> seqa = seqsa.get(z);
                List<String> seqb = seqsb.get(z);

                List<String> leftA = new ArrayList<>(tokensA);
                leftA.removeAll(seqa);
                List<String> leftB = new ArrayList<>(tokensB);
                leftB.removeAll(seqb);

                for (int j = 0; j < leftA.size(); j++) {
                    String nextA = leftA.get(j);
                    String nextB = leftB.get(j);

                    if (nextA.equals(nextB)) continue;

                    String aseq = seqa.stream().collect(Collectors.joining()) + nextA;
                    String bseq = seqb.stream().collect(Collectors.joining()) + nextB;

                    if (aseq.startsWith(bseq) || bseq.startsWith(aseq)) {
                        if (aseq.equals(bseq)) {
                            results.add(aseq);
                        } else {
                            seqa.add(nextA);
                            seqb.add(nextB);

                            newseqsa.add(seqa);
                            newseqsb.add(seqb);
                        }
                    }
                }
            }

        }

        System.err.println("results = " + results);
        return results;
    }

    public static String go(int caseNum, List<String> tokensA, List<String> tokensB) {

        System.err.println(tokensA);
        System.err.println(tokensB);

        Set<String> resList = go2(tokensA, tokensB);

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
