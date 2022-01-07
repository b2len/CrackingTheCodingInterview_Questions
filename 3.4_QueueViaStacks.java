package StacksAndQueues;

import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;
import CtCILibrary.AssortedMethods;

/** Implement a MyQueue class which implements a queue using two stacks
 *
 */

public class QueueViaStacks {
    /** Solution
     * The major difference between Queue and Stack is the order of operation
     * Queue: first in first out. Stack: first in last out
     * So need to modify peek() and pop() to go in reverse order
     * Have one stack that has oldest elements on top
     * Have the other has newest elements on top
     */

    Stack<T> stackNewest, stackOldest;

    public QueueViaStacks() {
        stackNewest = new Stack<T>();
        stackOldest = new Stack<T>();
    }

    public int size() {
        return stackOldest.size() + stackNewest.size();
    }

    public void add(T value) {
        // Push new element to the Newest stack
        stackNewest.push(value);
    }

    /* Move elements from Newest to Oldest. This is usually done so that we can
        do operation on Oldest
     */
    public void shiftStacks() {
        if (stackOldest.isEmpty()) {
            while (!stackNewest.isEmpty()) {
                stackOldest.push(stackNewest.pop());
            }
        }
    }

    public T peek() {
        shiftStacks();
        return stackOldest.peek(); // return the oldest item from Oldest Stack
    }

    public T remove() {
        shiftStacks();
        return stackOldest.pop(); // pop the oldest item
    }

    public static void main(String[] args) {
        QueueViaStacks<Integer> my_queue = new QueueViaStacks<Integer>();

        // Let's test our code against a "real" queue
        Queue<Integer> test_queue = new LinkedList<Integer>();

        for (int i = 0; i < 100; i++) {
            int choice = AssortedMethods.randomIntInRange(0, 10);
            if (choice <= 5) { // enqueue
                int element = AssortedMethods.randomIntInRange(1, 10);
                test_queue.add(element);
                my_queue.add(element);
                System.out.println("Enqueued " + element);
            } else if (test_queue.size() > 0) {
                int top1 = test_queue.remove();
                int top2 = my_queue.remove();
                if (top1 != top2) { // Check for error
                    System.out.println("******* FAILURE - DIFFERENT TOPS: " + top1 + ", " + top2);
                }
                System.out.println("Dequeued " + top1);
            }

            if (test_queue.size() == my_queue.size()) {
                if (test_queue.size() > 0 && test_queue.peek() != my_queue.peek()) {
                    System.out.println("******* FAILURE - DIFFERENT TOPS: " + test_queue.peek() + ", " + my_queue.peek() + " ******");
                }
            } else {
                System.out.println("******* FAILURE - DIFFERENT SIZES ******");
            }
        }
    }
}
