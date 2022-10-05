Jump search in Java → Find a block that may contain the target value
---

## Description

Write a program that reads an ascending sorted array of integers and a single value. The program should find the first
suitable block for this value according to the values of block borders.

Your algorithm should use principles of the jump search.

As a block size use the optimal value. The last block can have the smaller size.

## Input data format

The first line contains the length of an input array, the second one consists of array elements, the last one has the
target value.

## Output data format

The program must output the left and the right indexes of the found block separated by space. If no any block could
contain the value, output the string "-1".
