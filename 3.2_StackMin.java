package StacksAndQueues;
import java.util.Stack;
/** How would you design a stack which, in addtition to push and pop, has a function min
 * which returns the minimum element? Push, pop and min should all operate in O(1) time
 */

public class StackMin extends Stack<Integer> {
    /** Solution
     * Using another stack to track the mins
      */
    Stack<Integer> s2;
    public void StackWithMin() {
        s2 = new Stack<Integer>();
    }

    public void push(int value) {
        if (value <= min()) {
            s2.push(value);
        }
        super.push(value);
    }

    public Integer pop() {
        //The super keyword in Java is a reference variable that is used to refer parent class objects.
        int value = super.pop();
        if (value == min ()) {
            s2.pop();
        }
        return value;
    }

    public int min() {
        if (s2.isEmpty()) {
            return Integer.MAX_VALUE;
        } else {
            return s2.peek();
        }
    }

    public static void main(String[] args) {
        StackMin stack = new StackMin();
        StackMin stack2 = new StackMin();
        int[] array = {2, 1, 3, 1};
        for (int value : array) {
            stack.push(value);
            stack2.push(value);
            System.out.print(value + ", ");
        }
        System.out.println('\n');
        for (int i = 0; i < array.length; i++) {
            System.out.println("Popped " + stack.pop().value + ", " + stack2.pop());
            System.out.println("New min is " + stack.min() + ", " + stack2.min());
        }
    }
}
