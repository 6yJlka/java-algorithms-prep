package ru.donskikh.algorithms.leetcode.prefixsum.p0560SubarraySumEqualsK;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        freq.put(0,1);
        int[] prefixSum = new int[nums.length];
        prefixSum[0] = nums[0];
        for(int i = 1; i < nums.length; i++){
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }

        for(int i = 0; i < prefixSum.length; i++){
            int key = prefixSum[i] - k;
            if(freq.containsKey(key)){
                count+= freq.get(key);
            }
            freq.put(prefixSum[i], freq.getOrDefault(prefixSum[i], 0) + 1);
        }

        return count;
    }
}
