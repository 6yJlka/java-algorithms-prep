package ru.donskikh.algorithms.patterns.hashing;

/*Найти первое число, которое встретилось повторно при обходе массива слева направо. Если повторений нет — вернуть null.
* Временная сложность - O(n), Пространственная сложность - O(n) */

import java.util.HashSet;
import java.util.Set;

public class FirstRepeatedElement {
    private FirstRepeatedElement() {
    }

    public static Integer find(int[] numbers) {
        Set<Integer> seen = new HashSet<>();

        for(int num : numbers){
            if(!seen.add(num)){
                return num;
            }
        }
        return null;
    }
}
