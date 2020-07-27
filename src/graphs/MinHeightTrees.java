package graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Scanner;

public class MinHeightTrees {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int vertices_number = sc.nextInt();
        int edges_number = sc.nextInt();
        int[][] edges_array = new int[edges_number][2];

        // node list initialisation
        for (int i = 0; i < edges_number; i++) {
            int start_vertex = sc.nextInt();
            int end_vertex = sc.nextInt();

            edges_array[i][0] = start_vertex;
            edges_array[i][1] = end_vertex;
        }
        List<Integer> result = findMinHeightTrees(vertices_number, edges_array);

        for (Integer item : result) {
            System.out.print(item + " ");
        }
        sc.close();
    }

    private static List<Integer> findMinHeightTrees(int n, int[][] edges) {
        ArrayList<Integer>[] adjacency_list = new ArrayList[n];
        ArrayList<HashMap<Integer, Integer>> cache = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            HashMap<Integer, Integer> item = new HashMap<>();
            cache.add(item);
        }
        List<Integer> answer = new ArrayList<>();
        int[] height_array = new int[n];

        for (int i = 0; i < n; i++) {
            adjacency_list[i] = new ArrayList<>();
        }
        for (int[] edge: edges) {
            adjacency_list[edge[0]].add(edge[1]);
            adjacency_list[edge[1]].add(edge[0]);
        }
        int min_height = Integer.MAX_VALUE;

        // get heights of trees with roots 0..n-1
        for (int i = 0; i < n; i++) {
            int cur_height = height_array[i] = dfs(adjacency_list, i, i, i, cache);

            if (cur_height < min_height) {
                min_height = cur_height;
            }
        }
        // build answer
        for (int i = 0; i < n; i++) {
            if (height_array[i] == min_height) {
                answer.add(i);
            }
        }
        return answer;
    }

    private static int dfs(List<Integer>[] adjacency_list, int vertex,
                           int root, int parent, ArrayList<HashMap<Integer, Integer>> cache) {
        // tree leaf condition
        if (vertex != root && adjacency_list[vertex].size() == 1) {
            return 0;
        }
        int max_height = -1;

        for (int neighbour: adjacency_list[vertex]) {
            if (neighbour != parent) {
                int cur_height;

                // is height already cached
                if (cache.get(parent).containsKey(neighbour)) {
                    // use the cached early result
                    cur_height = cache.get(parent).get(neighbour);
                }
                else {
                    // cache the result
                    cur_height = dfs(adjacency_list, neighbour, root, vertex, cache);
                    cache.get(parent).put(neighbour, cur_height);
                }

                // update max height
                if (cur_height > max_height) {
                    max_height = cur_height;
                }
            }
        }
        return max_height + 1;
    }
}
