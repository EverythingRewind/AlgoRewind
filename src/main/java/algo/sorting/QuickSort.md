# Quick sort
1. Input: an unsorted array
2. Output: a sorted array

# Steps

The base case:  an array of 0 or 1 element

1. Pick a pivot.
2. Partition the array into two sub-arrays: elements less then the pivot and elements greater than the pivot.
3. Call quick sort recursively on two sub-arrays.

https://www.geeksforgeeks.org/quick-sort/

CLRS:

Base case: array of 1 or 1 is sorted already
Input: A[p..r]

Divide:
+ Partition/arrange the array A[p..r] into two sub-arrays A[p..q-1] and A[q+1..r].
    ++ Each element of A[p..q-1] is less or equal to A[q]
    ++ Each element of A[q+1.. r] is greater or equal to A[q]
+ Compute the index of p as part of the partitioning procedure

Conquer:
+ Sort the tow sub-arrays A[p..q-1] and A[p+1..r] by recursive calls to quicksort

