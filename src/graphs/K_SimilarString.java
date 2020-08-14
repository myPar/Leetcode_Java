package graphs;

import java.util.*;

public class K_SimilarString {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String template1 = sc.nextLine();
        String template2 = sc.nextLine();

        System.out.println(kSimilarity(template1, template2));
        sc.close();
    }

    private static int kSimilarity(String A, String B) {
        HashMap<String, Integer> permutations_table = new HashMap<>();
        LinkedList<String> queue = new LinkedList<>();

        queue.add(A);
        permutations_table.put(A, 0);

        while (!queue.isEmpty()) {
            String template = queue.pop();
            add_permutations(template, B, permutations_table, queue);
        }

        return permutations_table.get(B);
    }

    private static void add_permutations(String cur_str, String temp, HashMap<String, Integer> map, LinkedList<String> queue) {
        int len = temp.length();
        int i = 0;

        for (; i < len; i++) {
            if (cur_str.charAt(i) != temp.charAt(i)) {
                break;
            }
        }
        char[] perm = cur_str.toCharArray();

        for (int j = i; j < len; j++) {
            // swap j'th character in current string if it is
            // equal to i'th character in the template string
            if (perm[j] == temp.charAt(i)) {
                swap(perm, i, j);
                String perm_str = new String(perm);

                if (!map.containsKey(perm_str)) {
                    map.put(perm_str, map.get(cur_str) + 1);
                    queue.add(perm_str);
                }
                // returns to current string to make new permutations
                swap(perm, i, j);
            }
        }
    }

    private static void swap(char[] ch_array, int idx1, int idx2) {
        char ch = ch_array[idx1];

        ch_array[idx1] = ch_array[idx2];
        ch_array[idx2] = ch;
    }
}
