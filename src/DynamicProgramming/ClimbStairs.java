package DynamicProgramming;

import java.util.Scanner;

public class ClimbStairs {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int height = sc.nextInt();

        System.out.println(climbStairs(height));
        sc.close();
    }

    private static int climbStairs(int n) {
        int[] step_array = new int[n];

        step_array[0] = 1;

        if (n > 1) {
            step_array[1] = 2;
        }
        for (int i = 2; i < n; i++) {
            step_array[i] = step_array[i - 1] + step_array[i - 2];
        }

        return step_array[n - 1];
    }

}
