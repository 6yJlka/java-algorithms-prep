package ru.donskikh.algorithms.leetcode.slidingwindow.p0003longestsubstringwithoutrepeatingcharacters;

import java.util.HashSet;
import java.util.Set;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> result = new HashSet<>();
        int maxLength = 0;
        int left = 0;
        for(int right = 0; right < s.length(); right++){
            char ch = s.charAt(right);
            while(result.contains(ch)){
                result.remove(s.charAt(left));
                left++;
            }
            result.add(ch);
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}