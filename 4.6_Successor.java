package TreesAndGraphs;

import CtCILibrary.TreeNode;

/** Write an algorithm to find the "next" node (i.e., in-order successor)
 *  of a given node in a binary search tree.
 *  You may assume that each node has a link to its parent
 */

public class Successor {
    /** Solution
     * Recall in-order traverse: left -> current -> right
     * Assume a hypothetical node n, the successor should be the leftmost node on the right subtree
     * If node n does not have a right subtree, we need to traverse upward to its parent p
     * if n is on p's left side, then the successor should be q itself
     * if n is on p's right side, we need to traverse upwards from q until we find a node x
     * that we have not fully traversed.
     */

    public static TreeNode inorderSucc(TreeNode n) {
        if (n == null) return null;

        /* Found right child -> return left most node of right subtree */
        if (n.parent == null || n.right != null) {
            return leftMostChild(n.right);
        } else {
            TreeNode q = n;
            TreeNode x = q.parent;
            // Go up until we're on the left instead of right
            while (x != null && x.left != q) {
                q = x;
                x = x.parent;
            }
            return x;
        }
    }

    public static TreeNode leftMostChild(TreeNode n) {
        if (n == null) {
            return null;
        }

        while (n.left != null) {
            n = n.left;
        }
        return n;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        TreeNode root = TreeNode.createMinimalBST(array);
        for (int i = 0; i < array.length; i++) {
            TreeNode node = root.find(array[i]);
            TreeNode next = inorderSucc(node);
            if (next != null) {
                System.out.println(node.data + "->" + next.data);
            } else {
                System.out.println(node.data + "->" + null);
            }
        }
    }
}
