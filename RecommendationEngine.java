import java.util.*;

public class RecommendationEngine {
    // Graph to store product connections
    private Map<String, List<String>> graph = new HashMap<>();

    // Add product relationship
    public void addProductLink(String productA, String productB) {
        graph.computeIfAbsent(productA, k -> new ArrayList<>()).add(productB);
        graph.computeIfAbsent(productB, k -> new ArrayList<>()).add(productA);
    }

    // BFS recommendation
    public List<String> getRecommendations(String product, int limit) {
        List<String> recommendations = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        visited.add(product);
        queue.add(product);

        while (!queue.isEmpty() && recommendations.size() < limit) {
            String current = queue.poll();
            for (String neighbor : graph.getOrDefault(current, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    recommendations.add(neighbor);
                    queue.add(neighbor);
                    if (recommendations.size() == limit) break;
                }
            }
        }

        return recommendations;
    }

    public static void main(String[] args) {
        RecommendationEngine engine = new RecommendationEngine();

        engine.addProductLink("Shoes", "Socks");
        engine.addProductLink("Shoes", "Sneakers");
        engine.addProductLink("Socks", "Hat");
        engine.addProductLink("Sneakers", "Jacket");

        System.out.println("Recommendations for 'Shoes':");
        System.out.println(engine.getRecommendations("Shoes", 3));
    }
}
