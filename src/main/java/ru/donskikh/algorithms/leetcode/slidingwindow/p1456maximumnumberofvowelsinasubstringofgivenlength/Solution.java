package ru.donskikh.algorithms.leetcode.slidingwindow.p1456maximumnumberofvowelsinasubstringofgivenlength;

class Solution {
    public int maxVowels(String s, int k) {
        int maxVowels = 0;
        String vowels = "aeiou";

        for (int i = 0; i < k; i++) {
            if (vowels.indexOf(s.charAt(i)) >= 0) {
                maxVowels++;
            }
        }

        int tempVowels = maxVowels;
        int left = 0;

        for (int right = k; right < s.length(); right++) {
            if (vowels.indexOf(s.charAt(left)) >= 0) {
                tempVowels--;
            }

            if (vowels.indexOf(s.charAt(right)) >= 0) {
                tempVowels++;
            }

            left++;
            maxVowels = Math.max(maxVowels, tempVowels);
        }

        return maxVowels;
    }
}