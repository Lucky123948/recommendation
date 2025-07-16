import java.util.*;

class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    boolean isEndOfWord;

    TrieNode() {
        this.isEndOfWord = false;
    }
}

class Trie {
    private final TrieNode root;

    Trie() {
        root = new TrieNode();
    }

    // Insert product into trie
    public void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            node.children.putIfAbsent(ch, new TrieNode());
            node = node.children.get(ch);
        }
        node.isEndOfWord = true;
    }

    // Get suggestions for a given prefix
    public List<String> getSuggestions(String prefix) {
        List<String> results = new ArrayList<>();
        TrieNode node = root;

        for (char ch : prefix.toCharArray()) {
            if (!node.children.containsKey(ch)) {
                return results; // No match found
            }
            node = node.children.get(ch);
        }

        dfsWithPrefix(node, prefix, results);
        return results;
    }

    // Helper: DFS to collect suggestions
    private void dfsWithPrefix(TrieNode node, String prefix, List<String> results) {
        if (node.isEndOfWord) {
            results.add(prefix);
        }

        for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
            dfsWithPrefix(entry.getValue(), prefix + entry.getKey(), results);
        }
    }
}

public class ProductSuggestion {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Trie trie = new Trie();

        // Insert product names
        String[] products = {"shoes", "shirt", "shorts", "socks", "sofa", "soap", "smartphone"};
        for (String product : products) {
            trie.insert(product);
        }

        System.out.print("Enter prefix to get suggestions: ");
        String prefix = sc.nextLine();

        List<String> suggestions = trie.getSuggestions(prefix);

        if (suggestions.isEmpty()) {
            System.out.println("No suggestions found for '" + prefix + "'");
        } else {
            System.out.println("Suggestions:");
            for (String suggestion : suggestions) {
                System.out.println("- " + suggestion);
            }
        }

        sc.close();
    }
}
