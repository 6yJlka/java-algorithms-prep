package ru.donskikh.algorithms.leetcode.p0242validanagram;

import java.util.Arrays;

/**
 * LeetCode 242: Valid Anagram
 *
 * Approach: Sorting
 * Difficulty: Easy
 * Time complexity: O(n log n)
 * Space complexity: O(n)
 */
public class SortingSolution {

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        char[] first = s.toCharArray();
        char[] second = t.toCharArray();

        Arrays.sort(first);
        Arrays.sort(second);

        return Arrays.equals(first, second);
    }
}