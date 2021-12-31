package ArraysAndStrings;

public class Is_Unique {

    /* this method explore the limited number of char in a string and use a boolean array
     * to track if a certain char is in the string or not
     */     
    public static boolean isUniqueChars(String str) {
        // Assume the string is ASCII code, Unicode would be 256. Clarify with your interviewer
        if (str.length() > 128) {
            return false;           // if the string is longer than 128, for sure there will be repeat chars
                                    // as the total number of unique char in ASCII is 128
        }
        boolean[] char_set = new boolean[128];      // create a boolean list, defalut is 'false'
        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i);                // obtain the integer that representing char using 'charAt' Function
            if (char_set[val]) return false;        // if the integer's corresponding boolean is true , then return flase 
            char_set[val] = true;                   // change the bollean value of the integer mapped to true
        }
        return true;
    }

    public static void main(String[] args) {
        String[] words = {"abcde", "hello", "apple", "kite", "padle"};
        for (String word : words) {
            System.out.println(word + ": " + isUniqueChars(word));
        }
    }
}
