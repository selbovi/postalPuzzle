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
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
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
            sb.append("\r\n");
        }

        return sb;
    }

    public static String go(int caseNum, List<String> tokensA, List<String> tokensB) {
        List<Integer> ints = new ArrayList<>();
        for (int i = 0; i < tokensA.size(); i++) {
            ints.add(i);
        }

        Collection<List<Integer>> permutations = getPossibleInts(ints, tokensA, tokensB);
        System.err.println(tokensA);
        System.err.println(tokensB);
        System.err.println("permutations.size: " + permutations.size());

        List<String> resList = new ArrayList<>();

        List<String> aStack = new ArrayList<>();
        List<String> bStack = new ArrayList<>();
        int count = 0;
        for (List<Integer> permutation : permutations) {
            if (allow(permutation.get(0), tokensA, tokensB)) {
                calculateSequences(
                        tokensA, tokensB,
                        permutation, aStack, bStack, resList
                );
                count++;
            }
        }
        System.err.println("count = " + count);

        return "Case " + caseNum + ": " + chooseResult(resList);
    }

    public static String chooseResult(List<String> resList) {
        if (resList.size() == 0) {
            return "IMPOSSIBLE";
        }
        resList.sort(new PuzzleComparator());

        return resList.get(0);
    }

    private static boolean allow(
            Integer ind,
            List<String> aList,
            List<String> bList
    ) {
        String s = aList.get(ind);
        String s1 = bList.get(ind);
        return s.startsWith(s1) || s1.startsWith(s);
    }


    public static List<List<Integer>> getPossibleInts(List<Integer> original, List<String> tokensA, List<String> tokensB) {
        List<List<Integer>> returnValue = new ArrayList<>();
        Integer[] elements = original.toArray(new Integer[original.size()]);
        int n = original.size();
        int[] c = new int[n];
        Arrays.fill(c, 0);
        returnValue.add(yield(elements));
        int i = 0;
        while (i < n) {
            if (c[i] < i) {
                if (i % 2 == 0) {
                    swap(elements, 0, i);
                } else {
                    swap(elements, c[i], i);
                }
                if (allow(elements[0], tokensA, tokensB)) {

                    returnValue.add(yield(elements));
                }
                c[i] = c[i] + 1;
                i = 0;
            } else {
                c[i] = 0;
                i++;
            }
        }

        return returnValue;
    }

    private static void swap(Integer[] elements, int i, int j) {
        Integer temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }

    private static List<Integer> yield(Integer[] elements) {
        ArrayList<Integer> list = new ArrayList<>(elements.length);

        for (int i = 0; i < elements.length; i++) {
            list.add(elements[i]);

        }

        return list;
    }

    static Integer numberOrNot(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException ex) {
        }
        return null;
    }

    public static void calculateSequences(
            List<String> aList,
            List<String> bList,
            List<Integer> permutation,
            List<String> aStack,
            List<String> bStack,
            List<String> resSet
    ) {
        aStack.clear();
        bStack.clear();
        String lastStrA = "";
        String lastStrB = "";
        for (int i = 0; i < aList.size(); i++) {
            Integer index = permutation.get(i);
            String a = aList.get(index);
            String b = bList.get(index);

            if (!a.equals(b)) {
                String left = lastStrA.concat(a);
                String right = lastStrB.concat(b);
                if (left.startsWith(right) || right.startsWith(left)) {
                    aStack.add(a);
                    bStack.add(b);
                    lastStrA = left;
                    lastStrB = right;
                } else {
                    calculateSequenceAndClearStacks(resSet, aStack, bStack, lastStrA, lastStrB);
                    lastStrA = "";
                    lastStrB = "";
                }
            } else {
                calculateSequenceAndClearStacks(resSet, aStack, bStack, lastStrA, lastStrB);
                lastStrA = "";
                lastStrB = "";
            }

        }
        calculateSequenceAndClearStacks(resSet, aStack, bStack, lastStrA, lastStrB);
    }

    private static void calculateSequenceAndClearStacks(
            List<String> resSet,
            List<String> aStack,
            List<String> bStack,
            String left,
            String right
    ) {
        if (aStack.size() < 1) {
            return;
        }

        while (!left.equals(right) && aStack.size() > 0) {
            aStack.remove(aStack.size() - 1);
            bStack.remove(bStack.size() - 1);
            left = aStack.stream().collect(Collectors.joining());
            right = bStack.stream().collect(Collectors.joining());
        }
        if (left.equals(right) && left.length() > 0) {
            resSet.add(left);
        }
        aStack.clear();
        bStack.clear();
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
