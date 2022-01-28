package SortingAndSearching;

public class Listy {
    int[] array;

    public Listy(int[] array) {
        array = array.clone();
    }

    public int elementAt(int index) {
        if (index >= array.length) {
            return -1;
        }
        return array[index];
    }

}
