package BitManipulation;

import CtCILibrary.AssortedMethods;

/** Write a function to determine the number of bits you would need to flip
 * to convert integer A to integer B
 * EXAMPLE
 * input: 29 (11101), 15 (01111), output 2
 */

public class Conversion {
    public static int bitSwapRequired(int a, int b) {
        int count = 0;
        int c = a ^ b;
        while (c != 0) {
            count++;
            /* continously flip the least significant bit and count how long it takes c to reach 0
            * The operation c = c & (c - 1) will clear the least significant bit with 1 to 0 in c
            * therefore only loop the number times that the digits have 1, a bit quicker than going
            * through every bit*/
            c = c & (c -1); // alternatively,shift the bit continously by 1

        }
        return count;
    }

    public static void main(String[] args) {
        int a = -23432;
        int b = 512132;
        System.out.println(a + ": " + AssortedMethods.toFullBinaryString(a));
        System.out.println(b + ": " + AssortedMethods.toFullBinaryString(b));
        System.out.println("Required number of bits: " + bitSwapRequired(a, b));
    }


}
