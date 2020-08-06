package graphs;

import java.util.Scanner;

public class ValidateBinaryTreeNodes {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int node_number = sc.nextInt();

        int[] left_child_array = new int[node_number];
        int[] right_child_array = new int[node_number];

        for (int i = 0; i < node_number; i++) {
            left_child_array[i] = sc.nextInt();
            right_child_array[i] = sc.nextInt();
        }
        System.out.println(validateBinaryTreeNodes(node_number, left_child_array, right_child_array));

        sc.close();
    }

    private static boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        byte[] edges_count_array = new byte[n];
        int[] parent_array = new int[n];
        boolean root_found = false;

        // start initialisation of parent array
        for (int i = 0; i < n; i++) {
            parent_array[i] = -1;
        }

        // check one node case
        if (n == 1) {
            return true;
        }
        for (int i = 0; i < n; i++) {
            int left_child = leftChild[i];
            int right_child = rightChild[i];

            if (left_child > -1) {
                parent_array[left_child] = i;
                // check number of input edges for left child
                if (edges_count_array[left_child] > 0) {
                    return false;
                }
                edges_count_array[left_child]++;
            }
            if (right_child > -1) {
                parent_array[right_child] = i;
                // check number of input edges for right child
                if (edges_count_array[right_child] > 0) {
                    return false;
                }
                edges_count_array[right_child]++;
            }
        }

        // check root consistence
        for (int i = 0; i < n; i++) {
            if (edges_count_array[i] == 0) {
                // the root is already found so we have more then one tree
                if (root_found) {
                    return false;
                }
                root_found = true;
            }
        }
        // if root don't found we have loop in the graph
        return root_found && !check_loop(parent_array);
    }

    private static boolean check_loop(int[] parent_array) {
        int size = parent_array.length;
        boolean[] is_marked = new boolean[size];
        int mark_count = 0;

        int cur_idx = 0;
        int start_idx = -1;
        // state variable: 0 - is moving on parent links; 1 - move from marked node
        int state = 0;

        while (mark_count < size) {
            switch (state) {
                // states descriptions
                case 0:
                    // start state:
                    start_idx = cur_idx;
                    state = 1;
                    break;
                case 1:
                    // traversal state:
                    is_marked[cur_idx] = true;          // mark current node
                    mark_count++;                       //

                    int next = parent_array[cur_idx];

                    // check parent consistence
                    if (next == -1) {
                        // no parent so move to state 2
                        state = 2;
                        cur_idx = 0;
                    }
                    else {
                        // check loop
                        if (next == start_idx) {
                            return true;
                        }
                        // check if parent is already marked
                        if (is_marked[next]) {
                            // move to state 2
                            state = 2;
                            cur_idx = 0;
                        }
                        // move to parent
                        else {
                            cur_idx = next;
                        }
                    }
                    break;
                case 2:
                    // move to next unmarked node state:
                    if (is_marked[cur_idx]) {
                        cur_idx++;
                    }
                    else {
                        // move to state 0
                        state = 0;
                    }
                    break;
            }
        }
        return false;
    }
}
