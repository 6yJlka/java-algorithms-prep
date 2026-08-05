package ru.donskikh.algorithms.leetcode.p0643maximumaveragesubarrayi;

class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int windowSum = 0;
        for(int i = 0; i < k; i++){
            windowSum += nums[i];
        }

        int maxSum = windowSum;

        for(int right = k; right < nums.length; right++){
            windowSum += nums[right];
            windowSum -= nums[right - k];

            maxSum = Math.max(maxSum, windowSum);
        }

        return (double) maxSum / k;
    }
}
