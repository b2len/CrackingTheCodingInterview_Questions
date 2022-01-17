package MathAndLogic;

/** You have 20 bottls of pills, 19 bottles have 1.0 gram pills, one has weight of 1.1 grams.
 * Given a scale that provides an exact measurement, how would you find the heavy bottle?
 * You can only use the scale once.
 */

public class HeavyPill {
    /** Solution
     * Lable the bottles. Take 1 pill from bottle 1, 2 pills from bottle 2,..... 21 pills from bottle 21
     * if all pilss were one gram each, the scale would read 210 grams, any extra weight is from the heavy bottle
     * Using (measured weight - 210) / 0.1 to get the bottle number
     */
}
