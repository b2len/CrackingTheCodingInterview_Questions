package LinkedList;
import CtCILibrary.LinkedListNode;

/** Write code to partition a linked list around a value x,
 * such that all nodes less than x come before all nodes greater or equal to x.
 * If x is contained within the list, the values of x only need to be after the
 * elements less than x
 */

public class Partition {
    /** Solution
     * Element bigger than the partition element got put at the tail
     * Element smaller than the partition put at the head
     */

    public static LinkedListNode partition(LinkedListNode node, int x) {
        LinkedListNode head = node;
        LinkedListNode tail = node;

        while(node != null) {
            LinkedListNode next = node.next;
            if(node.data < x) {
                // insert node at the head
                node.next = head;
                head = node;
            } else {
                // insert node at the tail
                tail.next = node;
                tail = node;
            }
            node = next;
        }
        tail.next = null;

        // The head has changed, so we need to return it to the user
        return head;
    }

    public static void main(String[] args) {
        int length = 5;
        LinkedListNode[] nodes = new LinkedListNode[length];
        for (int i = 0; i < length; i++) {
            nodes[i] = new LinkedListNode(i >= length / 2 ? length - i - 1 : i, null, null);
        }

        for (int i = 0; i < length; i++) {
            if (i < length - 1) {
                nodes[i].setNext(nodes[i + 1]);
            }
            if (i > 0) {
                nodes[i].setPrevious(nodes[i - 1]);
            }
        }

        LinkedListNode head = nodes[0];
        System.out.println(head.printForward());

        LinkedListNode h = partition(head.next.next, 2);
        System.out.println(h.printForward());
    }

}
