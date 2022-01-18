package MathAndLogic;

/** There is a building of 100 floors. If egg drop from the Nth floor or above, it will break
 *  If it drop from below, it will not. Given two eggs, Find N while minimizing the number of
 *  drops for the worst case
 */

public class EggDrop {
    /** Solution
     * The goal is to minimize the worst case, so we need to create a system that have a perfectly
     * load-balanced with DropEgg1 + DropEgg2, this should be always the same
     * Therfore, assume Egg 1 starts at floor X, then go up by X - 1,  then X - 2, .... 1
     * until it goes to 100, slove for x ~ 13.65, round up to 14
     */

    public static int breakingPoint = 89;
    public static int countDrops = 0;

    public static boolean willBreak(int floor) {
        countDrops++;
        return floor >= breakingPoint;
    }

    public static int findBreakingPoint(int floors) {
        int interval = 14;
        int previousFloor = 0;
        int egg1 = interval;

        /* Drop egg1 at decreasing intervals. */
        while (!willBreak(egg1) && egg1 <= floors) {
            interval -= 1;
            previousFloor = egg1;
            egg1 += interval;
        }

        /* Drop egg2 at 1 unit increments */
        int egg2 = previousFloor + 1;
        while (egg2 < egg1 && egg2 <= floors && !willBreak(egg2)) {
            egg2 += 1;
        }

        /* If it didn't break, return -1 */
        return egg2 > floors ? -1 : egg2;
    }

    public static void main(String[] args){
        int max = 0;
        for (int i = 1; i <= 100; i++) {
            countDrops = 0;
            breakingPoint = i;
            int bp = findBreakingPoint(100);

            if (bp == breakingPoint) {
                System.out.println("SUCCESS: " + i + " -> " + bp + " -> " + countDrops);
            } else {
                System.out.println("ERROR: "  + i + " -> " + bp + " -> " + breakingPoint);
                break;
            }
            max = countDrops > max ? countDrops : max;
        }
        System.out.println(max);
    }
}
