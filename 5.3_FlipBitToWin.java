package BitManipulation;

/** You have an integer and you can flip exactly one bit from a 0 to a 1.
  * Write code to find the length of the longest sequence of 1s you could create
  */

public class FlipBitToWin {
    /** Solution
     * We can think about each integer as being an alternating sequence of 0s and 1s.
     * Whenever a 0s sequence has length one, we can potentially merge the adjacent 1s sequences
     * Walk through the integer, tracking current 1s sequence length and
     * the previous 1s sequence length. When see a zero, update previousLength
     * If the next bit is a 1, previousLength should be set to current Length
     * If the next bit is a 0, then can't merge these sequences together, so set previous Length to 0
     */

    public static int flipBit(int a) {
        /* If all 1s, this is already the longest sequence. */
        if (~a == 0) return Integer.BYTES * 8;   // Integer has 4 bytes

        int currentLength = 0;
        int previousLength = 0;
        int maxLength = 1;  // We can always have a sequence of at least one 1
        while (a != 0) {
            if ((a & 1) == 1) {
                currentLength++;
            } else if ((a & 1) == 0) {
                /* Update to 0 (if next bit is 0) or currentLength (if next bit is 1).
                *  it's like you have been forgiven once! */
                previousLength = (a & 2) == 0 ? 0 : currentLength; //2 == 10 in binary, so two digts need to be 0 to get 0
                currentLength = 0;
            }
            maxLength = Math.max(previousLength + currentLength + 1, maxLength);
            a >>>= 1; // logical shift by 1, it's equivalent to go to the next binary digit to the left
        }
        return maxLength;
    }

    public static void main(String[] args) {
        int[][] cases = {{-1, 32}, {Integer.MAX_VALUE, 32}, {-10, 31}, {0, 1},
                {1, 2}, {15, 5}, {1775, 8}};
        for (int[] c : cases) {
            int x = flipBit(c[0]);
            boolean r = (c[1] == x);
            System.out.println(c[0] + ": " + x + ", " + c[1] + " " + r);
        }

    }
}
