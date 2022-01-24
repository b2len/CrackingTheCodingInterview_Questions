package RecursionAndDP;

import java.util.ArrayList;

/** Write a method to compute all permutations of a string of unique characters
 *
 */

public class PermutationWithoutDups {
    /** Solution
     * From P(n-1) to P(n), it's like adding n to every permutation of P(n-1)
     * Example P(a1a2) = a1a2, a2a1
     * P(a1a2a3): a1a2 --> a3a1a2, a1a3a2, a1a2a3   + a2a1 --> a3a2a1, a2a3a1, a2a1a3
     */

    public static ArrayList<String> getPerms(String str) {
        if (str == null) {
            return null;
        }

        ArrayList<String> permutations = new ArrayList<String>();
        if (str.length() == 0) {
            permutations.add("");
            return permutations         // Base case
        }

        char first = str.charAt(0);    // get the first character
        String remainder = str.substring(1);    //remove the first character
        ArrayList<String> words = getPerms(remainder);
        for (String word : words) {
            for (int j = 0; j <= word.length(); j++) {
                String s = insertChaAt(word, first, j);
                permutations.add(s);
            }
        }
        return permutations;
    }

    public static String insertChaAt(String word, char c, int i) {
        String start = word.substring(0, i);
        String end = word.substring(i);
        return start + c + end;
    }

    public static void main(String[] args) {
        ArrayList<String> list = getPerms("abcd");
        System.out.println("There are " + list.size() + " permutations.");
        for (String s : list) {
            System.out.println(s);
        }
    }
}
