package RecursionAndDP;

import java.util.HashMap;

/** Given a boolean expression consisting of the symbols 0 (false), 1 (true), & (AND), | (OR), and ^ (XOR),
 * and a desired boolean result value "result", implement a function to count the number of ways of
 * parenthesizing the expression such that it evaluates to "result". The expression should be fully
 * parenthesized (e.g., (0)^(1) but not extraneously (e.g., (((0))^(1))).
 * Example: countEval("1^0|0|1", false) -> 2;   countEval("0&0&0&1^1|0", true) -> 10
 */

public class BooleanEvaluation {

    public static int count = 0;
    public static boolean stringToBool(String c) {
        return c.equals("1") ? true : false;
    }

    public static int countEval(String s, boolean result, HashMap<String, Integer> memo) {
        count++;
        if (s.length() == 0) return 0;
        if (s.length() == 1) return stringToBool(s) == result ? 1 : 0;
        if (memo.containsKey(result + s)) return memo.get(result + s);

        int ways = 0;

        /* Evaluate each side for each result. */
        for (int i = 1; i < s.length(); i += 2) {
            char c = s.charAt(i);
            String left = s.substring(0, i);
            String right = s.substring(i + 1, s.length());
            int leftTrue = countEval(left, true, memo);
            int leftFalse = countEval(left, false, memo);
            int rightTrue = countEval(right, true, memo);
            int rightFalse = countEval(right, false, memo);
            int total = (leftTrue + leftFalse) * (rightTrue + rightFalse);

            int totalTrue = 0;
            if (c == '^') {              //require one true and one false on each side
                totalTrue = leftTrue * rightFalse + leftFalse * rightTrue;
            } else if (c == '&') {      // require both true
                totalTrue = leftTrue * rightTrue;
            } else if (c == '|') {      // require anything but two falses
                totalTrue = leftTrue * rightTrue + leftFalse * rightTrue + leftTrue * rightFalse;
            }

            /* false case will be total case minus true cases */
            int subWays = result ? totalTrue : total -totalTrue;
            ways += subWays;
        }

        memo.put(result + s, ways);
        return ways;
    }

    public static int countEval(String s, boolean result) {
        return countEval(s, result, new HashMap<String, Integer>());
    }

    public static void main(String[] args) {
        String expression = "0^0|1&1^1|0|1";
        boolean result = true;

        System.out.println(countEval(expression, result));
        System.out.println(count);
    }


}
