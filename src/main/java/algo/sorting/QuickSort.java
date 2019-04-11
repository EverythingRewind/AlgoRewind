package algo.sorting;

import java.util.Arrays;

public class QuickSort {

    public static int[] qsort(int[] a, int n) {

        if (n < 2) return a;

        // pick a pivot
        int p = a[0];

        // partition into 2 arrays

        int[] less = new int[n];
        int li = 0;
        int[] greater = new int[n];
        int gi = 0;

        for (int i = 1; i < n; i++) {
            if (a[i] < p) less[li++] = a[i];
            else greater[gi++] = a[i];
        }
        qsort(less, li);
        qsort(greater, gi);
        for (int i = 0; i < n; i++) {
            if (i < li) {
                a[i] = less[i];
            }

            if (i == li) {
                a[i] = p;
            }

            if (i > li) {
                a[i] = greater[i - (li + 1)];
            }
        }

        return a;

    }

    public static void main(String[] args) {
        int[] a = {2, 1, 0, 4, 9, 100, -1, -6, -9, 100, 5,3,4,3,1};
        qsort(a, a.length);
        System.out.println(Arrays.toString(a));
    }

}
