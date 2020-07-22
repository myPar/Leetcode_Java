package graphs;

import java.util.Scanner;

public class TheTownJudge {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int vertices_number = sc.nextInt();
        int edges_number = sc.nextInt();
        int[][] edges_array = new int[edges_number][2];

        // read edges
        for (int i = 0; i < edges_number; i++) {
            edges_array[i][0] = sc.nextInt();
            edges_array[i][1] = sc.nextInt();
        }
        System.out.println(findJudge(vertices_number, edges_array));
        sc.close();
    }

    private static int findJudge(int N, int[][] trust) {
        int[] input_edges_array = new int[N];
        boolean[] output_edges_array = new boolean[N];

        for (int i = 0 ; i < N; i++) {
            input_edges_array[i] = 0;
        }

        for (int[] edge: trust) {
            int start_vertex = edge[0];
            int end_vertex = edge[1];

            input_edges_array[end_vertex - 1]++;
            output_edges_array[start_vertex - 1] = true;
        }
        for (int i = 0; i < N; i++) {
            // if all vertices have edge, which comes in to the vertex
            // and this vertex has no output edges - this is the answer
            if (input_edges_array[i] == N - 1 && !output_edges_array[i]) {
                return i + 1;
            }
        }

        return -1;
    }
}
