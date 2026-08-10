package ru.donskikh.algorithms.leetcode.twopointers.p0125validpalindrome;

/*Время: O(n)
Дополнительная память: O(n)*/

public class StringBuilderSolution {
    public boolean isPalindrome(String s) {
        StringBuilder line = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            if(Character.isLetterOrDigit(s.charAt(i))){
                line.append(Character.toLowerCase(s.charAt(i)));
            }
        }

        String refLine = line.toString();
        int left = 0;
        int right = refLine.length() - 1;

        while(left < right){
            if(refLine.charAt(left) != refLine.charAt(right)){
                return false;
            }

            left++;
            right--;
        }

        return true;
    }
}
