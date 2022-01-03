package ArraysAndStrings;
import CtCILibrary.*;
/** Given an image represented by an N-N matrix, where each pixel in the image is 4 bytes
 * write a method to rotate the image by 90 degrees.
 * Can you do this in place?
 */
public class RotateMatrix {
    /** Solution: implement the rotation in each layer
     * implement the swap index by index, i.e. temp = top, top = left, left = bottom etc....
     */

    public static boolean rotate(int[][] matrix) {
        //check if it is a square
        if (matrix.length == 0 || matrix.length != matrix[0].length) return false;
        int n = matrix.length;

        for (int layer = 0; layer < n / 2; layer++) { //loop through number of layers
            int first = layer;
            int last = n - 1 - layer;  // remember layer start from 0;
            for (int i = first; i < last; i++) { // loop through all the blocks in each chuck
                int offset = i - first; // each inner layer is one element less that the previous one
                int top = matrix[first][i]; // save top

                // left -> top
                matrix[first][i] = matrix[last - offset][first];

                // bottom -> left
                matrix[last - offset][first] = matrix[last][last - offset];

                // right -> bottom
                matrix[last][last - offset] = matrix[i][last];

                // top -> right
                matrix[i][last] = top;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] matrix = CtCILibrary.AssortedMethods.randomMatrix(3, 3, 0, 9);
        CtCILibrary.AssortedMethods.printMatrix(matrix);
        rotate(matrix);
        System.out.println();
        CtCILibrary.AssortedMethods.printMatrix(matrix);
    }





}
