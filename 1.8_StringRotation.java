package ArraysAndStrings;

/** Assume that you have a method isSubstring which checks if one word is a substring of another.
 * Given two strings, check if one string is a rotation of the other using only one call to isSubstring
 * e.g. "waterbottle" is a rotation of "erbottlewat"
 */
public class StringRotation {
    /** Solution
     * In a string rotation, we cut string s1 into x and y, and then rearrange them to get to s2 yx
     * since xy = s1 and yx = s2, they yx will always be a substring of xyxy, akak s1s1
     * so the problem transform to do isSubstring(s1s1, s2)
     */
    public static boolean isSubstring(String big, String small) {
        if (big.indexOf(small) >= 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isRotation(String s1, String s2) {
        int len = s1.length();
        // check equal length and not empty
        if (len == s1.length() && len > 0) {
            // concatenate s1 and s1 within new buffer
            String s1s1 = s1 + s1;
            return isSubstring(s1s1, s2);
        }
        return false;
    }

    public static void main(String[] args) {
        String[][] pairs = {{"apple", "pleap"}, {"waterbottle", "erbottlewat"}, {"camera", "macera"}};
        for (String[] pair : pairs) {
            String word1 = pair[0];
            String word2 = pair[1];
            boolean is_rotation = isRotation(word1, word2);
            System.out.println(word1 + ", " + word2 + ": " + is_rotation);
        }
    }
}
