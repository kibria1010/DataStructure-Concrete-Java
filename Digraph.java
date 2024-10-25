package graph;

import java.io.*;
import java.util.*;

public class Digraph {

    private static final String NEWLINE = System.getProperty("line.separator");
    int vertices, edges;
    Bag<Integer>[] adj;
    int[] indegree;

    public Digraph(int vertices) {
        this.vertices = vertices;
        this.edges = 0;

        adj = (Bag<Integer>[]) new Bag[vertices];
        for (int v = 0; v < vertices; v++) {
            adj[v] = new Bag<>();
        }
        indegree = new int[vertices];
    }

    public Digraph() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Enter num of vertices: ");
            this.vertices = Integer.parseInt(in.readLine());
            adj = (Bag<Integer>[]) new Bag[vertices];
            for (int v = 0; v < vertices; v++) {
                adj[v] = new Bag<>();
            }

            System.out.println("Enter num of edges: ");
            int E = Integer.parseInt(in.readLine());

            for (int i = 0; i < E; i++) {
                System.out.println("Enter v to w of edges: " + i + ": ");
                int v = Integer.parseInt(in.readLine());
                int w = Integer.parseInt(in.readLine());
                validateVertex(v);
                validateVertex(w);
                addEdge(v, w);
            }
            indegree = new int[vertices];
        } catch (Exception e) {
        }
    }

    public Digraph(Graph G) {
        this.vertices = G.V();
        this.edges = G.E();
        if (vertices < 0) {
            throw new IllegalArgumentException("Number of vertices must be non-negative");
        }
        adj = (Bag<Integer>[]) new Bag[vertices];
        for (int v = 0; v < vertices; v++) {
            adj[v] = new Bag<>();
        }

        for (int v = 0; v < G.V(); v++) {
            // reverse so that adjacency list is in same order as original
            Stack<Integer> reverse = new Stack<>();
            for (int w : G.adj[v]) {
                reverse.push(w);
            }
            for (int w : reverse) {
                adj[v].add(w);
            }
        }
        indegree = new int[vertices];
    }

    public int V() {
        return vertices;
    }

    public int E() {
        return edges;
    }

    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        
        adj[v].add(w);
        edges++;
        indegree[w]++;
    }

    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        if (v < 0 || v >= vertices) {
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (vertices - 1));
        }
    }
    
       public int outdegree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    public int indegree(int v) {
        validateVertex(v);
        return indegree[v];
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(vertices + " vertices, " + edges + " edges " + NEWLINE);
        for (int v = 0; v < vertices; v++) {
            s.append(String.format("%d: ", v));
            for (int w : adj[v]) {
                s.append(String.format("%d ", w));
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    public static void main(String[] args) {
        // TODO code application logic here
        Digraph g = new Digraph(4);
        g.addEdge(0, 3);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        
        System.out.println(g.toString());
        System.out.println(g.indegree(3));
        System.out.println("digraph");
    }
}
