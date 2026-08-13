package ru.donskikh.algorithms.leetcode.slidingwindow.p0904fruitintobaskets;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int totalFruit(int[] fruits) {
        Map<Integer, Integer> freq = new HashMap<>();
        int left = 0;
        int maxCount = 0;
        for(int right = 0; right < fruits.length; right++){
            freq.put(fruits[right], freq.getOrDefault(fruits[right], 0) + 1);
            while(freq.size() > 2){
                freq.put(fruits[left], freq.getOrDefault(fruits[left], 0) - 1);
                if(freq.get(fruits[left]) == 0){
                    freq.remove(fruits[left]);
                }
                left++;
            }
            maxCount = Math.max(maxCount, right - left + 1);
        }

        return maxCount;
    }
}
