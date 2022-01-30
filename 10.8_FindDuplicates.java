package SortingAndSearching;

import CtCILibrary.AssortedMethods;

/** You have an array with all numbers from 1 to N, where N is at most 32,000. The array may have duplicate entries
 *  and you do not know what N is. With only 4 kilobytes of memory available, how would you print all duplicate
 *  elements in the array
 */

public class FindDuplicates {

    public static void checkDuplicates(int[] array) {
        /** we have 4 kilobytes of memory, which means we can address up to 4 * 8 * 2^10 bits
         * Note that 32 * 2^10 bits is greater than 32000. We can create a bit vector with 32000 bits
         * where each bit represents one integer
         *
         * Using this bit vector, we can then iterate through the array, flagging each element v by setting
         * bit v to 1.
         */
        BitSet bs = new BitSet(32000);
        for (int i = 0; i < array.length; i++) {
            int num = array[i];
            int num0 = num - 1;     // bitset starts at 0, numbers start at 1
            if (bs.get(num0)) {     // if the num already exist in bit vector, then it's a duplicate
                System.out.println(num);
            } else {
                bs.set(num0);       // if the num not exist, fset the num0 bit to 1
            }
        }
    }

    public static void main(String[] args) {
        int[] array = AssortedMethods.randomArray(30, 1, 30);
        System.out.println(AssortedMethods.arrayToString(array));
        checkDuplicates(array);
    }
}
