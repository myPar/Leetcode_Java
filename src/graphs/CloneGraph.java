package graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CloneGraph {
    static class Node {
        public int val;
        public List<Node> neighbors;

        // default constructor
        public Node(int value) {
            val = value;
            neighbors = new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int vertices_number = sc.nextInt();
        int edges_number = sc.nextInt();
        Node[] node_array = new Node[vertices_number];

        // start nodes initialisation
        for (int i = 0; i < vertices_number; i++) {
            node_array[i] = new Node(i + 1);
        }
        // node list initialisation
        for (int i = 0; i < edges_number; i++) {
            int start_vertex = sc.nextInt();
            int end_vertex = sc.nextInt();

            Node start_node = node_array[start_vertex - 1];
            Node end_node = node_array[end_vertex - 1];

            start_node.neighbors.add(end_node);
            end_node.neighbors.add(start_node);
        }
        //print_node_array(node_array);
        boolean[] is_marked = new boolean[vertices_number];

        System.out.println("input Graph:");
        print_Graph(node_array[0], is_marked, 0);

        System.out.println("output Graph:");
        is_marked = new boolean[vertices_number];
        print_Graph(cloneGraph(node_array[0]), is_marked, 0);

        sc.close();
    }

    private static void print_Graph(Node top, boolean[] is_marked, int count) {
        System.out.println("[" + count + "]");
        System.out.println("    value: " + top.val);
        System.out.print("    neighbours: ");

        for (Node neighbour : top.neighbors) {
            System.out.print(neighbour.val + " ");
        }
        System.out.println();
        // mark processed vertex
        is_marked[top.val - 1] = true;

        // recursion call on all adjacent unmarked vertices
        for (Node neighbour : top.neighbors) {
            // if vertex is not marked yet
            if (!is_marked[neighbour.val - 1]) {
                print_Graph(neighbour, is_marked, count + 1);
            }
        }
    }

    // implements deep copy of the Graph
    private static Node cloneGraph(Node node) {
        boolean[] is_marked = new boolean[100];
        Node[]  copy_array = new Node[100];

        return clone_dfs(node, is_marked, copy_array);
    }

    private static Node clone_dfs(Node input_node, boolean[] is_marked, Node[] copy_array) {
        Node copy = new Node(input_node.val);
        // mark node as processed
        is_marked[copy.val - 1] = true;
        // add copy of the node in the array
        copy_array[copy.val - 1] = copy;

        for (Node neighbour: input_node.neighbors) {
            // call function recursively for neighbour node if it doesn't mark
            if (!is_marked[neighbour.val - 1]) {
                copy.neighbors.add(clone_dfs(neighbour, is_marked, copy_array));
            }
            else {
                copy.neighbors.add(copy_array[neighbour.val - 1]);
            }
        }
        return copy;
    }
}
