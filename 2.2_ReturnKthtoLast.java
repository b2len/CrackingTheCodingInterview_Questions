package LinkedList;

import CtCILibrary.*;
/** Implement an algorithm to find the kth element to last element of a singly linked list
 *
 */
public class ReturnKthtoLast {
    /** Solution
     * Use two pointer and put them k nodes apart
     * move them at the same pace till one hit the end of list
     * Then the other one will be kith element away from the end of list
     */

    public static CtCILibrary.LinkedListNode nthToLast(CtCILibrary.LinkedListNode head, int k) {
        CtCILibrary.LinkedListNode p1 = head;
        CtCILibrary.LinkedListNode p2 = head;

        // move p1 k nodes into the list
        for (int i = 0; i < k; i++) {
            if (p1 == null) return null; // out of bound
            p1 = p1. next;
        }
        /* Move p1 and p2 at the same pace
        When p1 hit the end, p2 will be at the kth element
         */
        while (p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2;
    }

    public static void main(String[] args) {
        int[] array = {0, 1, 2, 3};
        CtCILibrary.LinkedListNode head = CtCILibrary.AssortedMethods.createLinkedListFromArray(array);
        for (int i = 0; i <= array.length + 1; i++) {
            CtCILibrary.LinkedListNode node = nthToLast(head, i);
            String nodeValue = node == null ? "null" : "" + node.data;
            System.out.println(i + ": " + nodeValue);
        }
    }

}
