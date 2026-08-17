package ru.donskikh.algorithms.leetcode.prefixsum.p0724FindPivotIndex;

class Solution {
    public int pivotIndex(int[] nums) {
        int[] prefixSum = new int[nums.length];
        prefixSum[0] = nums[0];
        for(int i = 1; i < nums.length; i++){
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }

        int leftSum = 0;
        for(int i = 0; i < prefixSum.length; i++){
            int rightSum = prefixSum[nums.length - 1] - leftSum - nums[i];
            if(rightSum == leftSum){
                return i;
            }
            leftSum += nums[i];
        }
        return -1;
    }
}