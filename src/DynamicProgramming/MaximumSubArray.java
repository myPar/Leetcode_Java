package DynamicProgramming;

import java.util.Scanner;

public class MaximumSubArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int array_size = sc.nextInt();
        int[] array = new int[array_size];

        for (int i = 0; i < array_size; i++) {
            array[i] = sc.nextInt();
        }
        System.out.println(maxSubArray(array));

        sc.close();
    }

    private static int maxSubArray(int[] nums) {
        int size = nums.length;
        // sum[i] is equal to maximum element's sum of
        // sub_array with i'th first element index
        int[] sum = new int[size];
        int max_sum = Integer.MIN_VALUE;

        int st_idx = 0;

        for (int i = 0; i < size; i++) {
            if (i == st_idx) {
                st_idx += get_prefix_dist(nums, st_idx, sum);
            }
            else {
                sum[i] = sum[i - 1] - nums[i - 1];
            }
            if (sum[i] > max_sum) {
                max_sum = sum[i];
            }
        }
        return  max_sum;
    }

    private static int get_prefix_dist(int[] arr, int idx, int[] sum) {
        int i = idx;
        int size = arr.length;

        int max_sum = Integer.MIN_VALUE;
        int cur_sum = 0;
        int dist = 0;

        for (; i < size; i++) {
            cur_sum += arr[i];

            if (cur_sum > max_sum) {
                max_sum = cur_sum;
                dist = i + 1 - idx;
            }
        }
        sum[idx] = max_sum;

        return dist;
    }
}
