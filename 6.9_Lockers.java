package MathAndLogic;

/** There are 100 closed lockers in a hallway. A man begins by opening all 100 lockers.
 * Next, he closes every second locker. Then, on his thrid pass, he toggles every third lockers.
 * This process continues for 100 passes, such that on each pass i, then man toggles every ith locker.
 * After his 100th pass in the hallway, in which he toggles only locker #100, how many lockers are open?
 */

public class Lockers {
    /** Solution
     *  For a random door n, it will only be toggled when the rounds is a factor of n.
     *  For example, door 15 will be toggled on 1, 3, 5, and 15
     *  A door will be open, if the number of it being toggled is odd. So only the doors that have
     *  odd number of factors will let open
     *  When would n have odd factors? Knowing that each factor has another factor paired with it
     *  to get to the desired product, only the perfect square number have odd number of factors
     *  because the square root of the perfect square does not have a pair. Example 36 = 6 * 6,
     *  only count as one factor
     *  So we know there's 10 perfect squares between 1 - 100, therefore there are 10 lockers open
     *  at the end of this process
     */
}
