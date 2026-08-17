package ru.donskikh.algorithms.leetcode.prefixsum.p0525ContiguousArray;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> firstIndex = new HashMap<>();
        firstIndex.put(0, -1);

        int balance = 0;
        int maxLen = 0;

        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 0){
                balance--;
            } else {
                balance++;
            }
            if(firstIndex.containsKey(balance)){
                maxLen = Math.max(maxLen, i - firstIndex.get(balance));
            } else {
                firstIndex.put(balance, i);
            }
        }

        return maxLen;
    }
}
