package StacksAndQueues;

import java.util.ArrayList;
import java.util.EmptyStackException;

/** Imagine a stack of plates, it might topple, if it gets too high
 * Implement a data structure SetOfStacks that start a new stack once exceeds some threshold
 * SetOfStacks should composed of several stacks and should create new one once the previous is full
 * FOLLOW UP: Implement a function popAt(int index) which performs a pop operation on a specific substack
 */

public class StackofPlates {
    ArrayList<Stack> stacks = new ArrayList<Stack>();
    public int capacity;

    public StackofPlates(int capacity) {
        this.capacity = capacity;
    }

    public Stack getLastStack() {
        if (stacks.size() == 0) {
            return null;
        }
        return stacks.get(stacks.size() - 1);
    }

    public void push(int v) {
        Stack last = getLastStack();
        if (last != null && !last.isFull()) { // add to last
            last.push(v);
        } else {        // must create new stack
            Stack stack = new Stack(capacity);
            stack.push(v);
            stacks.add(stack);
        }
    }

    public int pop() {
        Stack last = getLastStack();
        if (last == null) throw new EmptyStackException();
        int v = last.pop();
        if (last.size == 0) {
            stacks.remove(stacks.size() - 1);
        }
        return v;
    }

    public int popAt(int index) {
        return leftShift(index, true);
    }

    public int leftShift(int index, boolean removeTop) {
        Stack stack = stacks.get(index);
        int removed_item;
        if (removeTop) removed_item = stack.pop();
        else removed_item = stack.removeBottom();
        if (stack.isEmpty()) {
            stacks.remove(index);
        } else if (stacks.size() > index + 1) {
            int v = leftShift(index + 1, false);
            stack.push(v);
        }
        return removed_item;
    }

    public boolean isEmpty() {
        Stack last = getLastStack();
        return last == null || last.isEmpty();
    }

    public static void main(String[] args) {
        int capacity_per_substack = 5;
        StackofPlates set = new StackofPlates(capacity_per_substack);
        for (int i = 0; i < 34; i++) {
            set.push(i);
        }
        for (int i = 0; i < 35; i++) {
            System.out.println("Popped " + set.pop());
        }
    }
}

public class Node {
    public Node above;
    public Node below;
    public int value;
    public Node(int value) {
        this.value = value;
    }
}


public class Stack {
    private int capacity;
    public Node top;
    public Node bottom;
    public int size = 0;

    public Stack(int capacity) {
        this.capacity = capacity;
    }

    public boolean isFull() {
        return capacity == size;
    }

    public void join(Node above, Node below) {
        if (below != null) below.above = above;
        if (above != null) above.below = below;
    }

    public boolean push(int v) {
        if (size >= capacity) return false;
        size++;
        Node n = new Node(v);
        if (size == 1) bottom = n;
        join(n, top);
        top = n;
        return true;
    }

    public int pop() {
        if (top == null) throw new EmptyStackException();
        Node t = top;
        top = top.below;
        size--;
        return t.value;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int removeBottom() {
        Node b = bottom;
        bottom = bottom.above;
        if (bottom != null) bottom.below = null;
        size--;
        return b.value;
    }
}
