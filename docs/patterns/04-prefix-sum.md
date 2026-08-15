# Prefix Sum

## 1. Что такое Prefix Sum

**Prefix Sum** — паттерн, при котором заранее вычисляются суммы элементов от начала массива до каждой позиции.

Для массива:

```text
nums = [2, 4, 1, 7, 3]
```

prefix sum будет:

```text
prefix = [2, 6, 7, 14, 17]
```

Каждый элемент означает:

```text
prefix[0] = 2
prefix[1] = 2 + 4 = 6
prefix[2] = 2 + 4 + 1 = 7
prefix[3] = 2 + 4 + 1 + 7 = 14
prefix[4] = 2 + 4 + 1 + 7 + 3 = 17
```

То есть:

```text
prefix[i] = nums[0] + nums[1] + ... + nums[i]
```

Prefix Sum позволяет один раз выполнить preprocessing за `O(n)`, после чего быстро получать информацию о суммах диапазонов.

---

## 2. Построение Prefix Sum

Обычный вариант:

```java
int[] prefix = new int[nums.length];

prefix[0] = nums[0];

for (int i = 1; i < nums.length; i++) {
    prefix[i] = prefix[i - 1] + nums[i];
}
```

Для:

```text
nums = [3, 2, 5, 1, 4]
```

получаем:

```text
prefix = [3, 5, 10, 11, 15]
```

Построение требует:

```text
Время: O(n)
Память: O(n)
```

---

## 3. Сумма диапазона

Главное применение Prefix Sum — быстрое получение суммы диапазона `[left, right]`.

Допустим:

```text
nums = [3, 2, 5, 1, 4]
```

Нужно найти сумму элементов с индекса `1` до `3`:

```text
2 + 5 + 1 = 8
```

Из prefix:

```text
prefix[3] = 11
prefix[0] = 3
```

Поэтому:

```text
sum(1, 3) = prefix[3] - prefix[0]
          = 11 - 3
          = 8
```

Общая формула:

```text
sum(left, right) =
prefix[right] - prefix[left - 1]
```

если:

```text
left > 0
```

Если диапазон начинается с нулевого индекса:

```text
left == 0
```

то:

```text
sum(0, right) = prefix[right]
```

В Java:

```java
if (left == 0) {
    return prefix[right];
}

return prefix[right] - prefix[left - 1];
```

После построения prefix каждый такой запрос выполняется за:

```text
O(1)
```

---

## 4. Prefix Sum длины n + 1

Существует второй распространённый вариант.

Вместо:

```java
int[] prefix = new int[nums.length];
```

создаётся:

```java
int[] prefix = new int[nums.length + 1];
```

При этом:

```text
prefix[0] = 0
```

а элементы строятся так:

```java
for (int i = 0; i < nums.length; i++) {
    prefix[i + 1] = prefix[i] + nums[i];
}
```

Например:

```text
nums   = [3, 2, 5, 1, 4]

prefix = [0, 3, 5, 10, 11, 15]
```

Теперь сумма диапазона `[left, right]` всегда считается одной формулой:

```java
return prefix[right + 1] - prefix[left];
```

Отдельная проверка:

```java
if (left == 0)
```

уже не нужна.

Такой вариант часто удобнее, потому что уменьшает количество граничных случаев.

---

## 5. Зачем нужен preprocessing

Без Prefix Sum для каждого запроса:

```text
sum(left, right)
```

приходилось бы проходить диапазон циклом.

Например:

```java
int sum = 0;

for (int i = left; i <= right; i++) {
    sum += nums[i];
}
```

В худшем случае:

```text
O(n)
```

на один запрос.

Если запросов `m`, сложность может стать:

```text
O(n * m)
```

При Prefix Sum:

```text
построение prefix = O(n)
каждый запрос = O(1)
```

Общая сложность:

```text
O(n + m)
```

Поэтому Prefix Sum особенно полезен, когда массив не меняется, а запросов суммы диапазона много.

---

# 6. Running Sum

Самая простая форма Prefix Sum — задача, где нужно вернуть сами накопленные суммы.

Например:

```text
nums = [1, 2, 3, 4]
```

Результат:

```text
[1, 3, 6, 10]
```

Решение:

```java
int[] result = new int[nums.length];

result[0] = nums[0];

for (int i = 1; i < nums.length; i++) {
    result[i] = result[i - 1] + nums[i];
}
```

