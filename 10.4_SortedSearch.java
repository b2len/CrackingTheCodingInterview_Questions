package SortingAndSearching;

/** You are given a array-like data structure Listy which lacks a size method. It does, however, have an
 *  elementAt(i) method that returns the element at index i in O(1) time. If i is beyond the bounds of the
 *  data structure, it returns -1 (for this reason, the data structure only supports positive integer).
 *  Given a Listy which contains sorted, positive integers, find the index at which an element x occurs.
 *  If x occurs multiple times, you may return any index
 */

public class SortedSearch {
    /** Try to find the size of the array to search exponentially, i.e., 2, 4, 8, 16
     * Using binary search to identify the element
     * If the element during finding the size of the process is already larger than the target
     * then go to binary search right away as the array given is sorted
     */

    public static int binarySearch(Listy list, int value, int low, int high) {
        int mid;

        while (low <= high) {
            mid = (low + high) / 2;
            int middle = list.elementAt(mid);
            if (middle > value || middle == -1) {
                high = mid -1;
            } else if (middle < value) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static int search(Listy list, int value) {
        int index = 1;
        while (list.elementAt(index) != -1 && list.elementAt(index) < value) {
            index *= 2;
        }
        return binarySearch(list, value, index / 2, index);
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 4, 5, 6, 7, 9, 10, 11, 12, 13, 14, 16, 18};
        Listy list = new Listy(array);
        for (int a : array) {
            System.out.println(search(list, a));
        }
        System.out.println(search(list, 15));
    }
}
