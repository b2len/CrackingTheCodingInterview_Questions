package LinkedList;

import java.util.HashSet;
import CtCILibrary.LinkedListNode;
/** Write code to remove duplicates from an unsorted linked list
 * FOLLOW UP: How would you solve this problem if a temporary buffer is not allowed?
 */
public class RemoveDups {
    /** Solution
     * 1. Using a hash table to track the duplicates then remove and iterating
     * 2. If no buffer allowed, we can iterate with two pointer
     * current iterate through the linked list, the runner checks all subsequent nodes for duplicates
     */

    public static void deleteDupBuffer(LinkedListNode n) {
        HashSet<Integer> set = new HashSet<Integer>();
        LinkedListNode previous = null;
        while (n != null) {
            if (set.contains(n.data)) {
                previous.next = n.next;
            } else {
                set.add(n.data);
                previous = n;
            }
            n = n.next;
        }
    }

    public static void deleteDupNoBuffer(LinkedListNode n) {
        LinkedListNode current = n;
        while (current != null) {
            /* remove all future nodes that are duplicates */
            LinkedListNode runner = current;
            while (runner.next != null) {
                if (runner.next.data == current.data) {
                    runner.next = runner.next.next;
                } else {
                    runner = runner.next;
                }
            }
            current = current.next;
        }
    }

    public static void main(String[] args) {
        LinkedListNode first = new LinkedListNode(0, null, null);
        LinkedListNode head = first;
        LinkedListNode second = first;
        for (int i = 1;  i <8;  i++) {
            second = new LinkedListNode(i % 2, null, null);
            first.setNext(second);
            second.setPrevious(first);
            first = second;
        }
        System.out.println(head.printForward());
        deleteDupNoBuffer(head);
        System.out.println(head.printForward());

    }
}
