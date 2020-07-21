package graphs;

import java.util.ArrayList;
import java.util.Scanner;

public class FlowerPlanting {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int vertices_number = sc.nextInt();
        int edge_number = sc.nextInt();
        int[][] edge_list = new int[edge_number][2];
        int[] answer;

        // reading of edges array
        for (int i = 0; i < edge_number; i++) {
            int start_vertex = sc.nextInt();
            int end_vertex = sc.nextInt();

            edge_list[i][0] = start_vertex;
            edge_list[i][1] = end_vertex;
        }
        answer = gardenNoAdj(vertices_number, edge_list);

        for (int i = 0; i < vertices_number; i++) {
            System.out.print(answer[i] + " ");
        }
        sc.close();
    }

    private static int[] gardenNoAdj(int N, int[][] paths) {
        ArrayList<Integer>[] adjacency_list = new ArrayList[N];
        int[] answer = new int[N];

        // start answer initializing
        for (int i = 0; i < N; i++) {
            answer[i] = 0;
        }
        for (int i = 0; i < N; i++) {
            adjacency_list[i] = new ArrayList<>();
        }
        for (int[] edge: paths) {
            adjacency_list[edge[0] - 1].add(edge[1] - 1);
            adjacency_list[edge[1] - 1].add(edge[0] - 1);
        }
        for (int i = 0; i < N; i++) {
            if (answer[i] == 0) {
                dfs(adjacency_list, answer, i);
            }
        }
        return answer;
    }

    private static void dfs(ArrayList<Integer>[] adjacency_list, int[] answer, int vertex) {
        int list_size = adjacency_list[vertex].size();
        boolean[] type_array = {true, true, true, true};

        // loop for all adjacent vertices to check types
        for (int i = 0; i < list_size; i++) {
            int adjacent_vertex = adjacency_list[vertex].get(i);

            // if vertex is marked
            if (answer[adjacent_vertex] != 0) {
                type_array[answer[adjacent_vertex] - 1] = false;
            }
        }
        for (int i = 0; i < 4; i++) {
            if (type_array[i]) {
                answer[vertex] = i + 1;
                break;
            }
        }
        // recursively call dfs for unmarked adjacent vertices
        for (int i = 0; i < list_size; i++) {
            int adjacent_vertex = adjacency_list[vertex].get(i);

            // if vertex is marked
            if (answer[adjacent_vertex] == 0) {
                dfs(adjacency_list, answer, adjacent_vertex);
            }
        }
    }
}
