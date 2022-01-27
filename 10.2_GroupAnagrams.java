package SortingAndSearching;

import java.util.ArrayList;
import java.util.Arrays;

import CtCILibrary.AssortedMethods;
import CtCILibrary.HashMapList;

/** Write a method to sort an array of strings so that all the anagrams are next to each other
 *
 */

public class GroupAnagrams {
    public static void sort(String[] array) {
        /* a hashmap that maps from Strings to ArrayList<Integer>. */
        HashMapList<String, String> mapList = new HashMapList<String, String>();

        /* Group words by anagram */
        for (String s : array) {
            String key = sortChars(s);
            mapList.put(key, s);
        }

        /* Convert hash table to array */
        int index = 0;
        for (String key : mapList.keySet()) {
            ArrayList<String> list = mapList.get(key);      // get all anagram in one set
            for (String t : list) {             // iterate through the list and add each one into array
                array[index] = t;               // now all anagram are adjacent to each other
                index ++;
            }
        }
    }

    public static String sortChars(String s) {
        char[] content = s.toCharArray();
        Arrays.sort(content);
        return new String(content);
    }

    public static void main(String[] args) {
        String[] array = {"apple", "banana", "carrot", "ele", "duck", "papel", "tarroc", "cudk", "eel", "lee"};
        sort(array);
        System.out.println(AssortedMethods.stringArrayToString(array));
    }
}
