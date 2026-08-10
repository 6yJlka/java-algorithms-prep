package ru.donskikh.algorithms.leetcode.hashing.p0217containsduplicate;

import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 217: Contains Duplicate
 *
 * Pattern: HashSet
 * Difficulty: Easy
 * Time complexity: O(n) average
 * Space complexity: O(n)
 */
public class Solution {

    public boolean containsDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();

        for (int number : nums) {
            if (!seen.add(number)) {
                return true;
            }
        }

        return false;
    }
}