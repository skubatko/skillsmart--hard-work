# Задание 62

## Решение

Я не владею Python, но попробовал разобраться.

В данном случае использованы генераторы, которые применены для получения сумм последовательности num чисел Фибоначчи.

fibs_sum() задаёт генератор, который позволяет получить следующее число, сложив переданное через send значение с
предыдущим результатом.

get_fibs() собственно реализует генерацию следующей суммы последовательности чисел Фибоначчи.

Строка

```py
    gsum.send(None)
```

функции get_fibs() вызовет TypeError в строке функции fibs_sum()

```py
    fsum += yield fsum
```

что, в данном случае. равносильно

```py
    fsum = fsum + None
```

// However, a None object cannot be modified, referred to with Python methods, or used with operators. If you refer to a
None object in such cases, you’ll get a TypeError. Нашёл вот [здесь](https://learnpython.com/blog/null-in-python/).

yield gsum.send(b) отправляет очередное значение для сложения в генератор.

конструкцию

```py
    c = b
    b = a + b
    a = c
```

можно записать короче

```py
   a, b = b, a + b
```

конструкцию

```py
    for i in range(num):
```

я бы заменил на

```py
    while a < num:
```

что лучше отражает суть происходящего и не вводит дополнительную переменную i, которая не применяется в вычислениях.

Написать похожий код на Java не представляется возможным. На Kotlin получится примерно так:

```kotlin
fun fibSum(num: Int) = sequence {
    var fsum = 0
    var terms = Pair(0, 1)
    while (terms.first < num) {
        terms = Pair(terms.second, terms.first + terms.second)
        fsum += terms.first
        yield(fsum)
    }
}
```

Для анализа использованы материалы:

- [Python алгоритмы: Числа Фибоначчи](https://py-algorithm.blogspot.com/2011/04/blog-post_04.html)
- [Использование send(), throw() и close() в генераторах Python](https://docs-python.ru/tutorial/generatory-python/ispolzovanie-send-throw-close/)
- [Null in Python: A Complete Guide | LearnPython.com](https://learnpython.com/blog/null-in-python/)
- [Python yield — простым языком](https://web.archive.org/web/20100422224607/http://www.kigorw.com/articles/python-yield-generator)
