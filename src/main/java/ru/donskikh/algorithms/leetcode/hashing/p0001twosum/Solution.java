package ru.donskikh.algorithms.leetcode.hashing.p0001twosum;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 1: Two Sum
 *
 * Pattern: HashMap
 * Difficulty: Easy
 * Time complexity: O(n) average
 * Space complexity: O(n)
 */
public class Solution {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> expected = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (expected.containsKey(nums[i])) {
                return new int[]{expected.get(nums[i]), i};
            }

            int complement = target - nums[i];
            expected.put(complement, i);
        }

        return  new int[]{0,0};
    }
}