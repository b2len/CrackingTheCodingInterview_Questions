package RecursionAndDP;

import java.util.ArrayList;
import java.util.HashSet;

/** Imagine a robot sitting on the upper left corner of grid with r rows and c cloumns.
 * The robot can only move in two directions, right and down, but certain cells are "off limit"
 * such that robot cannot step on them. Design an algorith to find a path for the robot from the top left
 * to the bottom right
 */
public class RobotGrid {

    /** Solution
     * Assume you start from the endpoint (row, col)  and work backware
     * @param maze
     * @return
     */
    public static ArrayList<RobotPoint> getPath(boolean[][] maze) {
        if (maze == null || maze.length == 0) return null;
        ArrayList<RobotPoint> path = new ArrayList<RobotPoint>();
        HashSet<RobotPoint> failedPoints = new HashSet<RobotPoint>();
        if (getPath(maze, maze.length - 1, maze[0].length - 1, path, failedPoints)) {
            return path;
        }
        return null;
    }

    private static boolean getPath(boolean[][] maze, int row, int col, ArrayList<RobotPoint> path, HashSet<RobotPoint> failedPoints) {
        /* If out of bounds or not available, return.*/
        if (col < 0 || row < 0 || !maze[row][col]) {
            return false;
        }

        RobotPoint p = new RobotPoint(row, col);

        /* If we've already visited this cell, return. This reduce the running time from exponential to much lower*/
        if (failedPoints.contains(p)) {
            return false;
        }

        boolean isAtOrigin = (row == 0) && (col == 0);

        /* If there's a path from the start to my current location, add my location.*/
        if (isAtOrigin || getPath(maze, row, col - 1, path, failedPoints) || getPath(maze, row - 1, col, path, failedPoints)) {
            path.add(p);
            return true;
        }

        failedPoints.add(p); // Cache result
        return false;
    }
}
