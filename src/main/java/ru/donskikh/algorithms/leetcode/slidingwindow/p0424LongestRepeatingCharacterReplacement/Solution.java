package ru.donskikh.algorithms.leetcode.slidingwindow.p0424LongestRepeatingCharacterReplacement;

class Solution {
    public int characterReplacement(String s, int k) {
        int left = 0;
        int maxLen = 0;
        int maxFreq = 0;
        int[] freq = new int[26];
        for(int right = 0; right < s.length(); right++){
            char c = s.charAt(right);
            freq[c - 'A']++;
            maxFreq = Math.max(maxFreq, freq[c - 'A']);
            while(right - left + 1 - maxFreq > k){
                freq[s.charAt(left) - 'A']--;
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}