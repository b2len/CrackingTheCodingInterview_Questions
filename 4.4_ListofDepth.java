package TreesAndGraphs;

import com.sun.source.tree.Tree;
import CtCILibrary.TreeNode;
import CtCILibrary.AssortedMethods;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/** Given a binary tree, design an algorithm which creates a liked list of all the nodes
 *  at each depth (e.g., if you have a tree with depth D, you'll have D liked lists).
 */

public class ListofDepth {
    /** Solution
     * A modification of the pre-order traversal
     * where we pass in level + 1 to the next recursive call
     * Can be implemented using depth-first search or breadth-first search
     */

    public static void createLevelLinkedList(TreeNode root, ArrayList<LinkedList<TreeNode>> lists,
                                             int level) {
        if (root == null) return; // base case
        LinkedList<TreeNode> list = null;
        if (lists.size() == level) { // level not contained in list
            list = new LinkedList<TreeNode>();
            /* Levels are always traversed in order. So, if this is the first time we've visited level i,
             * we must have seen levels 0 through i - 1. We can therefore safely add the level at the end. */
            lists.add(list);
        } else {
            list = lists.get(level);
        }
        list.add(root);
        createLevelLinkedList(root.left, lists, level + 1);
        createLevelLinkedList(root.right, lists, level + 1);
    }

    public static ArrayList<LinkedList<TreeNode>> createLevelLinkedList(TreeNode root) {
        ArrayList<LinkedList<TreeNode>> lists = new ArrayList<LinkedList<TreeNode>>();
        createLevelLinkedList(root, lists, 0);
        return lists;
    }

    public static void printResult(ArrayList<LinkedList<TreeNode>> result) {
        int depth = 0;
        for(LinkedList<TreeNode> entry : result) {
            Iterator<TreeNode> i = entry.listIterator();
            System.out.print("Link list at depth " + depth + ":");
            while (i.hasNext()) {
                System.out.print(" " + ((TreeNode)i.next()).data);
            }
            System.out.println();
            depth++;
        }
    }

    public static void main(String[] args) {
        int[] nodes_flattened = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        TreeNode root = AssortedMethods.createTreeFromArray(nodes_flattened);
        ArrayList<LinkedList<TreeNode>> list = createLevelLinkedList(root);
        printResult(list);
    }


}
