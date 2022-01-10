package TreesAndGraphs;

import CtCILibrary.TreeNode;

/** Implement a function to check if a binary tree is balanced.
 * For this question, a balanced tree is defined to be a tree such that
 * the heights of the two subtrees of any node never differ by more than one
 */
public class CheckBalanced {
    /** Solution
     * Recurse through the entire tree, and compute the heights of each subtree
     * If subtree is not balanced just return an error code instead of recurse the whole tree
     */
    public static int checkHeight(TreeNode root) {
        if (root == null) {
            return -1;
        }
        int leftHeight = checkHeight(root.left);
        if (leftHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE; // propagate error up

        int rightHeight = checkHeight(root.right);
        if (rightHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE; // propagate error up

        int heightDiff = leftHeight - rightHeight;
        if (Math.abs(heightDiff) > 1) {
            return Integer.MIN_VALUE; // Found error -> pass it back
        } else {
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

        public static boolean isBalanced(TreeNode root) {
            return checkHeight(root) != Integer.MIN_VALUE;
        }

        public static void main(String[] args){
        //Create balanced tree
            int[] array = {0, 1, 2, 3, 5, 6, 7, 8, 9, 10};
            TreeNode root = TreeNode.createMinimalBST(array);

            System.out.println("Is balanced? " + isBalanced(root));

            root.insertInOrder(4); // Add 4 to make it unbalanced

            System.out.println("Is balanced? " + isBalanced(root));
        }
}
