package BitManipulation;

/** A monochrome screen is stored as a single array of bytes, allowing 8 pixel stored in one byte
 * The screen has a width of w, which is divisible by 8, that is, no byte will be split across rows
 * The height of the screen, can be derived from the length of the array and width
 * Implement a function that draws a horizontal line from (x1, y) to (x2, y).
 * Method signature similar to: drawLine(byte[] screen, int width, int x1, int x2, int y)
 */

public class DrawLine {
    /**
     * Solution
     * If x1 and x2 are far away from ech other, several full bytes will be contained between them
     * approach everything before x1 & after x2 and between x1 and x2 separately
     * These full bytes can be set at a time by doing screen[byte_pos] = 0xFF (1111111111111 in binary)
     * The residual start and end of the line can be set using masks
     */

    public static int computeByteNum(int width, int x, int y) {
        return (width * y + x) / 8;     // it's byte location in an array, each byte contain 8 pixel
    }

    public static void drawLine(byte[] screen, int width, int x1, int x2, int y) {
        int start_offset = x1 % 8;
        int first_full_byte = x1 / 8;
        if (start_offset != 0) {
            first_full_byte++;  // counting from zero
        }

        int end_offset = x2 % 8;
        int last_full_byte = x2 / 8;
        if (end_offset != 7) {  // again, counting from zero, contain 8 pixel, the last one will be 7
            last_full_byte--;
        }
        // Set full bytes between x1 and x2
        for (int b = first_full_byte; b <= last_full_byte; b++) {
            screen[(width / 8) * y + b] = (byte) 0xFF;
        }

        // Create masks for start and end of line
        byte start_mask = (byte) (0xFF >> start_offset);    // set the bits before start_offset to 0, the rest is 1s
        byte end_mask = (byte) ~(0xFF >> (end_offset + 1)); // remember bit pos starts from 0, reverse the bit with ~

        // Set start and end of line
        if ((x1 / 8) == (x2 / 8)) {     // If x1 and x2 are in the same byte
            byte mask = (byte) (start_mask & end_mask);     //  Now only need to modify on one byte level
            screen[(width / 8) * y + (x1 / 8)] |= mask;
        } else {
            if (start_offset != 0) {
                int byte_number = (width / 8) * y + first_full_byte - 1;    //calculate the byte that need to modify
                screen[byte_number] |= start_mask;              // operate with mask
            }
            if (end_offset != 7) {
                int byte_number = (width / 8) * y + last_full_byte + 1;
                screen[byte_number] |= end_mask;
            }
        }

        public static void printByte(byte b) {
            for (int i = 7; i >= 0; i--) {
                char c = ((b >> i) & 1) == 1 ? '1' : '_';
                System.out.print(c);
            }
        }

        public static void printScreen(byte[] screen, int width) {
            int height = screen.length * 8 / width;
            for (int r = 0; r < height; r++) {
                for (int c = 0; c < width; c+=8) {
                    byte b = screen[computeByteNum(width, c, r)];
                    printByte(b);
                }
                System.out.println("");
            }
        }

        public static void main(String[] args) {
            int width = 8 * 1;
            int height = 1;
            for (int r = 0; r < height; r++) {
                for (int c1 = 0; c1 < width; c1++) {
                    for (int c2 = c1; c2 < width; c2++) {
                        byte[] screen = new byte[width * height / 8];

                        System.out.println("row: " + r + ": " + c1 + " -> " + c2);
                        drawLine(screen, width, c1, c2, r);
                        printScreen(screen, width);
                        System.out.println("\n\n");
                    }
                }
            }

        }
}

