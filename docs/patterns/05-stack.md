# Stack Pattern

## Что такое стек

Стек — структура данных, работающая по принципу:

```text
LIFO
Last In — First Out
```

Последний добавленный элемент извлекается первым.

Пример:

```text
push(10)
push(20)
push(30)

stack:
30 ← верх
20
10

pop() → 30
pop() → 20
pop() → 10
```

Стек удобен в задачах, где нужно помнить последний незавершённый элемент, последнее состояние или быстро возвращаться назад.

---

# Как реализовать стек в Java

Для нового Java-кода обычно используют:

```java
Deque<E>
```

с реализацией:

```java
ArrayDeque<E>
```

Пример:

```java
Deque<Integer> stack = new ArrayDeque<>();

stack.push(10);
stack.push(20);

int top = stack.peek();
int removed = stack.pop();
```

Результат:

```text
top = 20
removed = 20
```

Класс:

```java
java.util.Stack
```

существует, но считается legacy API. Для новых решений обычно предпочтительнее `Deque`.

---

# Основные операции

```java
stack.push(value);
```

Добавить элемент на вершину.

```java
stack.pop();
```

Удалить и вернуть верхний элемент.

```java
stack.peek();
```

Посмотреть верхний элемент без удаления.

```java
stack.isEmpty();
```

Проверить, пуст ли стек.

Для `ArrayDeque` эти операции работают за:

```text
O(1)
```

---

# Когда стоит думать о Stack Pattern

Типичные признаки задачи:

```text
нужно работать с последним добавленным элементом
```

```text
нужно найти последнее незакрытое состояние
```

```text
есть вложенные конструкции
```

```text
нужно откатываться назад
```

```text
нужно помнить предыдущие минимумы или максимумы
```

```text
нужно обработать скобки
```

```text
нужно найти следующий больший или меньший элемент
```

```text
нужно вычислять выражения
```

Частые категории задач:

- parentheses;
- expression evaluation;
- undo / rollback;
- parsing;
- DFS в итеративной форме;
- monotonic stack;
- next greater element;
- next smaller element;
- previous greater / smaller element;
- хранение истории состояний.

---

# Пример 1. Valid Parentheses

Задача:

```text
()
[]{}
([{}])
```

Нужно определить, правильно ли закрываются скобки.

Основная идея:

```text
открывающая скобка
→ кладём в стек

закрывающая скобка
→ сравниваем с верхушкой стека
```

Пример:

```text
([{}])
```

Последовательность:

```text
(
stack: [(]

[
stack: [(, []

{
stack: [(, [, {]

}
верхушка {
совпадает
pop()

]
верхушка [
совпадает
pop()

)
верхушка (
совпадает
pop()

stack пуст
→ строка корректна
```

Пример Java:

```java
class Solution {

    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(' || c == '[' || c == '{') {
                stack.addLast(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }

                char last = stack.removeLast();

                if (c == ')' && last != '(') {
                    return false;
                }

                if (c == ']' && last != '[') {
                    return false;
                }

                if (c == '}' && last != '{') {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}
```

Сложность:

```text
Time  → O(n)
Space → O(n)
```

---

# Важный нюанс Deque

Если стек строится через:

```java
addLast()
```

то снимать элемент нужно через:

```java
removeLast()
```

Пример:

```java
Deque<Integer> stack = new ArrayDeque<>();

stack.addLast(10);
stack.addLast(20);
stack.addLast(30);

stack.removeLast(); // 30
```

Если смешать:

```java
addLast()
pop()
```

можно получить неправильное поведение, потому что `pop()` работает с началом `Deque`.

Поэтому лучше придерживаться одного стиля.

Стиль 1:

```java
push()
pop()
peek()
```

Стиль 2:

```java
addLast()
removeLast()
peekLast()
```

Оба корректны, если использовать их последовательно.

---

# Пример 2. Min Stack

Задача:

реализовать стек, который поддерживает:

```text
push()
pop()
top()
getMin()
```

причём все операции должны работать за:

```text
O(1)
```

Проблема:

если искать минимум обычным проходом по стеку:

```text
getMin() → O(n)
```

Это не подходит.

---

# Идея второго стека

Используем:

```text
stack
→ все значения

minStack
→ история минимумов
```

Пример:

```text
push(5)
push(3)
push(4)
push(2)
```

Состояние:

```text
stack:
2
4
3
5
```

```text
minStack:
2
3
5
```

`4` не попадает в `minStack`, потому что минимум не изменился.

---

# Почему нужно хранить дубликаты минимума

Пример:

```text
push(5)
push(3)
push(3)
```

Если хранить только уникальные минимумы:

```text
minStack:
3
5
```

