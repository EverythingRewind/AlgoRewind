package hackerank;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class PlusMinus {

    // Complete the plusMinus function below.
    static void plusMinus(int[] arr) {

        long positiveCount = Arrays.stream(arr).filter(i -> i > 0).count();
        long negativeCount = Arrays.stream(arr).filter(i -> i < 0).count();
        long zeroCount = Arrays.stream(arr).filter(i -> i == 0).count();

        BigDecimal divisor = new BigDecimal((double)arr.length);

        System.out.println(new BigDecimal(positiveCount).divide(divisor, 6,RoundingMode.HALF_UP));
        System.out.println(new BigDecimal(negativeCount).divide(divisor, 6,RoundingMode.HALF_UP));
        System.out.println(new BigDecimal(zeroCount).divide(divisor, 6,RoundingMode.HALF_UP));

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        plusMinus(arr);

        scanner.close();
    }
}
