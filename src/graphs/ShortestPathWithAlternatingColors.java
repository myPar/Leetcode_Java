package graphs;

import java.util.LinkedList;
import java.util.Scanner;

public class ShortestPathWithAlternatingColors {
    private static class Edge {
        int end_vertex;
        int cur_path_size;  // current path size to end vertex
        boolean type;
        boolean is_marked;

        private Edge(int end_vertex, boolean type) {
            this.end_vertex = end_vertex;
            this.type = type;
            is_marked = false;
            cur_path_size = 0;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int node_number = sc.nextInt();
        int red_edges_number = sc.nextInt();
        int blue_edges_number = sc.nextInt();

        int[][] red_edges_array = new int[red_edges_number][2];
        int[][] blue_edges_array = new int[blue_edges_number][2];

        for (int i = 0; i < red_edges_number; i++) {
            int start_vertex = sc.nextInt();
            int end_vertex = sc.nextInt();

            red_edges_array[i][0] = start_vertex;
            red_edges_array[i][1] = end_vertex;
        }
        for (int i = 0; i < blue_edges_number; i++) {
            int start_vertex = sc.nextInt();
            int end_vertex = sc.nextInt();

            blue_edges_array[i][0] = start_vertex;
            blue_edges_array[i][1] = end_vertex;
        }

        int[] answer = shortestAlternatingPaths(node_number, red_edges_array, blue_edges_array);

        for (int value : answer) {
            System.out.print(value + " ");
        }
        sc.close();
    }

    private static int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
        // true - red edge; 0 - no edge; false - blue edge
        LinkedList<Edge>[] adjacency_list = new LinkedList[n];
        int[] answer = new int[n];

        // start initialisation of answer
        for (int i = 0; i < n; i++) {
            answer[i] = Integer.MAX_VALUE;
        }
        // start initialisation of adjacency_list
        for (int i = 0; i < n; i++) {
            adjacency_list[i] = new LinkedList<>();
        }
        // adjacency_list initialisation
        for (int[] blue_edge: blue_edges) {
            adjacency_list[blue_edge[0]].add(new Edge(blue_edge[1], false));
        }
        for (int[] red_edge: red_edges) {
            adjacency_list[red_edge[0]].add(new Edge(red_edge[1], true));
        }
        LinkedList<Edge> queue = new LinkedList<>();
        answer[0] = 0;
        find_min_path(0, 0, true, answer, adjacency_list, queue);

        for (int i = 0; i < n; i++) {
            if (answer[i] == Integer.MAX_VALUE) {
                answer[i] = -1;
            }
        }
        return answer;
    }
    private static void find_min_path(int start_vertex, int path_size, boolean parent_type, int[] answer,
                                      LinkedList<Edge>[] adjacency_list, LinkedList<Edge> queue) {
        for (Edge edge: adjacency_list[start_vertex]) {
            if (start_vertex == 0 && !edge.is_marked || !edge.is_marked && edge.type != parent_type) {
                edge.is_marked = true;
                queue.add(edge);
                answer[edge.end_vertex] = Math.min(answer[edge.end_vertex], path_size + 1);
                edge.cur_path_size = path_size + 1;
            }
        }
        if (queue.size() == 0) {
            return;
        }
        Edge parent_edge = queue.poll();
        find_min_path(parent_edge.end_vertex, parent_edge.cur_path_size, parent_edge.type,
                      answer, adjacency_list, queue);
    }
}
