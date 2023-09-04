package leetcode.NewYear2022.MyString;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shaobin on 2022/1/9.
 */
class TrieNew {
    String word;
    Map<Character, TrieNew> children;

    public TrieNew() {
        this.word = "";
        this.children = new HashMap<Character, TrieNew>();
    }

    public void insert(String word) {
        TrieNew cur = this;
        for (int i = 0; i < word.length(); ++i) {
            char c = word.charAt(i);
            if (!cur.children.containsKey(c)) {
                cur.children.put(c, new TrieNew());
            }
            cur = cur.children.get(c);
        }
        cur.word = word;
    }
}