после удаления верхней `3` стек всё ещё содержит ещё одну `3`.

Поэтому нужно хранить:

```text
minStack:
3
3
5
```

Условие:

```java
value <= minStack.peek()
```

а не:

```java
value < minStack.peek()
```

---

# Реализация Min Stack

```java
class MinStack {

    private final Deque<Integer> stack = new ArrayDeque<>();
    private final Deque<Integer> minStack = new ArrayDeque<>();

    public MinStack() {
    }

    public void push(int value) {
        stack.push(value);

        if (minStack.isEmpty() || value <= minStack.peek()) {
            minStack.push(value);
        }
    }

    public void pop() {
        int removed = stack.pop();

        if (removed == minStack.peek()) {
            minStack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
```

Сложность:

```text
push()   → O(1)
pop()    → O(1)
top()    → O(1)
getMin() → O(1)

Space → O(n)
```

---

# Почему Min Stack работает

`minStack` хранит минимальные значения, которые актуальны для текущих уровней основного стека.

Если текущий минимум удаляется:

```java
if (removed == minStack.peek()) {
    minStack.pop();
}
```

следующее значение в `minStack` автоматически становится предыдущим минимумом.

Пример:

```text
stack:
1
2
4
3
5

minStack:
1
2
3
5
```

Удаляем:

```text
1
```

Теперь:

```text
minStack top = 2
```

Минимум восстановился за:

```text
O(1)
```

---

# Stack как история состояний

Во многих задачах стек можно рассматривать не просто как набор значений, а как:

```text
историю состояний
```

Пример:

```text
state 1
state 2
state 3
state 4
```

Если нужно откатиться:

```text
pop()
```

получаем предыдущее состояние.

Это встречается в:

- undo;
- DFS;
- обработке директорий;
- парсерах;
- вычислении выражений;
- браузерной истории;
- задачах на вложенность.

---

# Monotonic Stack

Отдельный важный подвид Stack Pattern — монотонный стек.

В нём элементы поддерживаются в определённом порядке:

```text
возрастающем
```

или:

```text
убывающем
```

Например:

```text
monotonic increasing stack
```

может выглядеть так:

```text
1
3
5
8
```

При добавлении нового элемента часть значений может удаляться, чтобы сохранить нужный порядок.

---

# Где используется Monotonic Stack

Типичные задачи:

```text
Next Greater Element
Next Smaller Element
Previous Greater Element
Previous Smaller Element
Daily Temperatures
Largest Rectangle in Histogram
Trapping Rain Water
```

Главная идея:

вместо того чтобы для каждого элемента искать следующий подходящий элемент отдельным проходом:

```text
O(n²)
```

используется стек и задача часто решается за:

```text
O(n)
```

---

# Почему monotonic stack часто O(n)

На первый взгляд внутри цикла может находиться `while`:

```java
for (...) {
    while (!stack.isEmpty() && condition) {
        stack.pop();
    }

    stack.push(...);
}
```

Можно ошибочно решить, что это:

```text
O(n²)
```

Но каждый элемент:

```text
push → максимум один раз
pop  → максимум один раз
```

Поэтому суммарно:

```text
O(n)
```

Это типичный пример амортизированного анализа.

---

# Хранить значения или индексы

В стек можно класть:

```text
значения
```

или:

```text
индексы
```

Очень часто удобнее хранить индексы.

Например:

```java
Deque<Integer> stack = new ArrayDeque<>();
```

где `Integer` — индекс элемента массива.

Тогда можно получить:

```java
nums[index]
```

и одновременно знать расстояние между позициями:

```java
i - index
```

Это особенно важно в задачах вроде:

```text
Daily Temperatures
```

---

# Частые ошибки

## Использовать стек, когда нужен полный порядок

Стек предоставляет быстрый доступ только к вершине.

Если нужен произвольный элемент:

```text
stack
```

может быть неподходящей структурой.

---

## Путать FIFO и LIFO

```text
Queue
→ FIFO

Stack
→ LIFO
```

---

## Смешивать разные стороны Deque

Например:

```java
addLast()
pop()
```

может привести к логической ошибке.

Лучше использовать последовательный набор методов:

```java
push()
pop()
peek()
```

или:

```java
addLast()
removeLast()
peekLast()
```

---

## Забывать проверить isEmpty()

Перед:

```java
pop()
peek()
```

в задачах часто необходимо проверить:

```java
stack.isEmpty()
```

---

## Забывать финальную проверку

В задачах на скобки недостаточно только успешно обработать все закрывающие символы.

Например:

```text
(((
```

ни разу не вызовет конфликт, но строка всё равно неправильная.

Поэтому в конце:

```java
return stack.isEmpty();
```

---

## Не учитывать дубликаты минимумов

