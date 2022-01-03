package ArraysAndStrings;

/** Implement a method to perform basic string compression using the counts of repeated chars.
 * For example the string aabbcccccaaa would become a2b1c5a3
 * If the compressed string would be longer than the original one, you should return the original
 */
public class StringCompression {
    public static String compress(String str) {
        /** Solution: check the length of the compressed string length in advance
         * this approach take care cases that do not have a large number of repeating chars
         * Thus aviod creating a string that will never be used.
         * Also limited the size of the String that need to created (Stringbuild will double
         * its capacity every time it hits threshold)
         * Dowdside: cause a second loop through the char and some duplicated code
         */
        int finalLength = countCompression(str);
        if (finalLength >= str.length()) return str;

        StringBuffer compressed = new StringBuffer(finalLength); // initialize capacity
        int countconsecutive = 0;
        for(int i = 0; i < str.length(); i++) {
            countconsecutive++;

            // if next character is different than current, append this char to result.
            if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
                compressed.append(str.charAt(i));
                compressed.append(countconsecutive);
                countconsecutive = 0;
            }
        }
        return compressed.toString();
    }

    public static int countCompression(String str) {
        int compressedLength = 0;
        int countConsecutive = 0;
        for (int i = 0; i < str.length(); i++) {
            countConsecutive ++;

            // if next character is different than current, append this char to result.
            if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
                compressedLength += 1 + String.valueOf(countConsecutive).length();
                countConsecutive = 0;
            }
        }
        return compressedLength;
    }

    public static void main(String[] args) {
        String str = "aaabbbeefvce";
        System.out.println(str);
        System.out.println(compress(str));
    }
}
