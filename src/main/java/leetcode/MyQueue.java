package leetcode;

import java.util.Objects;
import java.util.Stack;

/**
 * Created by ShaoBin on 2016/3/14.
 */
class MyQueue {
    Stack<Integer> s1 = new Stack<>();
    Stack<Integer> s2 = new Stack<>();

    // Push element x to the back of queue.
    public void push(int x) {
        s1.push(x);
    }

    // Removes the element from in front of queue.
    public void pop() {
        if(!s2.isEmpty()) s2.pop();
        else {
            while(!s1.isEmpty()) s2.push(s1.pop());
            s2.pop();
        }
    }

    // Get the front element.
    public int peek() {
        if(!s2.isEmpty()) return s2.peek();
        else {
            while(!s1.isEmpty()) s2.push(s1.pop());
            return s2.peek();
        }
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return s1.empty() && s2.empty();
    }
}
