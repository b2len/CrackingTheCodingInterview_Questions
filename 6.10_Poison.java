package MathAndLogic;

import java.util.ArrayList;
import java.util.Random;

/** You have 1000 bottle of soda, one of them is poisoned. You have 10 test strips to test poison
 * You can put any number of drops on the test strip at once and you can reuse it as many times as you like
 * as long as the results are negative. However, you can only run tests once per day and it takes seven
 * days to return a result. How would you figure out the poisoned bottle in as few days as possible?
 * Follow up: Write code to simulate your approach
 */

public class Poison {
    /** Solution
     * Notice each strip is a binary indicator for poisoned or unpoisoned.
     * 10 strip can technically identify 2^10 = 1024 different id at once
     * Look at each bottle's binary representation, if there's a 1 in the ith digit, then will add a drop
     * of this bottle's contents to test strip i.
     * Wait for 7 days and then read the result. If test strip i is positive, then set bit i of the result value.
     * Reading all the test strips will give us the ID of the poisoned bottle.
     * So the optimized day is seven
     */

    public static ArrayList<Bottle> createBottles(int nBottles, int poisoned) {
        ArrayList<Bottle> bottles = new ArrayList<Bottle>();
        for (int i = 0; i < nBottles; i++) {
            bottles.add(new Bottle(i));
        }

        if (poisoned == -1) {
            Random random = new Random();
            poisoned = random.nextInt(nBottles);
        }
        bottles.get(poisoned).setAsPoisoned();

        System.out.println("Added poison to bottle " + poisoned);

        return bottles;
    }

    public static int findPoisonedBottle(ArrayList<Bottle> bottles, ArrayList<TestStrip> strips) {
        runTests(bottles, strips);
        ArrayList<Integer> positive = getPositiveOnDay(strips, 7);
        return setBits(positive);
    }

    /* Add bottles to test strips */
    public static void runTests(ArrayList<Bottle> bottles, ArrayList<TestStrip> testStrip) {
        for (Bottle bottle : bottles) {
            int id = bottle.getId();
            int bitIndex = 0;
            while (id > 0) {
                if ((id & 1) == 1) {
                    testStrip.get(bitIndex).addDropOnDay(0, bottle);
                }
                bitIndex++;
                id >>= 1;
            }
        }
    }

    /* Get test strips that are positive on a particular day. */
    public static ArrayList<Integer> getPositiveOnDay(ArrayList<TestStrip> testStrip, int day) {
        ArrayList<Integer> positive = new ArrayList<Integer>();
        for (TestStrip strip : testStrip) {
            int id = strip.getId();
            if (strip.isPositiveOnDay(day)) {
                positive.add(id);
            }
        }
        return positive;
    }

    /* Create number by setting  bits with indices specified in positive. */
    public static int setBits(ArrayList<Integer> positive) {
        int id = 0;
        for (Integer bitIndex : positive) {
            id |= 1 << bitIndex;
        }
        return id;
    }

    public static ArrayList<TestStrip> createTestStrips(int nTestStrips) {
        ArrayList<TestStrip> testStrips = new ArrayList<TestStrip>();
        for (int i = 0; i < nTestStrips; i++) {
            testStrips.add(new TestStrip(i));
        }
        return testStrips;
    }

    public static void main(String[] args) {
        int nBottles = 1000;
        int nTestStrips = 10;
        for (int poisoned = 0; poisoned < nBottles; poisoned++) {
            ArrayList<Bottle> bottles = createBottles(nBottles, poisoned);
            ArrayList<TestStrip> testStrips = createTestStrips(nTestStrips);
            int poisonedId = findPoisonedBottle(bottles, testStrips);
            System.out.println("Suspected Bottle: " + poisonedId);
            if (poisonedId != poisoned) {
                System.out.println("ERROR");
                break;
            }
        }
    }

}