Здесь `result` одновременно является prefix-массивом.

Сложность:

```text
O(n) time
O(n) space
```

Если разрешено изменять входной массив, можно использовать его же:

```java
for (int i = 1; i < nums.length; i++) {
    nums[i] += nums[i - 1];
}

return nums;
```

Тогда дополнительная память:

```text
O(1)
```

---

# 7. Prefix Sum + HashMap

Prefix Sum становится значительно интереснее, когда нужно не просто получать сумму конкретного диапазона, а искать подмассивы с определённой суммой.

Например:

```text
nums = [1, 1, 1]
k = 2
```

Нужно определить количество подмассивов с суммой `2`.

Ответ:

```text
[1, 1] на индексах 0..1
[1, 1] на индексах 1..2

Итого: 2
```

---

# 8. Главная формула

Пусть:

```text
currentPrefix
```

— сумма элементов от начала массива до текущего индекса.

Пусть:

```text
previousPrefix
```

— какая-то prefix sum, которая встречалась раньше.

Тогда сумма элементов между этими двумя точками равна:

```text
currentPrefix - previousPrefix
```

Нам нужен подмассив с суммой:

```text
k
```

Следовательно:

```text
currentPrefix - previousPrefix = k
```

Переносим:

```text
previousPrefix = currentPrefix - k
```

Это основная формула паттерна:

```text
currentPrefix - k
```

Для каждой текущей prefix sum нужно проверить, встречалась ли раньше prefix sum со значением:

```text
currentPrefix - k
```

---

# 9. Зачем нужен HashMap

Нужно быстро отвечать на вопрос:

```text
сколько раз раньше встречалась конкретная prefix sum?
```

Для этого используется:

```java
Map<Integer, Integer> freq = new HashMap<>();
```

Map хранит:

```text
prefix sum → количество её предыдущих появлений
```

Например:

```text
freq = {
    0 -> 2,
    1 -> 3,
    5 -> 1
}
```

означает:

```text
prefix sum 0 встречалась 2 раза
prefix sum 1 встречалась 3 раза
prefix sum 5 встречалась 1 раз
```

---

# 10. Почему нужно хранить количество, а не только наличие

Нельзя ограничиться:

```java
if (freq.containsKey(key)) {
    count++;
}
```

Потому что одна и та же prefix sum может встречаться несколько раз.

Каждое её появление может означать отдельный подходящий подмассив.

Поэтому:

```java
count += freq.get(key);
```

Если искомая previous prefix sum встречалась:

```text
3 раза
```

значит существует:

```text
3 разных подмассива
```

с нужной суммой, заканчивающихся в текущей позиции.

---

# 11. Начальное значение 0 → 1

Для Prefix Sum + HashMap часто используется:

```java
freq.put(0, 1);
```

Это очень важная строка.

Она означает:

```text
до начала массива сумма элементов = 0
```

Это можно представить как виртуальную prefix sum перед индексом `0`.

Она нужна для подмассивов, которые начинаются с самого начала массива.

Например:

```text
nums = [1, 2]
k = 3
```

На втором элементе:

```text
currentPrefix = 3
```

Ищем:

```text
currentPrefix - k
= 3 - 3
= 0
```

Если заранее существует:

```text
0 → 1
```

алгоритм понимает, что:

```text
[1, 2]
```

является подходящим подмассивом.

Без:

```java
freq.put(0, 1);
```

подмассивы, начинающиеся с нулевого индекса, пришлось бы обрабатывать отдельно.

---

# 12. Пример Prefix Sum + HashMap

Возьмём:

```text
nums = [1, -1, 0]
k = 0
```

Подходящие подмассивы:

```text
[1, -1]
[0]
[1, -1, 0]
```

Ответ:

```text
3
```

Начальное состояние:

```text
prefix = 0
count = 0

freq = {
    0 -> 1
}
```

Первый элемент:

```text
nums[0] = 1

prefix = 1
key = prefix - k
    = 1 - 0
    = 1
```

В `freq` значения `1` ещё нет.

После обработки:

```text
freq = {
    0 -> 1,
    1 -> 1
}
```

Следующий элемент:

```text
nums[1] = -1

prefix = 0
key = 0 - 0 = 0
```

`0` встречалась раньше один раз:

```text
count += 1
```

Нашли:

```text
[1, -1]
```

После этого:

```text
freq = {
    0 -> 2,
    1 -> 1
}
```

