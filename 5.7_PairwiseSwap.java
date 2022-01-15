package BitManipulation;

import CtCILibrary.AssortedMethods;

/** Write a program to swap odd and even bits in an integer with as few instructions as possible
 * e.g. bit 0 and bit 1 are swapped, bit 2 and bit 3 are swapped , and so on
 */

public class PairwiseSwap {
    /** Solution
     * Operating th8is as operating on the odds bits first, then and even bits
     * mask all odd bits with 10101010 in binary, then shift them right by 1 to put them in the even spots
     * Do an equivalent operation for even bits
     */

    public static int swapOddEvenBits(int x) {
        return ( ((x & 0xaaaaaaaa) >>> 1) | ((x & 0x55555555) << 1) );
    }

    public static void main(String[] args) {
        int a = 234321;
        System.out.println(a + ": " + AssortedMethods.toFullBinaryString(a));
        int b = swapOddEvenBits(a);
        System.out.println(b + ": " + AssortedMethods.toFullBinaryString(b));
    }
}
