package graph;

import java.io.*;
import java.util.*;

public class Graph {

    private static final String NEWLINE = System.getProperty("line.separator");
    int vertices, edges;
    Bag<Integer>[] adj;

    public Graph(int vertices) {
        this.vertices = vertices;
        this.edges = 0;

        adj = (Bag<Integer>[]) new Bag[vertices];
        for (int v = 0; v < vertices; v++) {
            adj[v] = new Bag<>();
        }
    }

    public Graph() {
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
        } catch (Exception e) {
        }
    }

    public Graph(Graph G) {
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
        edges++;
        adj[v].add(w);
        adj[w].add(v);
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

    public int degree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(vertices + " vertices, " + edges + " edges " + NEWLINE);
        for (int v = 0; v < vertices; v++) {
            s.append(v + ": ");
            for (int w : adj[v]) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
    
    public void graphTraverse() {
        Iterator<Integer> it;
        for (int v = 0; v < vertices; v++) {
            System.out.print(v + ": ");
            it = adj(v).iterator();
            while (it.hasNext()) {
                Integer next = it.next();
                System.out.print(next + " ");
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        Graph g = new Graph(3);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(0, 2);
        System.out.println(g.toString());
        System.out.println(g.degree(0));
        System.out.println("------------");
        g.graphTraverse();
        
        

    }
}
