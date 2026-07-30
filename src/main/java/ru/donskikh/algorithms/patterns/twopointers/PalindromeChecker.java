package ru.donskikh.algorithms.patterns.twopointers;

public class PalindromeChecker {

    public static boolean isPalindrome(String value) {
        int left = 0;
        int right = value.length() - 1;

        while (left < right){
            if(value.charAt(left) != value.charAt(right)){
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
}