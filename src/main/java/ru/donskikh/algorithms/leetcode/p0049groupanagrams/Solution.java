package ru.donskikh.algorithms.leetcode.p0049groupanagrams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode 49: Group Anagrams
 *
 * Pattern: HashMap Grouping
 * Approach: Sorted string as a key
 * Difficulty: Medium
 * Time complexity: O(n * k log k)
 * Space complexity: O(n * k)
 *
 * n — number of strings
 * k — maximum string length
 */
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> result = new HashMap<>();

        for(int i = 0; i < strs.length; i++){
            char[] chars = strs[i].toCharArray();
            Arrays.sort(chars);
            String word = new String(chars);

            result.computeIfAbsent(word, ignored -> new ArrayList<>()).add(strs[i]);
        }

        return new ArrayList<>(result.values());
    }
}