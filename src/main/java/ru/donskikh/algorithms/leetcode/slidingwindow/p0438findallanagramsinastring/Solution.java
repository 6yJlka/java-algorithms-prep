package ru.donskikh.algorithms.leetcode.slidingwindow.p0438findallanagramsinastring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        int[] need = new int[26];
        int[] window = new int[26];
        List<Integer> result = new ArrayList<>();

        for(int i = 0; i < p.length(); i++){
            char c = p.charAt(i);
            need[c - 'a']++;
        }

        int left = 0;
        for(int right = 0; right < s.length(); right++){
            char c = s.charAt(right);
            window[c - 'a']++;
            while(right - left + 1 > p.length()){
                window[s.charAt(left) - 'a']--;
                left++;
            }

            if(right - left + 1 == p.length()){
                if(Arrays.equals(need,window)){
                    result.add(left);
                }
            }
        }

        return result;
    }
}