Следующий элемент:

```text
nums[2] = 0

prefix = 0
key = 0
```

Но теперь:

```text
freq.get(0) = 2
```

Поэтому:

```text
count += 2
```

Нашли ещё:

```text
[0]
[1, -1, 0]
```

Итого:

```text
count = 3
```

---

# 13. Почему сначала ищем, а потом добавляем текущий prefix

Порядок операций важен.

Правильно:

```java
int key = prefix - k;

if (freq.containsKey(key)) {
    count += freq.get(key);
}

freq.put(
        prefix,
        freq.getOrDefault(prefix, 0) + 1
);
```

То есть:

```text
1. найти подходящие предыдущие prefix sums
2. посчитать новые подмассивы
3. добавить текущую prefix sum в историю
```

Мы ищем только среди prefix sums, существовавших **до текущей позиции**.

После этого текущая prefix sum становится доступной для следующих элементов.

---

# 14. Вариант без отдельного массива Prefix Sum

Не всегда нужно создавать:

```java
int[] prefixSum = new int[nums.length];
```

Если алгоритму нужна только текущая prefix sum, достаточно:

```java
int prefix = 0;
```

Например:

```java
int count = 0;
int prefix = 0;

Map<Integer, Integer> freq = new HashMap<>();
freq.put(0, 1);

for (int num : nums) {
    prefix += num;

    int key = prefix - k;

    count += freq.getOrDefault(key, 0);

    freq.put(
            prefix,
            freq.getOrDefault(prefix, 0) + 1
    );
}

return count;
```

Отдельный prefix-массив здесь не нужен.

Сложность:

```text
Время: O(n)
Память: O(n)
```

Память остаётся `O(n)` из-за `HashMap`.

---

# 15. Почему Sliding Window подходит не всегда

Задачи на суммы подмассивов иногда хочется решить через Sliding Window.

Но Sliding Window особенно удобно работает, когда элементы неотрицательные.

Например:

```text
[1, 2, 3, 4]
```

Если сумма окна слишком большая, уменьшение окна слева гарантированно уменьшает сумму.

Но с отрицательными числами:

```text
[1, -5, 10, -2]
```

это правило перестаёт работать.

При добавлении нового элемента сумма может:

```text
увеличиться
уменьшиться
не измениться
```

При удалении элемента слева происходит то же самое.

Поэтому условие вида:

```text
если sum > k
→ двигать left
```

уже не гарантирует корректность.

В таких задачах часто используется:

```text
Prefix Sum + HashMap
```

---

# 16. Когда распознавать Prefix Sum

Prefix Sum стоит рассматривать, если в задаче встречаются вопросы вида:

```text
сумма диапазона
сумма подмассива
количество подмассивов с определённой суммой
несколько запросов по одному массиву
разность накопленных значений
```

Особенно сильный сигнал:

```text
дан неизменяемый массив
+
будет много запросов суммы диапазона
```

Тогда часто нужен обычный prefix-массив.

Если же требуется:

```text
найти количество подмассивов с суммой k
```

особенно при наличии отрицательных чисел, стоит подумать о:

```text
Prefix Sum + HashMap
```

---

# 17. Сложность

Обычное построение Prefix Sum:

```text
Time: O(n)
Space: O(n)
```

Запрос суммы диапазона после preprocessing:

```text
Time: O(1)
```

Для Prefix Sum + HashMap:

```text
Time: O(n)
Space: O(n)
```

Каждый элемент обрабатывается один раз, а операции `HashMap` в среднем выполняются за:

```text
O(1)
```

---

# 18. Типичные ошибки

Первая частая ошибка — неправильно определить границы:

```text
prefix[right] - prefix[left]
```

для обычного prefix-массива неверно.

Правильно:

```text
prefix[right] - prefix[left - 1]
```

при:

```text
left > 0
```

Вторая ошибка — забыть обработать:

```text
left == 0
```

Либо используется отдельная проверка:

```java
if (left == 0) {
    return prefix[right];
}
```

либо prefix-массив длины `n + 1`.

Третья ошибка в `Prefix Sum + HashMap` — хранить:

```text
currentPrefix - k
```

в `HashMap`.

В Map нужно добавлять именно:

```text
currentPrefix
```

Потому что `currentPrefix - k` — это значение, которое мы **ищем**, а `currentPrefix` — значение, которое мы уже **увидели** и должны сохранить для будущих элементов.

