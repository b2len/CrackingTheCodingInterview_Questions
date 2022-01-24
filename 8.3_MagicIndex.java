package RecursionAndDP;

import java.util.Arrays;

import CtCILibrary.AssortedMethods;

/** A magic index in a array A[1....n-1] is defined to be an index such taht A[i] = i.
 *  Given a sorted array of distinct integers, write a method to find a magic index,
 *  if one exists, in array A.
 *  FOLLOW UP: What if the values are not distinct?
 */

public class MagicIndex {
    /** Solution
     * Compare the middle element. Since it is sorted. if A[mid] < mid, then magic index must be on right side
     * This is because if the middle element is already too small to be a magic index, then everything left
     * will also be too small -- given the elements are distinct
     * If the elements is not distinct, we do not need to search everywhere on the left either.  For example
     * Since A[5] =3, we know A[4] could be a magic index, as its value will be less than 3. There fore
     * we need only search elements A[0] through A[3]. In summary
     * Left side: search indices from "start" through "Math.min(midIndex - 1, midValue).
     * Right side: search indices from "Math.min(midIndex + 1, midValue) through end.
     */

    public static int magicSlow(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == i) {
                return i;
            }
        }
        return -1;
    }

    public static int magicFast(int[] array, int start, int end) {
        if (end < start) {
            return -1;
        }
        int midIndex = (start + end) / 2;
        int midValue = array[midIndex];
        if (midValue == midIndex) {
            return midValue;
        }

        /* Search Left */
        int leftIndex = Math.min(midIndex - 1, midValue);
        int left = magicFast(array, start, leftIndex);
        if (left >= 0) {
            return left;
        }

        /* Search Right */
        int rightIndex = Math.min(midIndex + 1, midValue);
        int right = magicFast(array, rightIndex, end);
        if (right >= 0) {
            return right;
        }
        return -1;
    }

    public static int magicFast(int[] array) {
        return magicFast(array, 0, array.length - 1);
    }

    /* Creates an array that is sorted */
    public static int[] getSortedArray(int size) {
        int[] array = AssortedMethods.randomArray(size, -1 * size, size);
        Arrays.sort(array);
        return array;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            int size = AssortedMethods.randomIntInRange(5, 20);
            int[] array = getSortedArray(size);
            int v2 = magicFast(array);
            if (v2 == -1 && magicSlow(array) != -1) {
                int v1 = magicSlow(array);
                System.out.println("Incorrect value: index = -1, actual = " + v1 + " " + i);
                System.out.println(AssortedMethods.arrayToString(array));
                break;
            } else if (v2 > -1 && array[v2] != v2) {
                System.out.println("Incorrect values: index= " + v2 + ", value " + array[v2]);
                System.out.println(AssortedMethods.arrayToString(array));
                break;
            }
        }
    }

}
