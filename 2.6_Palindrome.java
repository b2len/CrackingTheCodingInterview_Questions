package LinkedList;
import CtCILibrary.LinkedListNode;

/** Implement a function to check if a linked list is a palindrome
 *
 */
public class Palindrome {

    /** Solution
     * reverse the linked list and compare the reversed list to the original
     * Only need to compare the first half
     */

    public static boolean isPalindrome(LinkedListNode head) {
        LinkedListNode reversed = reverseAndCline(head);
        return isEqual(head, reversed);
    }

    public static LinkedListNode reverseAndCline(LinkedListNode node) {
        LinkedListNode head = null;
        while (node != null) {
            LinkedListNode n = new LinkedListNode(node.data); //Clone the node
            n.next = head;          // point to the previous head node
            head = n;               // point the head node to n, n.next continue point the node of previous head
            node = node.next;       // move the node to the next one in the linkedlist
        }
        return head;
    }

    public static boolean isEqual(LinkedListNode one, LinkedListNode two) {
        while (one != null & two != null) {
            if (one.data != two.data) {
                return false;
            }
            one = one.next;
            two = two.next;
        }
        return one == null && two == null;
    }

    public static void main(String[] args) {
        int length = 9;
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
        // nodes[length - 2].data = 9; // Uncomment to ruin palindrome

        LinkedListNode head = nodes[0];
        System.out.println(head.printForward());
        System.out.println(isPalindrome(head));
    }
}
