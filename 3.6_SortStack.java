package StacksAndQueues;

import java.util.Stack;
import CtCILibrary.AssortedMethods;
/** Write program to sort a stack such that the smallest items are on the top.
 * Use can use an additional temporary stack but no other structure
 * The stack supports the following operation: push, pop, peek, isEmpty
 */
public class SortStack {
    public static void SortStack(Stack<Integer> s) {
        Stack<Integer> r = new Stack<Integer>();
        while (!s.isEmpty()) {
            /* Insert each element in s in sorted order into r. */
            int tmp = s.pop();
            // when the element in r is larger, move it to s first
            while (!r.isEmpty() && r.peek() > tmp) {
                s.push(r.pop());
            }
            // add the element to the r
            r.push(tmp);
        }

        /* Copy the elements from r back into s. */
        while (!r.isEmpty()) {
            s.push(r.pop());
        }
    }

    public static void main(String[] args) {
        Stack<Integer> s = new Stack<Integer>();
        for (int i = 0; i < 10; i++) {
            int r = AssortedMethods.randomIntInRange(0,  1000);
            s.push(r);
        }

        SortStack(s);

        while(!s.isEmpty()) {
            System.out.println(s.pop());
        }

    }
}
