package BitManipulation;

/** Explain what the following code does: ((n & n-1)) == 0)
 *
 */

public class Debugger {
    /** Solution
     * It means that n and n-1 do not have a 1 bit in the same place, they never shared a 1
     * if n's last digit is 1, n-1 will change it to zero, and all the other digits are the same
     *  if n's last digit is 0, n-1 will change it to one, and borrow from the next least significant
     *  digit until it encounters the first 1, or the least significant one. Change it from one to zero
     *  then it's done
     *  Example: n = abcde1000, then n-1 = abcde0111. If n & (n-1) == 0, abcde must all be 0s
     *  which is 000001000. The value n is therefore a power of two
     *  So ((n & n-1)) == 0) checks if n is a power of 2 (or if n is 0)
     */

}