Четвёртая ошибка — писать:

```java
count++;
```

при найденной prefix sum.

Если такая prefix sum встречалась несколько раз:

```java
count += freq.get(key);
```

Пятая ошибка — забыть:

```java
freq.put(0, 1);
```

из-за чего теряются подмассивы, начинающиеся с индекса `0`.

Шестая ошибка — сначала сохранить текущую prefix sum, а затем искать `prefix - k`.

Обычно нужно:

```text
сначала поиск
→ затем запись текущего prefix
```

---

# 19. Основные формулы

Обычный Prefix Sum:

```text
prefix[i] =
prefix[i - 1] + nums[i]
```

Сумма диапазона:

```text
sum(left, right) =
prefix[right] - prefix[left - 1]
```

или для prefix длины `n + 1`:

```text
sum(left, right) =
prefix[right + 1] - prefix[left]
```

Поиск подмассива с суммой `k`:

```text
currentPrefix - previousPrefix = k
```

Следовательно:

```text
previousPrefix = currentPrefix - k
```

---

# 20. Шаблон обычного Prefix Sum

```java
int[] prefix = new int[nums.length];

prefix[0] = nums[0];

for (int i = 1; i < nums.length; i++) {
    prefix[i] = prefix[i - 1] + nums[i];
}
```

Запрос:

```java
if (left == 0) {
    return prefix[right];
}

return prefix[right] - prefix[left - 1];
```

---

# 21. Шаблон Prefix Sum длины n + 1

```java
int[] prefix = new int[nums.length + 1];

for (int i = 0; i < nums.length; i++) {
    prefix[i + 1] = prefix[i] + nums[i];
}
```

Запрос:

```java
return prefix[right + 1] - prefix[left];
```

---

# 22. Шаблон Prefix Sum + HashMap

```java
int prefix = 0;
int count = 0;

Map<Integer, Integer> freq = new HashMap<>();

freq.put(0, 1);

for (int num : nums) {
    prefix += num;

    int key = prefix - k;

    count += freq.getOrDefault(key, 0);

    freq.put(
            prefix,
            freq.getOrDefault(prefix, 0) + 1
    );
}
```

Главная логика:

```text
prefix += nums[i]

ищем:
prefix - k

сколько раз это значение встречалось:
freq.get(prefix - k)

добавляем найденное количество:
count += freq.get(prefix - k)

сохраняем текущий prefix:
freq[prefix]++
```

---

# 23. Задачи для закрепления

`1480. Running Sum of 1d Array`

```text
базовый Prefix Sum
```

`303. Range Sum Query - Immutable`

```text
preprocessing O(n)
+
запрос диапазона O(1)
```

`560. Subarray Sum Equals K`

```text
Prefix Sum
+
HashMap
+
частоты предыдущих prefix sums
```

---

# 24. Итоговая шпаргалка

```text
Prefix Sum
→ заранее считаем накопленные значения
→ preprocessing O(n)

prefix[i]
→ сумма nums[0..i]

sum(left, right)
→ prefix[right] - prefix[left - 1]

prefix длины n + 1
→ sum = prefix[right + 1] - prefix[left]

много range sum queries
→ Prefix Sum

подмассив с суммой k
→ currentPrefix - previousPrefix = k

отсюда:
previousPrefix = currentPrefix - k

Prefix Sum + HashMap
→ HashMap хранит:
prefixSum → frequency

freq.put(0, 1)
→ виртуальная prefix sum до начала массива

count += freq.get(prefix - k)
→ учитываем все подходящие предыдущие позиции

после проверки:
freq[prefix]++

обычный range query:
O(1)

Prefix Sum + HashMap:
O(n) time
O(n) space
```

# 25. Главное

Главная идея Prefix Sum:

```text
не считать сумму одного и того же участка массива заново,
а один раз сохранить накопленную информацию
```

Для диапазонов это приводит к формуле:

```text
сумма большого префикса
-
сумма части до нужного диапазона
=
сумма нужного диапазона
```

А для поиска подмассивов с суммой `k`:

```text
currentPrefix - previousPrefix = k
```

поэтому:

```text
previousPrefix = currentPrefix - k
```

Если быстро находить предыдущие prefix sums через `HashMap`, задачу, которая при полном переборе подмассивов могла требовать `O(n²)`, во многих случаях можно решить за `O(n)`.