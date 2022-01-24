package RecursionAndDP;

import java.util.Stack;

/** You have 3 towers and N disks of different sizes which can slide onto any tower.
 * The puzzle starts with disks sorted in ascending order of size from top to bottom. You have the following constraints
 * 1) Only one disk can be moved at a time
 * 2) A disk is slid off the top of one tower onto another tower
 * 3) A disk cannot be placed on top of a smaller disk
 * Write a program to move the disks from the first tower to the last using Stacks
 */

public class TowersOfHanoi {

    private Stack<Integer> disks = new Stack<Integer>();
    private int index;
    public String name;

    public void Tower(int i) {
        disks = new Stack<Integer>();
        index = i;
    }

    public int index() {
        return index;
    }

    public void add(int d) {
        if (!disks.isEmpty() && disks.peek() <= d) {
            System.out.println("Error placing disk " + d);
        } else {
            disks.push(d);
        }
    }

    public void moveTopTo(Tower t) {
        int top = disks.pop();
        t.add(top);
    }

    public void print() {
        System.out.println("Contents of Tower " + name + ": " + disks.toString());
    }

    public void moveDisks(int quantity, Tower destination, Tower buffer) {
        if (quantity <= 0) return;

        moveDisks(quantity -1, buffer, destination );
        System.out.println("Move " + disks.peek() + " from " + this.name + " to " + destination.name);
        moveTopTo(destination);
        buffer.moveDisks(quantity - 1, destination, this);
    }

    public static void main(String[] args) {
        Tower source = new Tower();
        Tower destination = new Tower();
        Tower buffer = new Tower();

        source.name = "s";
        destination.name = "d";
        buffer.name = "b";

        /* Load up tower */
        int numberOfDisks = 5;
        for (int disk = numberOfDisks - 1; disk >= 0; disk--) {
            source.add(disk);
        }

        source.print();
        source.moveDisks(numberOfDisks, destination, buffer);
        destination.print();
    }


}
