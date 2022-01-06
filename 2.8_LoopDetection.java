package LinkedList;
import CtCILibrary.LinkedListNode;

/** Given a circular linked list, implement an algorithm that returns the node at the beginning of the loop
 * Circular linked list is a linked list in which a node's next pointer points to an earlier node
 * so as to make a loop in the linked list
 */
public class LoopDetection {
    /** Solution
     * Create two pointers, FastPointer and SlowPointer
     * Move Fast Pointer at a rate of 2 steps and SlowPointer at a rate of 1 step
     * When they collide, move SlowPointer to LinkedListHead. Keep FastPointer where it is
     * Move SlowPointer and FastPointer at a rate of one step. Return the new collision point
     */

    public static LinkedListNode FindBeginning(LinkedListNode head) {
        LinkedListNode slow = head;
        LinkedListNode fast = head;

        // Find meeting point
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }

        /* Error check - no meeting point, no loop */
        if (fast == null || fast.next == null) {
            return null;
        }

        /* Move slowto Head. keep fast at Meeting point. Each are k steps from the loop start
         * If they move at the same pace, they must meet at Loop Start
         */
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next.next;
        }

        /* both now point to the start of the loop. */

        return fast;
    }

    public static void main(String[] args) {
        int list_length = 100;
        int k = 10;

        // Create linked list
        LinkedListNode[] nodes = new LinkedListNode[list_length];
        for (int i = 0; i < list_length; i++) {
            nodes[i] = new LinkedListNode(i, null, i > 0 ? nodes[i - 1] : null);
        }

        // Create loop;
        nodes[list_length - 1].next = nodes[list_length - k];

        LinkedListNode loop = FindBeginning(nodes[0]);
        if (loop == null) {
            System.out.println("No loop");
        } else {
            System.out.println(loop.data);
        }
    }
}
