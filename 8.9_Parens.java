package RecursionAndDP;

import java.util.ArrayList;

/** Implement an algorithm to print all valid (i.e., properly opened and closed) combinations
 * of n pairs of parentheses
 * Example: input 3; output ((())), (()()), (())(), ()(()), ()()()
 */
public class Parens {
    /** Solution
     * Keep track of the number of left and right paren used.
     * 1. as long as we haven't used up all the left paren, we can add more
     * 2. as long as right paren used is less than left paren, we can add more
     */

    public static void addParen(ArrayList<String> list, int leftRem, int rightRem, char[] str, int index) {
        if (leftRem < 0 || leftRem < rightRem) return;      // invalid state

        if (leftRem == 0 && rightRem == 0) {                // used all left and right parents
            list.add(String.copyValueOf(str));
        } else {
            str[index] = '(';       // Add left and recurse
            addParen(list, leftRem - 1, rightRem, str, index + 1);

            str[index] = ')';       // Add right and recurse
            addParen(list, leftRem, rightRem - 1, index + 1);
        }
    }

    public static ArrayList<String> generateParens(int count) {
        char[] str = new char[count * 2];
        ArrayList<String> list = new ArrayList<String>();
        addParen(list, count, count, 0);
        return list;
    }

    public static void main(String[] args) {
        ArrayList<String> list = generateParens(6);
        for (String s : list) {
            System.out.println(s);
        }
        System.out.println(list.size());
    }
}
