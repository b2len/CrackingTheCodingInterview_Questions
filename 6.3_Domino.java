package MathAndLogic;

/** an 8 by 8 chessboard with two diagonal place been taken off
 * you have 31 dominos, each can occupy two squares, can you cover the whole chessboard
 * with these 31 dominos?
 */

public class ChessBoard {
    /** Solution
     * No. Initially the chessboard has 32 black squares and 32 white squares
     * taken off two diagonal squares, lets assume they are white, now we left with
     * 30 white squares and 32 blacks
     * each domino will take one black square and one white square. So 31 dominos will take
     * 31 black squares and 31 white squares eaxtly, which is different from our 30 white and 32 black
     * therefore it's not possible
     */
}
