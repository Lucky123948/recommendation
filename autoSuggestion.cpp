#include<bits/stdc++.h>
using namespace std;
struct TrieNode {
    unordered_map<char, TrieNode*> children;
    bool isEndOfWord;
    TrieNode() : isEndOfWord(false) {}
};
class Trie {
private:
    TrieNode* root;
    void dfsWithPrefix(TrieNode* node, string prefix, vector<string>& results) {
        if (node->isEndOfWord)
            results.push_back(prefix);
        for (auto& child : node->children) {
            dfsWithPrefix(child.second, prefix + child.first, results);
        }
    }

public:
    Trie() {
        root = new TrieNode();
    }

    // Insert product name into Trie
    void insert(const string& word) {
        TrieNode* node = root;
        for (char ch : word) {
            if (!node->children[ch])
                node->children[ch] = new TrieNode();
            node = node->children[ch];
        }
        node->isEndOfWord = true;
    }

    // Return suggestions for given prefix
    vector<string> getSuggestions(const string& prefix) {
        TrieNode* node = root;
        vector<string> results;

        // Traverse to the end of prefix
        for (char ch : prefix) {
            if (!node->children[ch])
                return {}; // No suggestion
            node = node->children[ch];
        }

        // DFS from current node
        dfsWithPrefix(node, prefix, results);
        return results;
    }
};

int main() {
    Trie trie;

    // Insert product names
    vector<string> products = {"shoes", "shirt", "shorts", "socks", "sofa", "soap", "smartphone"};
    for (const string& product : products)
        trie.insert(product);

    string prefix;
    cout << "Enter prefix to get suggestions: ";
    cin >> prefix;

    vector<string> suggestions = trie.getSuggestions(prefix);

    if (suggestions.empty()) {
        cout << "No suggestions found for '" << prefix << "'" << endl;
    } else {
        cout << "Suggestions:\n";
        for (const string& suggestion : suggestions)
            cout << "- " << suggestion << endl;
    }

    return 0;
}
