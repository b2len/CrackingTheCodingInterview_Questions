package ArraysAndStrings;

/**
 * Given a string, write a function to check if it is a permutation of a
 * palindrome.
 * A palindrome is a word or phrase that is the same forwards and backwards.
 * A permutation is a rearrangement of letters
 * The palindrome does not need to be limited to just dictionary words
 * Questions to ask: assume alphabets or there's special characters?
 * Leecode link: https://leetcode.com/problems/palindrome-permutation/
 */

public class PalindromePermutation {
    /**
     * Solution: A palindrome should have even numbers of chars and at most one odd
     * cha
     * Use an Array (or a hash table) to count the number of chars appear
     * Iterate through the hash table to ensure that no more than one char has an
     * odd count
     */

    public static boolean isPermutationOfPalindrome(String phrase) {
        int[] table = buildCharFrequencyTable(phrase);
        return checkMaxOneOdd(table);
    }

    // Check that no more than one character has an odd count.
    public static boolean checkMaxOneOdd(int[] table) {
        boolean foundOdd = false;
        for (int count : table) {
            if (count % 2 == 1) {
                if (foundOdd) {
                    return false;
                }
                foundOdd = true;
            }
        }
        return true;
    }

    // Map each character to a number. a -> 0, b -> 1, c -> 2, etc.
    // This is case-insensitive. Non-letter char map to -1

    public static int getCharNumber(Character c) {
        int a = Character.getNumericValue('a');
        int z = Character.getNumericValue('z');
        int val = Character.getNumericValue(c);
        if (a <= val && val <= z) {
            return val - a;
        }
        return -1;
    }

    // Count how many times each character appears
    public static int[] buildCharFrequencyTable(String phrase) {
        int[] table = new int[Character.getNumericValue('z') - Character.getNumericValue('a') + 1];
        for (char c : phrase.toCharArray()) {
            int x = getCharNumber(c);
            if (x != -1) {
                table[x]++;
            }
        }
        return table;
    }

    public static void main(String[] args) {
        String pali = "Rats live on no evil star";
        System.out.println(isPermutationOfPalindrome(pali));
    }

}
