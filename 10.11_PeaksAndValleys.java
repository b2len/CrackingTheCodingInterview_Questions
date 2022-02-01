package SortingAndSearching;

import CtCILibrary.AssortedMethods;

/** In an array of integers, a "peak" is an element which is greater than or equal to the adjacent integers
 * and a valley is an element which is less than or equal to the adjacent integers.
 * Given an array of integers, sort the array into a alternating sequence of peaks and valleys
 */

public class PeaksAndValleys {
    /** for a three element array, swap the largest one to the center will garantee to generate a vally
     * For nearby elements, if it will swap with the original vally, it will swap a large value with a small value
     * therefore ensure that the original vally stay as vally
     */

    public static void sortValleyPeak(int[] array) {
        for (int i = 1; i < array.length; i += 2 ) {
            int biggestIndex = maxIndex(array, i - 1, i, i + 1);
            if (i != biggestIndex) {
                swap(array, i, biggestIndex);
            }
        }
    }

    public static int maxIndex(int[] array, int a, int b, int c) {
        int len = array.length;
        int aValue = a >= 0 && a < len ? array[a] : Integer.MIN_VALUE;
        int bValue = b >= 0 && b < len ? array[b] : Integer.MIN_VALUE;
        int cValue = c >= 0 && c < len ? array[c] : Integer.MIN_VALUE;

        int max = Math.max(aValue, Math.max(bValue, cValue));

        if (aValue == max) {
            return a;
        } else if (bValue == max) {
            return b;
        } else return c;
    }

    public static void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }

    public static void main(String[] args) {
        int[] array = {48, 40, 31, 62, 28, 21, 64, 40, 23, 17};
        System.out.println(AssortedMethods.arrayToString(array));
        sortValleyPeak(array);
        System.out.println(AssortedMethods.arrayToString(array));
        System.out.println(Tester.confirmValleyPeak(array));
    }
}
