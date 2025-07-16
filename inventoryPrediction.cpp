#include <bits/stdc++.h>
using namespace std;

const int INF = 1e9;

int inventoryPredictionDP(vector<int>& demand, int cost_per_unit, int holding_cost, int restock_cost) {
    int N = demand.size();
    vector<int> dp(N + 1, 0); // dp[i] = min cost from day i to end

    for (int i = N - 1; i >= 0; --i) {
        int min_cost = INF;
        int total_units = 0;
        int hold_cost = 0;

        for (int j = i; j < N; ++j) {
            total_units += demand[j];
            if (j > i)
                hold_cost += (j - i) * demand[j] * holding_cost;

            int cost = restock_cost + total_units * cost_per_unit + hold_cost + dp[j + 1];
            min_cost = min(min_cost, cost);
        }
        dp[i] = min_cost;
    }
    return dp[0];
}

int main() {
    // Example: 5 days of demand
    vector<int> demand = {10, 20, 15, 10, 30};

    int cost_per_unit = 5;
    int holding_cost = 1;
    int restock_cost = 20;

    int min_total_cost = inventoryPredictionDP(demand, cost_per_unit, holding_cost, restock_cost);

    cout << "Minimum total cost to fulfill all demands = ₹" << min_total_cost << endl;

    return 0;
}
