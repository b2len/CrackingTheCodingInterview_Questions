package RecursionAndDP;

import java.util.ArrayList;

/** Write a method to return all subsets of a set
 *
 */

public class PowerSet {
    /** Solution
     * Recognize for P(n), we need to generate P(n-1), clone the results and add the nth element
     * to each of these coloned set
     */

    public static ArrayList<ArrayList<Integer>> getSubsets(ArrayList<Integer> set, int index) {
        ArrayList<ArrayList<Integer>> allsubsets;
        if (set.size() == index) {                               // Base case - add empty set
            allsubsets = new ArrayList<ArrayList<Integer>> ();
            allsubsets.add(new ArrayList<Integer>());           // Empty set
        } else {
            allsubsets = getSubsets(set, index + 1);
            int item = set.get(index);                          // the new item to add to the clone
            ArrayList<ArrayList<Integer>> moresubsets = new ArrayList<ArrayList<Integer>>();
            for (ArrayList<Integer> subset : allsubsets) {
                ArrayList<Integer> newsubset = new ArrayList<Integer>();
                newsubset.addAll(subset);                       // make the clone
                newsubset.add(item);                            // add the item in
                moresubsets.add(newsubset);                     // add the newly created subset to the list
            }
            allsubsets.addAll(moresubsets);                     // finally add the whole new sub to the total list
        }
        return allsubsets;
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 3; i++) {
            list.add(i);
        }
        ArrayList<ArrayList<Integer>> subsets = getSubsets(list, 0);
        System.out.println(subsets.toString());
    }

}
