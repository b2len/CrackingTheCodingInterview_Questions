package SortingAndSearching;

import java.io.FileReader;
import java.nio.file.FileSystemNotFoundException;
import java.util.Scanner;

/** Given an input file with four billion non-negative integers, provide an algorithm to generate
 *  an integer that is not contained in the file. Assume you have 1 GB of memory available for this task
 *
 *  FOLLOW UP
 *  What if you have only 10 MB of memory? Assume that all the values are distinct and we now have no more
 *  than one billion non-negative integers
 */

public class MissingIntFollowup {
    /** Solution: Two steps.
     * First step: Divide the array into blocks and find the block that has a size that is smaller than the design.
     * Given all values are distinct, this means it misses one or more numbers
     * Second step: using bit vector search in that identified block to get the missing integer
     *
     * How to find the appropriate block size? Assume blocksize = x, then the number of blocks is 2^31 / x; as there
     * is 2^31 non-negative integers in Java. This need to below our memory limit, which is 10 MB or 10 * 2^20 byte
     * roughly 2^23 bytes
     * Since we are holding ints in our array, which is 4 bytes in size, so our array can holds 2^21 maximum.
     * Therefore arraysize = 2^31 / blocksize <= 2^10  blocksize >= 2^10
     * The array size in each block also need to be fit in 2^23 bytes or 2^26 bits. So we can choose from
     * 2^10 to 2^26
     */

    public  static int findOpenNumber(String filename) throws FileSystemNotFoundException {
        int rangeSize = (1 << 20);  // 2^20 bits

        /* Get count of number of values within each block. */
        int[] blocks =getCountPerBlock(filename, rangeSize);

        /* Find a block with a missing value. */
        int blockIndex = findBlockWithMissing(blocks, rangeSize);
        if (blockIndex < 0) return -1;

        /* Create bit vector for items within this range */
        byte[] bitVector = getBitVectorForRange(filename, blockIndex, rangeSize);

        /* Find a zero in the bit vector, i.e. the missing element */
        int offset = findZero(bitVector);
        if (offset < 0) return -1;

        /* Compute missing value. */
        return blockIndex * rangeSize + offset;
    }

    public static int[] getCountPerBlock(String filename, int rangeSize) throws FileSystemNotFoundException {
        int arraySize = Integer.MAX_VALUE / rangeSize + 1;
        int[] blocks = new int[arraySize];

        Scanner in = new Scanner(new FileReader(filename));
        while (in.hasNextInt()) {
            int value = in.nextInt();
            blocks[value / rangeSize]++;
        }
        in.close();
        return blocks;
    }

    /* find a block whose counts is low */
    public static int findBlockWithMissing(int[] blocks, int rangeSize) {
        for (int i = 0; i < blocks.length; i++) {
            if (blocks[i] < rangeSize) {
                return i;
            }

        }
        return -1;
    }

    /* Create bit vector for items within a specific range */
    public static byte[] getBitVectorForRange(String filename, int blockIndex, int rangeSize) throws FileSystemNotFoundException {
        int startRange = blockIndex * rangeSize;
        int endRange = startRange + rangeSize;
        byte[] bitVector = new byte[rangeSize / Byte.SIZE];

        Scanner in = new Scanner(new FileReader(filename));
        while (in.hasNextInt()) {
            int value = in.nextInt();
            /* If the number is inside the block that's missing numbers, we record it */
            if (startRange <= value && value < endRange) {
                int offset = value - startRange;
                int mask = (1 << (offset % Byte.SIZE));
                bitVector[offset / Byte.SIZE] |= mask;
            }
        }
        in.close();
        return bitVector;
    }

    /* Find bit index that is 0 within one byte */
    public static int findZero(byte b) {
        for (int i = 0; i < Byte.SIZE; i++) {
            int mask = 1 << i;
            if (((b & mask) == 0)) {
                return i;
            }
        }
        return -1;
    }
    /* Find a zero within the bit vector and return the index */
    public static int findZero(byte[] bitVector) {
        for (int i = 0; i < bitVector.length; i++) {
            if (bitVector[i] != ~0) { // if not all 1s in that byte
                int bitIndex = findZero(bitVector[i]);
                return i * Byte.SIZE + bitIndex;
            }
        }
        return -1;
    }

    public static void generateFile(String filename, int max, int missing) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(filename);

        for (int i = 0; i < max && i >= 0; i++) {
            if (i != missing) {
                writer.println(i);
            }
            if (i % 10000 == 0) {
                System.out.println("Now at location: " + i);
            }
        }
        writer.flush();
        writer.close();
    }

    public static void main(String[] args) throws FileNotFoundException {
        String filename = "Ch 10. Scalability and Memory Limits/Q10_04_Missing_Int/input.txt";
        int max = 10000000;
        int missing = 1234325;
        System.out.println("Generating file...");
        generateFile(filename, max, missing);
        System.out.println("Generated file from 0 to " + max + " with " + missing + " missing.");
        System.out.println("Searching for missing number...");
        System.out.println("Missing value: " + findOpenNumber(filename));
    }

}