Для Min Stack условие:

```java
value < minStack.peek()
```

ошибочно.

Нужно:

```java
value <= minStack.peek()
```

---

## Считать вложенный while автоматическим O(n²)

Для monotonic stack нужно считать, сколько раз каждый элемент реально добавляется и удаляется.

Часто каждый элемент проходит через стек максимум один раз, поэтому итог:

```text
O(n)
```

---

# Как распознать Stack Pattern на собеседовании

Можно задать себе вопросы:

```text
Нужно ли мне помнить последний незавершённый элемент?
```

```text
Нужно ли откатываться к предыдущему состоянию?
```

```text
Есть ли вложенность?
```

```text
Есть ли соответствующие пары:
открытие → закрытие?
```

```text
Нужно ли быстро получать предыдущий минимум или максимум?
```

```text
Нужно ли искать следующий больший или меньший элемент?
```

Если ответ на один из этих вопросов положительный, стоит рассмотреть стек.

---

# Шаблон обычного стека

```java
Deque<Integer> stack = new ArrayDeque<>();

for (int value : values) {

    // обработка

    stack.push(value);
}
```

---

# Шаблон обработки пар

```java
Deque<Character> stack = new ArrayDeque<>();

for (char c : chars) {

    if (isOpening(c)) {
        stack.push(c);
        continue;
    }

    if (stack.isEmpty()) {
        return false;
    }

    char last = stack.pop();

    if (!matches(last, c)) {
        return false;
    }
}

return stack.isEmpty();
```

---

# Общий шаблон monotonic stack

```java
Deque<Integer> stack = new ArrayDeque<>();

for (int i = 0; i < nums.length; i++) {

    while (!stack.isEmpty()
            && nums[stack.peek()] < nums[i]) {

        int index = stack.pop();

        // nums[i] является следующим большим
        // элементом для nums[index]
    }

    stack.push(i);
}
```

Этот шаблон не нужно заучивать буквально. Важно понимать идею:

```text
стек хранит элементы,
для которых ответ ещё не найден
```

Когда приходит новый элемент, он может дать ответ нескольким элементам с вершины стека.

---

# Пройденные задачи

## LeetCode 20 — Valid Parentheses

Паттерн:

```text
Stack
```

Ключевая идея:

```text
хранить незакрытые открывающие скобки
```

Сложность:

```text
Time  → O(n)
Space → O(n)
```

---

## LeetCode 155 — Min Stack

Паттерн:

```text
Stack
```

Ключевая идея:

```text
основной стек
+
стек истории минимумов
```

Сложность:

```text
push   → O(1)
pop    → O(1)
top    → O(1)
getMin → O(1)

Space  → O(n)
```

---

# Краткая памятка

```text
Stack
→ LIFO
→ последний вошёл — первый вышел
```

```text
Java:
Deque<E>
ArrayDeque<E>
```

```text
push()
pop()
peek()
isEmpty()
```

```text
Типичные задачи:
скобки
вложенность
история
undo
парсинг
DFS
min/max history
next greater/smaller
```

```text
Valid Parentheses
→ незакрытые элементы храним в stack
```

```text
Min Stack
→ stack + minStack
→ дубликаты минимума тоже храним
```

```text
Monotonic Stack
→ стек поддерживает монотонность
→ каждый элемент обычно push/pop максимум один раз
→ часто O(n)
```

---

# Вопросы для самопроверки

1. Что означает принцип LIFO?
2. Почему для стека в современном Java чаще используют `Deque`, а не `Stack`?
3. Чем `push()`, `pop()` и `peek()` отличаются друг от друга?
4. Какая сложность основных операций `ArrayDeque`?
5. Какие признаки задачи указывают на Stack Pattern?
6. Почему Valid Parentheses удобно решать через стек?
7. Почему в конце Valid Parentheses нужно проверить `stack.isEmpty()`?
8. Почему нельзя бездумно смешивать `addLast()` и `pop()`?
9. Почему обычный поиск минимума в стеке не подходит для Min Stack?
10. Для чего нужен второй стек в Min Stack?
11. Почему нужно использовать `<=`, а не `<`, при добавлении в `minStack`?
12. Когда элемент удаляется из `minStack`?
13. Какая сложность `getMin()` в правильном решении Min Stack?
14. Что значит рассматривать стек как историю состояний?
15. Что такое monotonic stack?
16. В каких задачах используется monotonic stack?
17. Почему алгоритм с monotonic stack часто работает за `O(n)`, несмотря на вложенный `while`?
18. Когда в стеке удобнее хранить индексы, а не значения?
19. Чем Stack отличается от Queue?
20. Какие две задачи на Stack Pattern уже пройдены?
