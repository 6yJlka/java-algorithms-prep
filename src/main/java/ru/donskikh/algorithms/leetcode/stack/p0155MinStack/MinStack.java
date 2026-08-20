package ru.donskikh.algorithms.leetcode.stack.p0155MinStack;

import java.util.ArrayDeque;
import java.util.Deque;

class MinStack {

    private Deque<Integer> stack = new ArrayDeque<>();
    private Deque<Integer> minStack = new ArrayDeque<>();

    public MinStack() {

    }

    public void push(int value) {
        stack.push(value);
        if(minStack.isEmpty()){
            minStack.push(value);
        } else if(value <= minStack.peek()){
            minStack.push(value);
        }
    }

    public void pop() {
        int removed = stack.pop();

        if (removed == minStack.peek()) {
            minStack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(value);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */