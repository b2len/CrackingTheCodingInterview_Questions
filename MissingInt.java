package SortingAndSearching;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/** Given an input file with four billion non-negative integers, provide an algorithm to generate
 *  an integer that is not contained in the file. Assume you have 1 GB of memory available for this task
 *
 *  FOLLOW UP
 *  What if you have only 10 MB of memory? Assume that all the values are distinct and we now have no more
 *  than one billion non-negative integers
 */

public class MissingInt {
    /** Since integer is represented as 32 bits in Java, so there's about 2^32 integers could be represented.
     * this is about 4 billion or about 2 billion positive and 2 billion negative.
     * We know 1 GB is 2^30 bytes, about 1 billion, each byte is 8 bits, so 1GB is 8 billion bits
     */
    public static long numberOfInts = ((long) Integer.MAX_VALUE) + 1;       // total number of Ints possible
    /* create a bit vector with 4 billion bits */
    public static byte[] bitfield = new byte [(int) (numberOfInts / 8)];

    public static void findOpenNumber() throws FileNotFoundException {
        Scanner in = new Scanner(new FileReader("Ch 10. Sorting and Searching/Q10_07_Missing_Int/input.txt"));
        while (in.hasNextInt()) {
            int n = in.nextInt ();
            /* Finds the corresponding number in the bitfield by using
             * the OR operator to set the nth bit of a byte
             * (e.g., 10 would correspond to bit 2 of index 1 in
             * the byte array). */
            bitfield [n / 8] |= 1 << (n % 8);
        }

        for (int i = 0; i < bitfield.length; i++) {
            for (int j = 0; j < 8; j++) {
                /* Retrieves the individual bits of each byte. When 0 bit
                 * is found, finds the corresponding value. */
                if ((bitfield[i] & (1 << j)) == 0) {
                    System.out.println (i * 8 + j);
                    return;
                }
            }
        }
    }

    public static void main(String[] args)  throws IOException {
        findOpenNumber();
    }


}
