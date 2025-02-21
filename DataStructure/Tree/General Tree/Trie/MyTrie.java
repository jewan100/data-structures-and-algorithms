//TrieNode 클래스: 트라이의 각 노드
class TrieNode {
    TrieNode[] children;
    boolean isEndOfWord;
    int count;

    public TrieNode() {
        children = new TrieNode[26];
        isEndOfWord = false;
        count = 0; // 현재 노드를 포함하여 이 노드 이하에 있는 단어 개수를 저장
    }
}

//Trie 클래스: 트라이 자료구조의 주요 기능들을 포함
class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // 단어를 트라이에 삽입하는 메서드
    public void insert(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (cur.children[index] == null)
                cur.children[index] = new TrieNode();
            cur = cur.children[index];
            cur.count++; // 해당 노드 이하의 단어 개수 증가
        }
        cur.isEndOfWord = true;
    }

    // 특정 단어가 트라이에 존재하는지 검색
    public boolean search(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (cur.children[index] == null)
                return false;
            cur = cur.children[index];
        }
        return cur.isEndOfWord;
    }

    // 특정 접두사를 가진 단어가 존재하는지 확인
    public boolean startsWith(String prefix) {
        TrieNode cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            int index = prefix.charAt(i) - 'a';
            if (cur.children[index] == null)
                return false;
            cur = cur.children[index];
        }
        return true;
    }

    // 특정 접두사를 포함하는 단어 개수를 반환하는 메서드
    public int countWordsWithPrefix(String prefix) {
        TrieNode cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            int index = prefix.charAt(i) - 'a';
            if (cur.children[index] == null)
                return 0;
            cur = cur.children[index];
        }

        return cur.count;
    }
}

public class MyTrie {
    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("apple");
        trie.insert("app");
        trie.insert("application");
        trie.insert("apply");
        trie.insert("apex");
        trie.insert("banana");
        trie.insert("ban");

        System.out.println("search(\"apple\") : " + trie.search("apple")); // true
        System.out.println("search(\"app\") : " + trie.search("app")); // true
        System.out.println("search(\"appl\") : " + trie.search("appl")); // false

        System.out.println("startsWith(\"app\") : " + trie.startsWith("app"));   // true
        System.out.println("startsWith(\"apl\") : " + trie.startsWith("apl"));   // false

        System.out.println("countWordsWithPrefix(\"app\") : " + trie.countWordsWithPrefix("app")); // 3 (apple, app, application, apply)
        System.out.println("countWordsWithPrefix(\"ap\") : " + trie.countWordsWithPrefix("ap"));   // 5 (모든 단어)
        System.out.println("countWordsWithPrefix(\"xyz\") : " + trie.countWordsWithPrefix("xyz")); // 0 (해당 접두사 없음)
    }
}
