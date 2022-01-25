package RecursionAndDP;

/** Given an infinite number of quarters (25), dime (10), nickels (5), and pennies (1)
 * Write code to calculate the number of ways of representing n cents
 */

public class Coins {
    /** Using 100 as example. It can deduce the problem to 0, 1, 3, 4 quarters scenarios
     * it can translate to find combons by using 0 quarters on 100, 75, 50, 25 + 1 (4 quarters have one case)
     * It can then further deduce on dime and so on....
      */

    public static int makeChange(int n, int[] denoms) {
        int[][] map = new int[n + 1][denoms.length];    // denoms is denominators i,e, 25, 10, 5, 1
        return makeChangeHelper(n, denoms, 0, map);
    }

    public static int makeChangeHelper(int total, int[] denoms, int index, int[][] map) {
        /* Check cache for prior result */
        if (map[total][index] > 0) {    // retrieve value
            return map[total][index];
        }

        int coin = denoms[index];
        if (index == denoms.length -1) {
            int remaining = total % coin;
            return remaining == 0 ? 1 : 0;
        }

        int numberOfWays = 0;

        /* Try 1 coin, then 2 coins, 3 three, ... (recursing each time on rest). */
        for (int amount = 0; amount <= total; amount += coin) {
            numberOfWays += makeChangeHelper(total - amount, denoms, index + 1, map); // go to next denom
        }

        /* Update cache with current result. */
        map[total][index] = numberOfWays;

        return numberOfWays;
    }

    public static void main(String[] args) {
        int[] denoms = {25, 10, 5, 1};
        int ways = makeChange(10, denoms);
        System.out.println(ways);
    }
}
