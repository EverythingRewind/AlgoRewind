package hackerank;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;

public class CountingValleys {

    // Complete the countingValleys function below.
    static int countingValleys(int n, String s) {

        // U D D D U D U U
        // 1 -1 -1 -1 1 1 1

        // D D U U D D U D U U U D

        char[] steps = s.toCharArray();
        int[] levels = new int[steps.length];
        for (int i = 0; i < steps.length; i++) {
            if (steps[i] == 'U') {
                levels[i] = 1;
            }
            if (steps[i] == 'D') {
                levels[i] = -1;
            }
        }

        int count = 0;

        int sum = 0;

        boolean isDownToValley = false;

        for (int i = 0; i < levels.length; i++) {
            int level = levels[i];

            if (level < 0 && sum <= 0) {
                isDownToValley = true;
            }

            sum += level;

            if (sum == 0 && isDownToValley) {
                count++;
                isDownToValley = false;
            }
        }

        return count;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String s = scanner.nextLine();

        int result = countingValleys(n, s);

        System.out.println(result);

        scanner.close();
    }

}
