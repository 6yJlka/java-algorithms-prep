package ru.donskikh.algorithms.leetcode.slidingwindow.p0567permutationinstring;

import java.util.Arrays;

class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int[] need = new int[26];
        int[] window = new int[26];

        for(int i = 0; i < s1.length(); i++){
            char c = s1.charAt(i);
            need[c - 'a']++;
        }

        int left = 0;
        for(int right = 0; right < s2.length(); right++){
            char c = s2.charAt(right);
            window[c - 'a']++;
            while(right - left + 1 > s1.length()){
                window[s2.charAt(left) - 'a']--;
                left++;
            }

            if(right - left + 1 == s1.length()){
                if(Arrays.equals(need,window)){
                    return true;
                }
            }
        }

        return false;
    }
}