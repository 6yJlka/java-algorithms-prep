package ru.donskikh.algorithms.leetcode.slidingwindow.p1343numberofsubarraysofsizekandaveragegreaterthanorequaltothreshold;

class Solution {
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int result = 0;
        int sum = 0;

        for (int i = 0; i < k; i++) {
            sum += arr[i];
        }

        if (sum >= threshold * k) {
            result++;
        }

        for (int right = k; right < arr.length; right++) {
            sum -= arr[right - k];
            sum += arr[right];

            if (sum >= threshold * k) {
                result++;
            }
        }

        return result;
    }
}