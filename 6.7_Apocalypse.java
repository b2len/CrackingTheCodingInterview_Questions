package MathAndLogic;

import java.util.Random;

/** in the new post-apocalyptic world, all families should ensure they have one girl
 * They need to continue have children until they have one girl
 * What will the gender ratio of the new generation be?
 * Assume the odds of boy or girl is equal
 * Write a computer simulation of it
 */
public class Apocalypse {
    /** Solution
     * The ratio will be close to 1, as the odds of having a boy or girl did not change no matter
     * how many kids you have. Or you can add the probability together to prove it
     */

    public static int[] runOneFamily() {
        Random random = new Random();
        int boys = 0;
        int girls = 0;
        while (girls == 0) {    // until we have a gril
            if (random.nextBoolean()) { // girl
                girls += 1;
            } else {    // boy
                boys += 1;}
        }
        int[] genders = {girls, boys};
        return genders;
    }

    public static double runNFamily(int n) {
        int boys = 0;
        int girls = 0;
        for (int i = 0; i < n; i++) {
            int[] genders = runOneFamily();
            girls += genders[0];
            boys += genders[1];
        }
        return girls / (double) (boys + girls);
    }

    public static void main(String[] args) {
        double ratio = runNFamily(1000000);
        System.out.println(ratio);
    }
}
