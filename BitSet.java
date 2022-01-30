package SortingAndSearching;

public class BitSet {
    int[] bitset;

    public BitSet(int size) {
        /* Each integer is 32 bit, we can compress it to use only 1 bit to represent if it is in the given array
        * or not (1 or 0) at its specific position. Because we only need to know whether it exist or not, and
        * because we have its position to identify the number, that's why we can compress it 32 times */
        bitset = new int[(size >> 5) + 1];      // divide by 32 or 2^5

        /* >> is Signed right shift moves all the bits by given number of positions to the right.
		Similar to left shift, the right shift of n positions is equivalent to division by 2^n.
		Or division by 2^n -1 in case of odd numbers.
		*/
    }

    boolean get(int pos) {

        /* Every 32 integer share the same wordNumber */
        int wordNumber = (pos >> 5);        //divide by 32.  Example: 10 will return 0

        /*  Because integer has 32 bits, we can use each bit to represent an integer, e.g. 0 - 31;
            0x1F is 000.....11111 in binary, equivalent to mod 32 */
        int bitNumber = (pos & 0x1F);       // mod 32		 Example: 10 will return 10
        /* false means the number is not in the vector, true means it is */
        return (bitset[wordNumber] & (1 << bitNumber)) != 0; // Example: bitset[0] & 10000000000 == 0 return false
    }

    void set(int pos) {
        int wordNumber = (pos >> 5);        // divide by 32.	Example: pos = 0, wordNumber = 0
        int bitNumber = (pos & 0x1F);       // mod 32           Example: pos = 0, bitNumber = 0;
        /* x |= 5 is equivalent to x = x | 5, bitwise OR results in 1 when at least one of the compared bits is 1
         (or both), otherwise it results in 0.   */
        bitset[wordNumber] |= 1 << bitNumber;       //allocate the value to its corresponding position.
                                                    // Example: bitset[0] = 10000000000 for bitNumber 10
    }
}
