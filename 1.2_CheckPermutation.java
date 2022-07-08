package ArraysAndStrings;

/**
 * Given two strings, write a method to decide if one is a permutation of the
 * other?
 * Clarification with Interviewer: is it case sensitive? is it whitespace
 * sensitive?
 * 
 * July 08, 2022: be able to formulate the right solution but failed to write
 * the correct code
 * Main issue is not familiar with String char related operation,
 * especially String.CharAt() and String.toCharArray()
 */

public class CheckPermutation {

    /**
     * Solution 1: if the two strings are permutations to each other, this means
     * they are
     * the same length with different arrangement of characters. Thus, if we sort
     * the strings,
     * the result should be the same
     */

    public static String sort(String s) {
        char[] content = s.toCharArray();
        java.util.Arrays.sort(content);
        return new String(content);
    }

    public static boolean permutation(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        return sort(s).equals(sort(t));
    }

    public static void main(String[] args) {
        String[][] pairs = { { "apple", "papel" }, { "carrot", "tarroc" }, { "hello", "llloh" } };
        for (String[] pair : pairs) {
            String word1 = pair[0];
            String word2 = pair[1];
            boolean anagram = permutation(word1, word2);
            System.out.println(word1 + ", " + word2 + ": " + anagram);
        }
    }

    /**
     * Solution 2: Use the definition of a permutation - two words with the same
     * character counts
     * we can iterate through counting how many times each character appears, then
     * compare the two arrays
     * public static boolean permutation(String s, String t) {
     * if (s.length() != t.length()) return false; // Permutations must be same
     * length
     *
     * int[] letters = new int[128]; // Assumption: ASCII
     * for (int i = 0; i < s.length(); i++) {
     * letters[s.charAt(i)]++;
     * }
     *
     * for (int i = 0; i < t.length(); i++) {
     * letters[t.charAt(i)]--;
     * if (letters[t.charAt(i)] < 0) {
     * return false;
     * }
     * }
     *
     * return true; // letters array has no negative values, and therefore no
     * positive values either
     */
}
