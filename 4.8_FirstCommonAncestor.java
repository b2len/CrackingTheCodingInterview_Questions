package TreesAndGraphs;

import CtCILibrary.TreeNode;

/** Design an algorithm and write code to find the first common ancestor of two nodes in a binary tree
 * Avoid storing additional nodes ina data structure
 * NOTE: This is not necessarily a binary search tree
 */

public class FirstCommonAncestor {
    /** Multiple solution exists. This one assumes node has a link to its parent
     *
     */

    public static TreeNode commonAncestor(TreeNode p, TreeNode q) {
        int delta = depth(p) - depth(q); // get difference in depth
        TreeNode first = delta > 0 ? q : p; // get shallower node
        TreeNode second = delta > 0 ? p : q; // get deeper node
        second = goUpBy(second, Math.abs(delta)); // move deeper node to depth of shallower

        /* Find where paths intersect */
        while (first != second && first != null && second != null) {
            first = first.parent;
            second = second.parent;
        }
        return first == null || second == null ? null : first;
    }

    public static int depth(TreeNode node) {
        int depth = 0;
        while (node != null) {
            node = node.parent;
            depth++;
        }
        return depth;
    }

    public static TreeNode goUpBy(TreeNode node, int delta) {
        while (delta > 0 && node != null) {
            node = node.parent;
            delta--;
        }
        return node;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        TreeNode root = TreeNode.createMinimalBST(array);
        TreeNode n3 = root.find(3);
        TreeNode n7 = root.find(7);
        TreeNode ancestor = commonAncestor(n3, n7);
        System.out.println(ancestor.data);
    }
}
