/**Name: Sreemoyee Mukherjee
 * Andrew ID: sreemoym
 * Course: Data Structures & Algorithms
 * Assignment Number: 3
 */
package ds;

// class for building the graph
public class Graph {
    private boolean[][] edges;
    private Object[] vertex;
    public Graph (int n) {
        edges = new boolean[n][n];
        vertex = new Object[n];
    }
    public void addEdge(int s, int t) {
        edges[s][t] = true; // mark true i.e. 1 for an edge between two vertices
        edges[t][s] = true; // edge should be set to true for both course 0 and course 1 as for course 1 and course 0
    }
    public boolean isEdge(int s, int t) {
        return edges[s][t];
    }

    public int size() {
        return vertex.length;
    }

}
