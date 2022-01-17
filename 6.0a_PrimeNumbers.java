package MathAndLogic;

/** Check if a number is a prime number
 *
 */

public class PrimeNumbers {
    /* brute force iterate every number to check if it is divisible */
    public static boolean primeNaive(int n) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /* The square n is sufficient because for every number a which divides
        n evenly, there is a complement larger b, where a * b = n
        if a < sqrt n, then b > sqrt n; so no need to check both
     */
    public static boolean primebetter(int n) {
        int sqrt = (int) Math.sqrt(n);
        for (int i = 2; i < sqrt; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        for (int i = 2; i < 100; i++) {
            if (primebetter(i)) {
                System.out.println(i);
            }
        }
    }
}
