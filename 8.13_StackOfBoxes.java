package RecursionAndDP;

import java.util.ArrayList;
import java.util.Collections;

/** You have a stack of n boxes with widths wi, heights hi, and depth di. If you stack them from the largest
 * to the smallest on every dimension, implement a method to compute the height of the tallest possible stack
 * The height of a stack is the sum of the heights of each box
 */

public class StackOfBoxes {
    public static int createStack(ArrayList<Box> boxes) {
        /* Sort in decending order by height. */
        Collections.sort(boxes, new BoxComparator());
        int maxHeight = 0;
        int[] stackMap = new int[boxes.size()];         // the sequence of the stack
        for (int i = 0; i < boxes.size(); i++) {
            int height = createStack(boxes, i, stackMap);
            maxHeight = Math.max(maxHeight, height);
        }
        return maxHeight;
    }

    public static int createStack(ArrayList<Box> boxes, int bottomIndex, int[] stackMap) {
        /* if certain bottom already mapped, return the mapped value */
        if (bottomIndex < boxes.size() && stackMap[bottomIndex] > 0) {
            return stackMap[bottomIndex];
        }

        Box bottom = boxes.get(bottomIndex);
        int maxHeight = 0;
        for (int i = bottomIndex + 1; i < boxes.size(); i++) {
            if (boxes.get(i).canBeAbove(bottom)) {
                int height = createStack(boxes, i, stackMap);
                maxHeight = Math.max(height, maxHeight);
            }
        }
        maxHeight += bottom.height;
        stackMap[bottomIndex] = maxHeight;
        return maxHeight;
    }

    public static void main(String[] args) {
        Box[] boxList = { new Box(6, 4, 4), new Box(8, 6, 2), new Box(5, 3, 3), new Box(7, 8, 3), new Box(4, 2, 2), new Box(9, 7, 3)};
        ArrayList<Box> boxes = new ArrayList<Box>();
        for (Box b : boxList) {
            boxes.add(b);
        }

        int height = createStack(boxes);
        System.out.println(height);
    }
}
