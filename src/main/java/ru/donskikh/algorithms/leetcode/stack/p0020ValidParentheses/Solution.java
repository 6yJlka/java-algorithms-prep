package ru.donskikh.algorithms.leetcode.stack.p0020ValidParentheses;

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == '(' || c == '{' || c == '['){
                stack.add(c);
            } else {
                if(stack.isEmpty()){
                    return false;
                }

                char last = stack.removeLast();
                if(c == ')' && last != '('){
                    return false;
                }
                if(c == '}' && last != '{'){
                    return false;
                }
                if(c == ']' && last != '['){
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}
