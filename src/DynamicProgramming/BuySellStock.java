package DynamicProgramming;

import java.util.Scanner;

public class BuySellStock {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int day_number = sc.nextInt();
        int[] price_array = new int[day_number];

        for (int i = 0; i < day_number; i++) {
            price_array[i] = sc.nextInt();
        }
        System.out.print(maxProfit(price_array));
        sc.close();
    }

    private static int maxProfit(int[] prices) {
        int size = prices.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        int min_idx = 0;
        int max_idx = 0;

        int diff = 0;

        for (int i = 0; i < size; i++) {
            int item = prices[i];
            // update min
            if (item < min) {
                min = item;
                min_idx = i;
            }
            // update max
            if (item > max || i > min_idx) {
                max = item;
                max_idx = i;
            }
            // update difference
            if (max_idx > min_idx) {
                diff = Math.max(max - min, diff);
            }
        }
        return diff;
    }
}
