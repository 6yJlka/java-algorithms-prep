package ru.donskikh.algorithms.leetcode.slidingwindow.p1004maxconsecutiveonesiii;

class Solution {
    public int longestOnes(int[] nums, int k) {
        int zeroCount = 0;
        int maxLen = Integer.MIN_VALUE;
        int left = 0;
        for(int right = 0; right < nums.length; right++){
            if(nums[right] == 0){
                zeroCount++;
            }
            while(zeroCount > k){
                if(nums[left] == 0){
                    zeroCount--;
                }
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;

    }
}
