package MathAndLogic;

/** Find an effective way to generate a list of primes
 *
 */

public class SieveOfEratosthenes {
    /** Solution
     * Recognizing that all non-prime numbers are divisible by a prime number
     * By crossing off all numbers divisible by primes, we wind up with a list of primes from 2 to max
     * start crossing off all numbers from 2, then 3, 5, 7 etc....
     */

    public static void crossOff(boolean[] flags, int prime) {
        /* Cross off remaining multiples of prime. We can start with
         * (prime*prime), because if we have a k * prime, where k < prime,
         * this value would have already been crossed off in a prior
         * iteration. */
        for (int i = prime * prime; i< flags.length; i += prime) {
            flags[i] = false;
        }
    }

    public static int getNextPrime(boolean[] flags, int prime) {
        int next = prime + 1;
        /* as we start from the smallest prime 2, all multiples of 2s get crossed off */
        while (next < flags.length && !flags[next]) {
            next++;
        }
        return next;
    }

    public static void init(boolean[] flags) {
        flags[0] = false;
        flags[1] = false;
        for (int i = 2; i < flags.length; i++) {
            flags[i] = true;
        }
    }

    public static int[] prune(boolean[] flags, int count) {
        int[] primes = new int[count];
        int index = 0;
        for (int i = 0; i < flags.length; i++) {
            if (flags[i]) {
                primes[index] = i;
                index++;
            }
        }
        return primes;
    }

    public static boolean[] sieveOfEratosthenes(int max) {
        boolean[] flags = new boolean[max + 1];

        init(flags);    // set all flags to true other than number 0 and 1
        int prime = 2;

        while (prime <= Math.sqrt(max)) {
            /* Cross off remaining multiples of prime */
            crossOff(flags, prime);

            /* Find next value which is true */
            prime = getNextPrime(flags, prime);
        }

        return flags;   //prune(flags, count)
    }

    public static void main(String[] args) {
        boolean[] primes = sieveOfEratosthenes(4);
        for (int i = 0;  i<primes.length; i++) {
            if (primes[i]) {
                System.out.println(i);
            }
        }
    }
}
