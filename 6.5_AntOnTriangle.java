package MathAndLogic;

/** There are n ants on different vertices of a triangle. What's the probability of collision (2 or 3)
 * if they start warlking on the sides of the triangle? Assume direction is random and equally possible
 * Speed are the same? What about n ants on a-vertex polygon?
 */

public class AntOnTriangle {
    /** the only possibillity that there's less than 2 collision is all three walking in the same direction
     * P(clockwise) = P(counterclockwise) = (1/2)^3
     * so the probability is = 1 - (1/2)^3 * 2
     * for n, P(collision) = 1 - P (same direction) = 1 - (1/2)^n * n = 1 - (1/2)^ (n - 1)
     */
}
