#include <bits/stdc++.h>
using namespace std;

const int INF = 1e9;

class Graph {
    int V;
    vector<pair<int, int>>* adj;

public:
    Graph(int V) {
        this->V = V;
        adj = new vector<pair<int, int>>[V];
    }

    void addEdge(int u, int v, int w) {
        adj[u].emplace_back(v, w);
        adj[v].emplace_back(u, w); // undirected graph
    }

    // Prim’s Algorithm: Minimum Spanning Tree
    void primMST() {
        priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
        vector<int> key(V, INF);
        vector<bool> inMST(V, false);
        vector<int> parent(V, -1);

        key[0] = 0;
        pq.push({0, 0});

        while (!pq.empty()) {
            int u = pq.top().second;
            pq.pop();

            if (inMST[u]) continue;
            inMST[u] = true;

            for (const auto& edge : adj[u]) {
                int v = edge.first;
                int w = edge.second;
                if (!inMST[v] && w < key[v]) {
                    key[v] = w;
                    pq.push({key[v], v});
                    parent[v] = u;
                }
            }
        }

        cout << "Prim's MST (Efficient Delivery Network):\n";
        int totalCost = 0;
        for (int i = 1; i < V; ++i) {
            cout << " - " << parent[i] << " <--> " << i << " : " << key[i] << " km\n";
            totalCost += key[i];
        }
        cout << "Total network cost: " << totalCost << " km\n\n";
    }

    // Dijkstra’s Algorithm: Shortest Delivery Route
    void dijkstra(int src, int dest) {
        vector<int> dist(V, INF);
        vector<int> parent(V, -1);
        priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;

        dist[src] = 0;
        pq.push({0, src});

        while (!pq.empty()) {
            int u = pq.top().second;
            pq.pop();

            for (const auto& edge : adj[u]) {
                int v = edge.first;
                int w = edge.second;
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    parent[v] = u;
                    pq.push({dist[v], v});
                }
            }
        }

        cout << "Dijkstra's Shortest Path (Parcel Delivery from " << src << " to " << dest << "):\n";
        if (dist[dest] == INF) {
            cout << " -  No route found.\n";
            return;
        }

        vector<int> path;
        for (int v = dest; v != -1; v = parent[v])
            path.push_back(v);
        reverse(path.begin(), path.end());

        cout << " - Path: ";
        for (int node : path) cout << node << " ";
        cout << "\n - Total Distance: " << dist[dest] << " km\n";
    }
};

int main() {
    int V = 6; // Example: 6 locations
    Graph g(V);

    // Add sample edges (city areas or delivery points)
    g.addEdge(0, 1, 4);
    g.addEdge(0, 2, 3);
    g.addEdge(1, 2, 1);
    g.addEdge(1, 3, 2);
    g.addEdge(2, 3, 4);
    g.addEdge(3, 4, 2);
    g.addEdge(4, 5, 6);

    // Prim’s to build delivery network
    g.primMST();

    // Dijkstra’s to deliver parcel
    int source = 0;      // Warehouse
    int destination = 5; // Customer
    g.dijkstra(source, destination);

    return 0;
}
