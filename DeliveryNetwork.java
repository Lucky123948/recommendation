import java.util.*;

class Graph {
    private final int V;
    private final List<List<int[]>> adj;

    public Graph(int V) {
        this.V = V;
        adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
    }

    public void addEdge(int u, int v, int w) {
        adj.get(u).add(new int[]{v, w});
        adj.get(v).add(new int[]{u, w}); // undirected
    }

    // Prim’s Algorithm: Minimum Spanning Tree
    public void primMST() {
        int[] key = new int[V];
        boolean[] inMST = new boolean[V];
        int[] parent = new int[V];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        Arrays.fill(key, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        key[0] = 0;
        pq.offer(new int[]{0, 0}); // {key, vertex}

        while (!pq.isEmpty()) {
            int u = pq.poll()[1];

            if (inMST[u]) continue;
            inMST[u] = true;

            for (int[] neighbor : adj.get(u)) {
                int v = neighbor[0];
                int w = neighbor[1];

                if (!inMST[v] && w < key[v]) {
                    key[v] = w;
                    pq.offer(new int[]{key[v], v});
                    parent[v] = u;
                }
            }
        }

        System.out.println("Prim's MST (Efficient Delivery Network):");
        int totalCost = 0;
        for (int i = 1; i < V; i++) {
            System.out.println(" - " + parent[i] + " <--> " + i + " : " + key[i] + " km");
            totalCost += key[i];
        }
        System.out.println("Total network cost: " + totalCost + " km\n");
    }

    // Dijkstra’s Algorithm: Shortest Path
    public void dijkstra(int src, int dest) {
        int[] dist = new int[V];
        int[] parent = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        dist[src] = 0;
        pq.offer(new int[]{0, src}); // {distance, vertex}

        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int u = top[1];

            for (int[] neighbor : adj.get(u)) {
                int v = neighbor[0];
                int w = neighbor[1];

                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    parent[v] = u;
                    pq.offer(new int[]{dist[v], v});
                }
            }
        }

        System.out.println("Dijkstra's Shortest Path (Parcel Delivery from " + src + " to " + dest + "):");
        if (dist[dest] == Integer.MAX_VALUE) {
            System.out.println(" - No route found.");
            return;
        }

        List<Integer> path = new ArrayList<>();
        for (int v = dest; v != -1; v = parent[v]) {
            path.add(v);
        }
        Collections.reverse(path);

        System.out.print(" - Path: ");
        for (int node : path) System.out.print(node + " ");
        System.out.println("\n - Total Distance: " + dist[dest] + " km");
    }
}

public class DeliveryNetwork {
    public static void main(String[] args) {
        int V = 6;
        Graph g = new Graph(V);

        // Add edges
        g.addEdge(0, 1, 4);
        g.addEdge(0, 2, 3);
        g.addEdge(1, 2, 1);
        g.addEdge(1, 3, 2);
        g.addEdge(2, 3, 4);
        g.addEdge(3, 4, 2);
        g.addEdge(4, 5, 6);

        // Run Prim's MST
        g.primMST();

        // Run Dijkstra's Algorithm
        int source = 0;      // warehouse
        int destination = 5; // customer
        g.dijkstra(source, destination);
    }
}
