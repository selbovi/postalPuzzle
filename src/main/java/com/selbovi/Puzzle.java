package com.selbovi;

import com.google.common.collect.Collections2;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

public class Puzzle {
    public static void main(String[] args) {
        go(System.in);
    }

    public static String go(InputStream in) {
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

        i = 1;
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<List<String>, List<String>> entry : map.entrySet()) {
            String res = go(i, entry.getKey(), entry.getValue());
            sb.append(res);
            sb.append("\n");
            i++;
        }

        return sb.toString().trim();
    }

    public static String go(int caseNum, List<String> tokensA, List<String> tokensB) {
        List<Integer> ints = new ArrayList<>();
        for (int i = 0; i < tokensA.size(); i++) {
            ints.add(i);
        }

        Collection<List<Integer>> permutations = getPossibleInts(ints);
        System.err.println(tokensA);
        System.err.println(tokensB);
        System.err.println("permutations.size: " + permutations.size());

        List<String> resList = new ArrayList<>();

        List<String> aStack = new ArrayList<>();
        List<String> bStack = new ArrayList<>();
        int count = 0;
        for (List<Integer> permutation1 : permutations) {
            if (allow(permutation1, tokensA, permutation1, tokensB)) {
                calculateSequences(
                        tokensA, permutation1,
                        tokensB, permutation1,
                        aStack, bStack, resList
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
            List<Integer> permutation1,
            List<String> aList,
            List<Integer> permutation2,
            List<String> bList
    ) {
        Integer a = permutation1.get(0);
        Integer b = permutation2.get(0);

        String s = aList.get(a);
        String s1 = bList.get(b);
        return s.startsWith(s1) || s1.startsWith(s);
    }

    private static Collection<List<Integer>> getPossibleInts(List<Integer> words) {
        //TODO for performance
        Collection<List<Integer>> permutations = Collections2.permutations(words);
        return permutations;
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
            List<Integer> permutation1,
            List<String> bList,
            List<Integer> permutation2,
            List<String> aStack,
            List<String> bStack,
            List<String> resSet
    ) {
        aStack.clear();
        bStack.clear();
        String lastStrA = "";
        String lastStrB = "";
        for (int i = 0; i < aList.size(); i++) {
            String a = aList.get(permutation1.get(i));
            String b = bList.get(permutation2.get(i));

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

    private static class PuzzleComparator implements Comparator<String> {

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
}
