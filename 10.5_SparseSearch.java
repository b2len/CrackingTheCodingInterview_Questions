package SortingAndSearching;

/** Given a sorted array of strings that is interspersed with empty strings, write a method to find the
 *  location of a given string
 */

public class SparseSearch {
    public static int search(String[] strings, String str, int first, int last) {
        if (first > last) {
            return -1;
        }

        /* Move mid to the middle */
        int mid = (first + last) / 2;

        /* If mid is empty, find the closest non-empty String. */
        if (strings[mid].isEmpty()) {
            int left = mid - 1;
            int right = mid + 1;
            while (true) {
                if (left < first && right > last) {
                    return -1;
                } else if (right <= last && !strings[right].isEmpty()) {
                    mid = right;        // if the right value is not empty jump out
                    break;
                } else if (left >= first && !strings[left].isEmpty()) {
                    mid = left;         // if the left value is not empty jump out
                    break;
                }
                right++;                // if both sides are still empty, do another loop
                left--;
            }
        }

        /* check for string, and recurse if necessary */
        if (str.equals(strings[mid])) {
            return mid;
        } else if (strings[mid].compareTo(str) < 0) {   // search right
            return search(strings, str, mid + 1, last);
        } else if (strings[mid].compareTo(str) > 0) {   // search left
            return search(strings, str,first, mid - 1 );
        }
        return -1;
    }

    public static int search(String[] strings, String str) {
        if (strings == null || str == null || str.isEmpty()) {
            return -1;
        }
        return search(strings, str, 0, strings.length - 1);
    }

    public static void main(String[] args) {
        String[] stringList = {"apple", "", "", "banana", "", "", "", "carrot", "duck", "", "", "eel", "", "flower"};
        System.out.println(search(stringList, "ac"));
    }
}
