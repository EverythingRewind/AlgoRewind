package hackerank;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SockMerchant {


    // Complete the sockMerchant function below.
    static int sockMerchant(int n, int[] ar) {

        Map<Integer, Integer> sockByColor = new HashMap<>();

        for (int i = 0; i < ar.length; i++) {
            Integer color = ar[i];
            Integer count = sockByColor.get(color);
            if (count == null) {
                count = 0;
            }
            count ++;
            sockByColor.put(color, count);
        }

        int totalOfPairs = 0;

        for (Map.Entry<Integer, Integer> entry : sockByColor.entrySet()) {
            totalOfPairs += entry.getValue() / 2;
        }

        return totalOfPairs;
    }

    static int sockMerchant1(int n, int[] ar) {

       int[] sockByColor = new int[101];

       for (int i = 0; i < n; i++) {
           int color = ar[i];
           int count = sockByColor[color];
           count++;
           sockByColor[color] = count;
       }

       int total = 0;

       for (int i = 0; i < 101; i++) {
            total += sockByColor[i] / 2;
       }

       return total;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        int n = scanner.nextInt();

        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] ar = new int[n];

        String[] arItems = scanner.nextLine().split(" ");

        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arItem = Integer.parseInt(arItems[i]);
            ar[i] = arItem;
        }

        int result = sockMerchant1(n, ar);

        System.out.println(result);


        scanner.close();
    }

}
