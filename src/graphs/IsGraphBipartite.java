package graphs;

import java.util.Scanner;

public class IsGraphBipartite {
    private static boolean is_bipartite = true;

    private static class State {
        boolean is_marked;
        boolean colour;

        State(boolean color, boolean is_marked) {
            this.colour = color;
            this.is_marked = is_marked;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int node_number = sc.nextInt();
        int[][] edge_array = new int[node_number][];

        sc.nextLine();

        for (int i = 0; i < node_number; i++) {
            String input_str = sc.nextLine();
            String[] str_arr = input_str.split(" ");

            if (input_str.length() > 0) {
                edge_array[i] = new int[str_arr.length];

                for (int j = 0; j < str_arr.length; j++) {
                    edge_array[i][j] = str_arr[j].charAt(0) - '0';
                }
            }
        }
        System.out.println(isBipartite(edge_array));

        sc.close();
    }

    private static boolean isBipartite(int[][] graph) {
        boolean result;
        int size = graph.length;
        State[] state_array = new State[size];

        for (int i = 0; i < size; i++) {
            state_array[i] = new State(false, false);
        }
        for (int i = 0; i < size; i++) {
            if (!is_bipartite) {
                break;
            }
            if (!state_array[i].is_marked) {
                state_array[i].is_marked = true;
                dfs(graph, i, state_array);
            }
        }

        result = is_bipartite;
        is_bipartite = true;

        return result;
    }

    private static void dfs(int[][] edge_array, int st_v, State[] st_arr) {
        if (edge_array[st_v] == null) {
            return;
        }
        boolean cur_colour = st_arr[st_v].colour;
        int neighbours_number = edge_array[st_v].length;

        for (int i = 0; i < neighbours_number; i++) {
            if (!is_bipartite) {
                return;
            }
            int neighbour = edge_array[st_v][i];

            if (!st_arr[neighbour].is_marked) {
                st_arr[neighbour].colour = !cur_colour;
                st_arr[neighbour].is_marked = true;

                dfs(edge_array, neighbour, st_arr);
            }
            else {
                if (st_arr[neighbour].colour == cur_colour) {
                    // graph can't be bipartite
                    is_bipartite = false;
                    return;
                }
            }
        }
    }
}
