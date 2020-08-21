package DynamicProgramming;

import java.util.Scanner;

public class HouseRobber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int items_number = sc.nextInt();
        int[] price_array = new int[items_number];

        for (int i = 0; i < items_number; i++) {
            price_array[i] = sc.nextInt();
        }
        System.out.println(rob(price_array));

        sc.close();
    }

    private static int rob(int[] nums) {
        int size = nums.length;

        if (size == 0) {
            return 0;
        }
        if (size == 1) {
            return nums[0];
        }
        if (size == 2) {
            return  Math.max(nums[0], nums[1]);
        }
        int[] max_sum_array = new int[size];
        int[] max_proceed_array = new int[size];
        // start initialisation of first and second items
        // for max_proceed_array:
        max_proceed_array[0] = nums[0];
        max_proceed_array[1] = nums[1];
        // for max_sum_array:
        max_sum_array[0] = nums[0];
        max_sum_array[1] = Math.max(nums[0], nums[1]);
        int max_proceed = max_sum_array[1];

        for (int i = 2; i < size; i++) {
            max_proceed_array[i] = max_sum_array[i - 2] + nums[i];
            max_sum_array[i] = Math.max(max_sum_array[i - 1], max_proceed_array[i]);
            max_proceed = Math.max(max_proceed, max_proceed_array[i]);
        }
        return max_proceed;
    }
}
