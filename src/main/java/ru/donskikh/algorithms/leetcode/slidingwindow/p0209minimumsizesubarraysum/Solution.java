package ru.donskikh.algorithms.leetcode.slidingwindow.p0209minimumsizesubarraysum;

class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int sum = 0;
        int left = 0;
        int minLen = Integer.MAX_VALUE;
        for(int right = 0; right < nums.length; right++){
            sum+= nums[right];
            if(sum >= target){
                minLen = Math.min(minLen, right - left + 1);
            }
            while(sum >= target){
                minLen = Math.min(minLen, right - left + 1);
                sum-= nums[left];
                left++;
                if(sum < target){
                    break;
                }
            }
        }

        if(minLen == Integer.MAX_VALUE){
            return 0;
        } else {
            return minLen;
        }
    }
}