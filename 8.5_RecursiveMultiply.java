package RecursionAndDP;

/** Write a recursive function to multiply two positive integers without using the * operator (or / operator)
 * You can use addition, subtraction, and bit shifting, but you should lminimize the number of those operations
 */

public class RecursiveMultiply {
    /** Solution
     *  multiply two numbers is like counting the number of squares in a n * m grid
     *  We can count half of the squares, then double it
     *  To count half the squares, we repeat the same process
     *  However, when there is an odd number, one have to repeated calls, which is much slower than even
     *  One solution is to change the odd number to even and then add the bigger number to the end
     *  For example: (31, 35) -> (30, 35) + 35 = 2 * + (15, 35) + 35
     *  It will never repeat the same call, so there's no need to cache any information
     */

    public static int counter = 0;

    public static int minProductHelper(int smaller, int bigger) {
        if (smaller == 0) {
            return 0;
        } else if (smaller == 1) {
            return bigger;
        }

        int s = smaller >> 1; // divide by 2
        int halfProd = minProductHelper(s, bigger);

        if (smaller % 2 == 0) {
            counter ++;
            return halfProd + halfProd;
        } else {
            counter+=2;
            return halfProd + halfProd + bigger;
        }
    }
    public static int minProduct(int a, int b) {
        int bigger = a < b ? b : a;
        int smaller = a < b ? a : b;
        return minProductHelper(smaller, bigger);
    }

    public static void main(String[] args) {
        int a = 13494;
        int b = 22323;
        int product = a * b;
        int minProduct = minProduct(a, b);
        if (product == minProduct) {
            System.out.println("Success: " + a + " * " + b + " = " + product);
        } else {
            System.out.println("Failure: " + a + " * " + b + " = " + product + " instead of " + minProduct);
        }
        System.out.println("Adds: " + counter);
    }

    }


}
