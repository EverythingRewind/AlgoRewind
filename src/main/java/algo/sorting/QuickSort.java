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

    public static void qsort(int[] a, int q, int r) {
        // base case
        if (r - q < 2) return;

        // partition to a[q..p-1] and a[p+1..r]
        // compute p
        int p = r;
        for (int i = q; i < r; i++) {
            if (a[i] > a[p]) {
                int tmp = a[i];
                a[i] = a[p];
                a[p] = tmp;
                p = i;
            }
        }

        for (int i = r - 1 ; i > 0; i--) {
            if (a[i] > a[p]) {
                int tmp = a[i];
                a[i] = a[p];
                a[p] = tmp;
                p = i;
            }
        }

        qsort(a, q, p -1);
        qsort(a, p+ 1, r);

    }

    public static void quicksort(int a[], int p, int r) {
        // base case
        if (r - p < 1) return;


        // partition
        int q = partition(a, p, r);

        quicksort(a, p, q - 1);
        quicksort(a, q + 1, r);
    }

    private static int partition(int[] a, int p, int r) {
        int x = a[r];

        // partition the array into two subarrays

        // the largest index of smaller element
        int i = p - 1;

        // 4 2 1 0 5 6
        for (int j = p; j < r; j ++) {
            if (a[j] <= x) {
                i++; // increase size for smaller element
                exchange(a, i, j);
            }
        }
        exchange(a, i + 1, r);
        return i + 1;
    }

    public static void exchange(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
        int[] a = {4, 2, 1, 0, 5, 6};
        quicksort(a, 0 , a.length - 1);
        System.out.println(Arrays.toString(a));
    }

}
