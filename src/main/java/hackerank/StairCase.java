package hackerank;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class StairCase {

    // Complete the staircase function below.
    static void staircase(int n) {


        for (int i = 0; i < n; i++) {
            StringBuilder line = new StringBuilder();
            line.append(repeat(" ", n - (i+1)));
            line.append(repeat("#", i+1));
            System.out.println(line);
        }

    }

    static String repeat(String s, int times) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < times; i++) {
            builder.append(s);
        }

        return builder.toString();

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        staircase(n);

        scanner.close();
    }
}

