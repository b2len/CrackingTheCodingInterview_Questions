package BitManipulation;

/** Given a real number between 0 and 1 (e.g. 0.72) that is passed in as a double
 * print the binary representation. If the number cannot be represented accurately
 * in binary with at most characters, print "ERROR".
 */
public class BinaryToString {
    /** Solution
     * Observe that, if the first digit after decimal is one, it will be larger than 0.5
     * similarly, after minus 0.5 from the double, if it is larger than 0.25, we know the second
     * digit is 1 and so on. By doing this continuously, we can check every digit
     */

    public static String printBinary(double num) {
        if (num >= 1 || num <= 0) {
            return "ERROR";
        }

        StringBuilder binary = new StringBuilder();
        double frac = 0.5;
        binary.append(".");
        while (num > 0) {
            // setting a limit on length: 32 characters
            if (binary.length() >= 32) {
                return "ERROR";
            }

            if (num >= frac) {
                binary.append(1);
                num -= frac;
            } else {
                binary.append(0);
            }
            frac /= 2;
        }
        return binary.toString();
    }

    public static void main(String[] args) {
        String bs = printBinary(.125);
        System.out.println(bs);

        for (int i = 0; i < 1000; i++) {
            double num = i / 1000.0;
            String binary = printBinary(num);
            if (!binary.equals("ERROR")) {
                System.out.println(num + " : " + binary);
            }
        }
    }
}
