package ArraysAndStrings;

/** Write an algorithm such that if an element in an M-N matrix is 0,
 * its entire row and column are set to 0
 */

public class ZeroMatrix {
    /** Solution
     * Check if certain row or column have zero value
     * set that row/column to zero
     * no need to know the exact location of the zero value, therefore improve performance
     */
    public static void nullifyRow(int[][] matrix, int row) {
        for (int j = 0; j < matrix[0].length; j++){
            matrix[row][j] = 0;
        }
    }

    public static void nullifyColumn(int[][] matrix, int col) {
        for (int i = 0; i < matrix.length; i++){
            matrix[i][col] = 0;
        }
    }

    public static void setZeros(int[][] matrix) {
        //create two arrays to track row and column respectively
        boolean[] row = new boolean[matrix.length];
        boolean[] col = new boolean[matrix[0].length];

        // store the row and column index with value 0
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = true;
                    col[j] = true;
                }
            }
        }
        // Nullify rows
        for (int i = 0; i < row.length; i++) {
            if (row[i]) {
                nullifyRow(matrix, i);
            }
        }
        for (int j = 0; j < row.length; j++) {
            if (row[j]) {
                nullifyColumn(matrix, j);
            }
        }
    }

    public static void main(String[] args) {
        int nrows = 10;
        int ncols = 15;
        int[][] matrix = CtCILibrary.AssortedMethods.randomMatrix(nrows, ncols, -10, 10);
        CtCILibrary.AssortedMethods.printMatrix(matrix);

        setZeros(matrix);

        System.out.println();

        CtCILibrary.AssortedMethods.printMatrix(matrix);
    }
}
