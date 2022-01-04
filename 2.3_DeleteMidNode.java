package LinkedList;

import CtCILibrary.AssortedMethods;
import CtCILibrary.LinkedListNode;
/** Delete a node in the middle (any nodes except the first and last node)
 * given only access to that node
 */

public class DeleteMidNode {

    /** Solution
     * Copy the data from the next node to the given node
     * Then delete the next node
     */
    public static boolean deleteNode(CtCILibrary.LinkedListNode n) {
        if (n == null || n.next == null) {
            return false; // the input is the beginning or the end of the node
        }

        LinkedListNode next = n.next;
        n.data = next.data;
        n.next = next.next;
        return true;
    }
}
