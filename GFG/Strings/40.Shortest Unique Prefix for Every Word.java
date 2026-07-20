import java.util.*;

class Solution {

    static class TrieNode {
        TrieNode[] child = new TrieNode[26];
        int count = 0;
    }

    TrieNode root = new TrieNode();

    private void insert(String word) {

        TrieNode curr = root;

        for (char ch : word.toCharArray()) {

            int idx = ch - 'a';

            if (curr.child[idx] == null)
                curr.child[idx] = new TrieNode();

            curr = curr.child[idx];
            curr.count++;
        }
    }

    private String getPrefix(String word) {

        TrieNode curr = root;

        StringBuilder sb = new StringBuilder();

        for (char ch : word.toCharArray()) {

            curr = curr.child[ch - 'a'];
            sb.append(ch);

            if (curr.count == 1)
                break;
        }

        return sb.toString();
    }

    public ArrayList<String> findPrefixes(String[] arr) {

        for (String word : arr)
            insert(word);

        ArrayList<String> ans = new ArrayList<>();

        for (String word : arr)
            ans.add(getPrefix(word));

        return ans;
    }
}

// Time Complexity: O(N * L) where N is the number of words in the input array and L is the average length of the words. We insert each word into the Trie and then retrieve the unique prefix for each word, both operations taking O(L) time.
// Space Complexity: O(N * L) for the Trie data structure. In the worst case, we may have to store all characters of all words in the Trie, leading to a space complexity

//Algorithm Explanation:
// 1. We define a TrieNode class that represents each node in the Trie. Each node has an array of child nodes (for each letter a-z) and a count of how many words pass through that node.
// 2. We insert each word into the Trie, updating the count at each node to reflect how many words share that prefix.
// 3. To find the shortest unique prefix for each word, we traverse the Trie from the root, appending characters to a StringBuilder until we reach a node with a count of 1, indicating that the prefix is unique to that word.
// 4. Finally, we return a list of the shortest unique prefixes for all words in the input array.
