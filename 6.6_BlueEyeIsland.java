package MathAndLogic;

/** A bunch of people on an island. All blue-eye people must leave. There will be a flight at 8 pm everyday
 * They can see each other's eye, but don't know their own. Nobody allow to tell them either
 * At least one has blue eye. How many days will it take the blue-eyeed people to leave?
 */

public class BlueEyeIsland {
    /** Solution
     * case blue = 1, he will see all others are brown eye, he will go the first night
     * case blue = 2, no one leave on first night as they don't know if they are blue eye. after day 1
     * they realize there's at least two.  They look around and realize they are blue eye. so they leave day 2
     * case blue = 3, by same logic, no one leave on day 2 either, so they know there're 3 blue eyes, so they leave day 3
     * case blue = n, leave on day n
     */
}
