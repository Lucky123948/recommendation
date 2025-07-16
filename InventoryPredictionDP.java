import java.util.*;

public class InventoryPredictionDP {

    static final int INF = (int) 1e9;

    public static int inventoryPredictionDP(List<Integer> demand, int costPerUnit, int holdingCost, int restockCost) {
        int N = demand.size();
        int[] dp = new int[N + 1]; // dp[i] = min cost from day i to end

        for (int i = N - 1; i >= 0; --i) {
            int minCost = INF;
            int totalUnits = 0;
            int holdCost = 0;

            for (int j = i; j < N; ++j) {
                totalUnits += demand.get(j);
                if (j > i) {
                    holdCost += (j - i) * demand.get(j) * holdingCost;
                }

                int cost = restockCost + totalUnits * costPerUnit + holdCost + dp[j + 1];
                minCost = Math.min(minCost, cost);
            }
            dp[i] = minCost;
        }

        return dp[0];
    }

    public static void main(String[] args) {
        // Example: 5 days of demand
        List<Integer> demand = Arrays.asList(10, 20, 15, 10, 30);

        int costPerUnit = 5;
        int holdingCost = 1;
        int restockCost = 20;

        int minTotalCost = inventoryPredictionDP(demand, costPerUnit, holdingCost, restockCost);

        System.out.println("Minimum total cost to fulfill all demands = ₹" + minTotalCost);
    }
}
