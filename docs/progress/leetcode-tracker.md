# LeetCode Progress Tracker

| № | Задача | Паттерн | Уровень | Дата | Результат | Самостоятельность | Время | Память | Повторить  |
|---:|---|---|---|---|---|---|---|---|------------|
| 217 | Contains Duplicate | HashSet | Easy | 27.07.2026 | Accepted | Самостоятельно | O(n) | O(n) | 30.07.2026 |
| 1 | Two Sum | HashMap | Easy | 27.07.2026 | Accepted | С небольшой подсказкой | O(n) | O(n) | 31.07.2026 |
| 242 | Valid Anagram | Frequency Counting | Easy | 28.07.2026 | Accepted | С небольшой подсказкой | O(n) | O(1) | 01.08.2026 |
| 49 | Group Anagrams | HashMap Grouping | Medium | 28.07.2026 | Accepted | С подсказкой по выбору ключа | O(n * k log k) | O(n * k) | 02.08.2026 |
| 125 | Valid Palindrome | Two Pointers | Easy | 30.07.2026 | Accepted | Самостоятельно, оптимизация после разбора | O(n) | O(1) | 04.08.2026 |
| 167 | Two Sum II | Two Pointers | Medium | 03.08.2026 | Accepted | Самостоятельно | O(n) | O(1) | 07.08.2026 |
| 15 | 3Sum | Sorting, Two Pointers | Medium | 04.08.2026 | Accepted | С подсказками по дубликатам и границам | O(n²) | O(1) | 08.08.2026 |
| 643 | Maximum Average Subarray I | Sliding Window | Easy | 05.08.2026 | Accepted | Самостоятельно после изучения паттерна | O(n) | O(1) | 09.08.2026 |
| 3 | Longest Substring Without Repeating Characters | Sliding Window, HashSet | Medium | 05.08.2026 | Accepted | С подсказкой по удалению левого символа и сохранению максимума | O(n) | O(min(n, alphabet)) | 09.08.2026 |
| 1456 | Maximum Number of Vowels in a Substring of Given Length | Sliding Window | Medium | 10.08.2026 | Accepted | Самостоятельно, исправлена проверка indexOf: >= 0 | O(n) | O(1) | |
| 1343 | Number of Sub-arrays of Size K and Average Greater than or Equal to Threshold | Sliding Window | Medium | 10.08.2026 | Accepted | Самостоятельно, исправлено условие == на >= | O(n) | O(1) | |
| 209 | Minimum Size Subarray Sum | Sliding Window | Medium | 12.08.2026 | Accepted | С подсказками по порядку расширения/сжатия окна и обработке первого элемента | O(n) | O(1) | |
| 1004 | Max Consecutive Ones III | Sliding Window | Medium | 12.08.2026 | Accepted | Самостоятельно, исправлен порядок сжатия окна и обновления максимума | O(n) | O(1) | |
| 904 | Fruit Into Baskets | Sliding Window, HashMap | Medium | 13.08.2026 | Accepted | С небольшой подсказкой | O(n) | O(1) | |
| 424 | Longest Repeating Character Replacement | Sliding Window, Frequency Counting | Medium | 13.08.2026 | Accepted | С подсказками по условию валидности окна и maxFreq | O(n) | O(1) | |
| 567 | Permutation in String | Sliding Window, Frequency Counting | Medium | 13.08.2026 | Accepted | С подсказкой по фиксированному окну и сравнению частот | O(n) | O(1) | |
| 438 | Find All Anagrams in a String | Sliding Window, Frequency Counting | Medium | 13.08.2026 | Accepted | Самостоятельно | O(n) | O(1) | |
| 1480 | Running Sum of 1d Array | Prefix Sum | Easy | 15.08.2026 | Accepted | Самостоятельно | O(n) | O(n) | |
| 303 | Range Sum Query - Immutable | Prefix Sum | Easy | 15.08.2026 | Accepted | Самостоятельно | O(n) preprocessing, O(1) query | O(n) | |
| 560 | Subarray Sum Equals K | Prefix Sum, HashMap | Medium | 15.08.2026 | Accepted | С подсказками по HashMap и частотам prefix sum | O(n) | O(n) | |
| 724 | Find Pivot Index | Prefix Sum | Easy | 17.08.2026 | Accepted | С небольшой подсказкой по формуле rightSum | O(n) | O(n) | |
| 525 | Contiguous Array | Prefix Sum, HashMap | Medium | 17.08.2026 | Accepted | С подсказками по идее balance | O(n) | O(n) | |
| 20 | Valid Parentheses | Stack | Easy | 18.08.2026 | Accepted | Самостоятельно, исправлен выбор метода Deque | O(n) | O(n) | |
| 155 | Min Stack | Stack | Medium | 20.08.2026 | Accepted | Два стека: значения и история минимумов | O(1) | O(n) | |

## Обозначения самостоятельности

- **Самостоятельно** — решение найдено и реализовано без подсказок.
- **С небольшой подсказкой** — потребовалось указание на структуру данных или паттерн.
- **С подсказкой по выбору ключа** — основная идея была понятна, но потребовалась помощь с ключом группировки.
- **Самостоятельно, оптимизация после разбора** — первое корректное решение найдено самостоятельно, оптимальный вариант реализован после разбора.
- **После разбора** — решение написано после изучения готового подхода.
- **Повторно самостоятельно** — задача успешно решена без подсказок при повторении.
