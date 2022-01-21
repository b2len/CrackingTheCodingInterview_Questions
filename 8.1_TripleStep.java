package RecursionAndDP;

import java.util.Arrays;

/** A child is running up a staircase with n steps and can hop either 1 step, 2 steps, or 3 steps at a time.
 * Implement a method to count how many possible ways the child can run up the stairs
 */

public class TripleStep {
    /** Solution
     *  What is the very last step that is done? Either take 1 or 2 or 3 steps
     *  So we can get to the nth step by the following:
     *  Going to the (n-1)st step and hop 1 step
     *  Going to the (n-2)nd step and hop 2 steps
     *  Going to the (n-3)rd step and hopping 3 steps
     *  Therefore we just need to add the number of these paths together
     *  Again, a tree drawing will help to clearify the concept, it's add not multiply
     *  Cach seen value to improve running time
     *  Typically use a HashMap for a cache, in this case, the keys will be eactly 1 through n,
     *  so it's more compact to use an integer array
     */

    public static int countWays(int n) {
        int[] memo = new int[n + 1];    // build an integer Array to include stair steps 0 to n
        Arrays.fill(memo, -1)   //fill Array memo with value -1
        return countWays(n, memo);
    }

    public static int countWays(int n, int[] memo) {
        if (n < 0) {
            return 0;
        } else if (n == 0) {
            return 1;
        } else if (memo[n] > -1) {
            return memo[n];
        } else {
            memo[n] = countWays(n - 1, memo) + countWays(n - 2, memo) + countWays(n - 3, memo);
            return memo[n];
        }
    }

    public static void main(String[] args) {
        int n = 50;
        int ways = countWays(n);
        System.out.println(ways);
    }


}
