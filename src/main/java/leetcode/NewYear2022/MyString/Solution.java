package leetcode.NewYear2022.MyString;


import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by shaobin on 2022/1/5.
 */
public class Solution {
    // 找出无序数组中只存在一次的数
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int x : nums) {
            ans ^= x;
        }
        return ans;
    }

    // 找出无序数组中存在超过一半的数
    public int majorityElement(int[] nums) {
        int ans = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                ans = nums[i];
                count++;
            }
            else if (ans == nums[i]) count++;
            else count--;
        }
        return ans;
    }

    // 二分查找
    public int BSearch(int[] array, int target) {
        return BSearchHelper(array, target, 0, array.length - 1);
    }
    // 二分查找辅助程序
    public int BSearchHelper(int[] array, int target, int start, int end) {
        if (start > end) return -1;
        int mid = (start + end) / 2;
        if (array[mid] == target) return mid;
        else if (array[mid] > target) return BSearchHelper(array, target, start, mid - 1);
        else return BSearchHelper(array, target, mid + 1, end);
    }

    // 高效的搜索二维矩阵
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix[0].length - 1;
        int ans = -1;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == target || matrix[i][n] == target) return true;
            if (matrix[i][0] < target && matrix[i][n] > target) {
                int temp = BSearch(matrix[i], target);
                ans = temp > 0 ? temp : ans;
            }
        }
        return ans >= 0;
    }
    // 高效矩阵查找可使用Z字查找
    public boolean searchMatrixZ(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int x = 0, y = n - 1;
        while (x < m && y >= 0) {
            if (matrix[x][y] == target) {
                return true;
            }
            if (matrix[x][y] > target) {
                --y;
            } else {
                ++x;
            }
        }
        return false;
    }

    // 合并两个有序数组
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int i = 0; i < (m + n) / 2; i++) {
            int t = nums1[i];
            nums1[i] = nums1[m + n - i - 1];
            nums1[m + n - i - 1] = t;
        }
        for (int i = n; i < n + m / 2; i++) {
            int t = nums1[i];
            nums1[i] = nums1[nums1.length - i - 1 + n];
            nums1[nums1.length - i - 1 + n] = t;
        }
        int i = 0;
        int j = n;
        int index = 0;
        while (i < n && j < n + m) {
            if (nums2[i] < nums1[j]) {
                nums1[index++] = nums2[i++];
            }
            else {
                nums1[index++] = nums1[j++];
            }
        }
        while (i < n) {
            nums1[index++] = nums2[i++];
        }
        while (j < n + m) {
            nums1[index++] = nums1[j++];
        }
    }

    // 鸡蛋掉落
    public int superEggDrop(int k, int n) {
        if (n == 1) {
            return 1;
        }
        int[][] f = new int[n + 1][k + 1];
        for (int i = 1; i <= k; ++i) {
            f[1][i] = 1;
        }
        int ans = -1;
        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j <= k; ++j) {
                f[i][j] = 1 + f[i - 1][j - 1] + f[i - 1][j];
            }
            if (f[i][k] >= n) {
                ans = i;
                break;
            }
        }
        return ans;
    }

    // 判断回文字符串，忽略大小写，只考虑数字和字母
    public boolean isPalindrome(String s) {
        s = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        return isPalindromeHelper(s);
    }
    public boolean isPalindromeHelper(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - i - 1)) return false;
        }
        return true;
    }

    // 分割回文串，只有小写字母
    public List<List<String>> partition(String s) {

        return new LinkedList<>();
    }

    // 单词拆分，只包含小写字母，Trie树匹配超时
    public boolean wordBreakTrie(String s, List<String> wordDict) {
        DictTree root = new DictTree('1');
        for (String word : wordDict) {
            DictTree.updateTree(root, word);
        }
        return wordBreakHelper(s, root, root);
    }
    public boolean wordBreakHelper(String s, DictTree root, DictTree now) {
        if (s.length() < 1 && now.children.containsKey('0')) return true;
        else if (s.length() < 1) return false;
        System.out.println("\t" + s + ":\t" + root.val + ",\t" + now.val);
        if (now.children.containsKey(s.charAt(0)) && now.children.containsKey('0')) {
            now = now.children.get(s.charAt(0));
            System.out.println(1 + ":" + s);
            return wordBreakHelper(s, root, root) || wordBreakHelper(s.substring(1), root, now);
        }
        if (now.children.containsKey('0')) {
            System.out.println(2 + ":" + s);
            return wordBreakHelper(s, root, root);
        }
        if (now.children.containsKey(s.charAt(0))) {
            System.out.println(3 + ":" + s);
            return wordBreakHelper(s.substring(1), root, now.children.get(s.charAt(0)));
        }
        return false;
    }
    // 单词拆分，只包含小写字母，dp方法
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    // 有效字母异位词，字母相同且数量相同，仅位置不同
    public boolean isAnagram(String s, String t) {
        int[] cnt = new int[26];
        for (Character c : s.toCharArray()) {
            cnt[c - 'a']++;
        }
        for (Character c : t.toCharArray()) {
            cnt[c - 'a']--;
        }
        for (int c : cnt) {
            if (c != 0) return false;
        }
        return true;
    }

    // 返回字符串中第一个唯一字符，不存在返回-1
    public int firstUniqChar(String s) {
        int[] cnt = new int[26];
        int[] index = new int[26];
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            cnt[c - 'a']++;
            if (cnt[c - 'a'] == 1) index[c - 'a'] = i;
        }
        int ans = -1;
        for (int i = 0; i < cnt.length; i++) {
            if (cnt[i] == 1) {
                if (ans == -1) ans = index[i];
                else ans = ans < index[i] ? ans : index[i];
            }
        }
        return ans;
    }

    // 反转字符串
    public void reverseString(char[] s) {
        for (int i = 0; i < s.length / 2; i++) {
            char t = s[i];
            s[i] = s[s.length - 1 - i];
            s[s.length - 1 - i] = t;
        }
    }

    // 单词搜索II
    public List<String> findWords(char[][] board, String[] words) {
        List<String> ans = new LinkedList<>();
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }
        Set<String> temp = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                temp = findWordsHelper(board, trie, "", i, j, temp);
            }
        }
        ans.addAll(temp);
        return ans;
    }
    public Set<String> findWordsHelper(char[][] board, Trie now, String str, int i, int j, Set<String> ans) {
        if (now.isEnd()) ans.add(str);
        if (i > 0) {
            char up = board[i - 1][j];
            Trie upchild = now.getChildren()[up - 'a'];
            if (upchild != null) ans.addAll(findWordsHelper(board, upchild, str + up, i - 1, j, ans));
        }
        if (i < board.length - 1) {
            char down = board[i + 1][j];
            Trie downchild = now.getChildren()[down - 'a'];
            if (downchild != null) ans.addAll(findWordsHelper(board, downchild, str + down, i + 1, j, ans));
        }
        if (j > 0) {
            char left = board[i][j - 1];
            Trie leftchild = now.getChildren()[left - 'a'];
            if (leftchild != null) ans.addAll(findWordsHelper(board, leftchild, str + left, i, j - 1, ans));
        }
        if (j < board[0].length - 1) {
            char right = board[i][j + 1];
            Trie rightchild = now.getChildren()[right - 'a'];
            if (rightchild != null) ans.addAll(findWordsHelper(board, rightchild, str + right, i, j + 1, ans));
        }
        return ans;
    }
    // 单词搜索II最佳
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public List<String> findWordsNew(char[][] board, String[] words) {
        TrieNew trie = new TrieNew();
        for (String word : words) {
            trie.insert(word);
        }

        Set<String> ans = new HashSet<String>();
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                findWordsNew(board, trie, i, j, ans);
            }
        }

        return new ArrayList<String>(ans);
    }
    public void findWordsNew(char[][] board, TrieNew now, int i1, int j1, Set<String> ans) {
        if (!now.children.containsKey(board[i1][j1])) {
            return;
        }
        char ch = board[i1][j1];
        TrieNew nxt = now.children.get(ch);
        if (!"".equals(nxt.word)) {
            ans.add(nxt.word);
            nxt.word = "";
        }

        if (!nxt.children.isEmpty()) {
            board[i1][j1] = '#';
            for (int[] dir : dirs) {
                int i2 = i1 + dir[0], j2 = j1 + dir[1];
                if (i2 >= 0 && i2 < board.length && j2 >= 0 && j2 < board[0].length) {
                    findWordsNew(board, nxt, i2, j2, ans);
                }
            }
            board[i1][j1] = ch;
        }

        if (nxt.children.isEmpty()) {
            now.children.remove(ch);
        }
    }




    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] a = {1,2,0,0,0};
        int[] b = {2,5,6};
        int[][] matrix = {{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
//        int[][] matrix = {{1}};
        System.out.println(solution.singleNumber(a));
        System.out.println(solution.searchMatrix(matrix, 5));
        solution.merge(a,2,b,3);
        System.out.println(solution.superEggDrop(2,6));
        List<String> wordDict = new LinkedList<>();
//        wordDict.add("cats");
//        wordDict.add("dog");
//        wordDict.add("sand");
//        wordDict.add("and");
//        wordDict.add("cat");
        wordDict.add("leet");
        wordDict.add("code");
        DictTree root = new DictTree('1');
        for (String w : wordDict) {
            DictTree.updateTree(root, w);
        }
        Queue<DictTree> queue = new LinkedBlockingDeque<>();
        queue.add(root);
        int now = 1;
        int next = 0;
        while (!queue.isEmpty()) {
            DictTree temp = queue.poll();
            System.out.print(temp.val + "\t");
            now--;
            HashMap<Character, DictTree> childrenMap = temp.children;
            next += childrenMap.size();
            for (Character key : childrenMap.keySet()) {
                queue.add(childrenMap.get(key));
            }
            if (now == 0) {
                now = next;
                next = 0;
                System.out.println();
            }
        }
        String str = "leetcode";
        System.out.println(str.substring(1));
        System.out.println(solution.wordBreak(str, wordDict));
    }
}
