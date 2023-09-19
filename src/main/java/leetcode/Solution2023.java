package leetcode;

import javax.security.auth.login.CredentialNotFoundException;
import java.util.*;

class Solution2023 {
    /*
    1200. 最小绝对差
     */
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        List<List<Integer>> ans = new LinkedList<>();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length - 1; i++) {
            int new_min = arr[i + 1] - arr[i];
            if (new_min <= min) {
                List<Integer> temp = new LinkedList<>();
                if (new_min == min) ans.clear();
                temp.add(arr[i]);
                temp.add(arr[i + 1]);
                ans.add(temp);
                min = new_min;
            }
        }
        return ans;
    }
    /*
    1935. 可以输入的最大单词数
     */
    public int canBeTypedWords(String text, String brokenLetters) {
        int ans = 0;
        String[] strs = text.split(" ");
        if (brokenLetters.length() < 1) return strs.length;
        String bl = ".*[" + brokenLetters + "]+.*";
        for (String str : strs) {
            ans = str.matches(bl) ? ans : ans + 1;
        }
        return ans;
    }
    /*
    404. 左叶子之和
     */
    public int sumOfLeftLeaves(TreeNode root) {
        return root != null ? dfs(root) : 0;
    }
    public int dfs(TreeNode node) {
        int ans = 0;
        if (node.left != null) {
            ans += isLeafNode(node.left) ? node.left.val : dfs(node.left);
        }
        if (node.right != null && !isLeafNode(node.right)) {
            ans += dfs(node.right);
        }
        return ans;
    }
    public boolean isLeafNode(TreeNode node) {
        return node.left == null && node.right == null;
    }
    /*
    415. 字符串相加
     */
    public String addStrings(String num1, String num2) {
        boolean flag = false;
        int len1 = num1.length();
        int len2 = num2.length();
        if (len1 > len2) {
            int t = len1;
            len1 = len2;
            len2 = t;
            String tt = num1;
            num1 = num2;
            num2 = tt;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= len1; i++) {
            int sum = num1.charAt(len1 - i) - '0' + num2.charAt(len2 - i) - '0';
            sum = flag ? sum + 1 : sum;
            flag = sum / 10 == 1 ? true : false;
            sum = sum % 10;
            sb.append(sum);
        }
        for (int i = len2 - len1 - 1; i >= 0; i--) {
            int sum = num2.charAt(i) - '0';
            sum = flag ? sum + 1 : sum;
            flag = sum / 10 == 1 ? true : false;
            sum = sum % 10;
            sb.append(sum);
        }
        if (flag) sb.append("1");
        return sb.reverse().toString();
    }
    /*
    1367. 二叉树中的链表
     */
    public boolean isSubPath(ListNode head, TreeNode root) {
        if (head == null) {
            return true;
        }
        if (root == null) {
            return false;
        }
        //先判断当前的节点，如果不对，再看左子树和右子树呗
        return isSub(head, root) || isSubPath(head, root.left) || isSubPath(head, root.right);
    }

    private boolean isSub(ListNode head, TreeNode node) {
        //特判：链表走完了，返回true
        if (head == null) {
            return true;
        }
        //特判：链表没走完，树走完了，这肯定不行，返回false
        if (node == null) {
            return false;
        }
        //如果值不同，必定不是啊
        if (head.val != node.val) {
            return false;
        }
        //如果值相同，继续看，左边和右边有一个满足即可
        return isSub(head.next, node.left) || isSub(head.next, node.right);
    }
    /*
    面试题 05.04. 下一个数
     */
    public int[] findClosedNumbers(int num) {
        int[] ans = new int[2];
        if (num <=0 || num>=Integer.MAX_VALUE) {
            ans[0] = -1;
            ans[1] = -1;
        } else {
            ans[0] = getNext(num);
            ans[1] = getPrev(num);
        }

        return ans;
    }
    // 取得后一个较大的数
    private int getNext(int n) {
        // 计算c0和c1，用于找到最右边非拖尾0的下标p
        int c = n;
        int c0 = 0;
        int c1 = 0;
        while (((c&1)==0)&&(c!=0)) {
            c0++;
            c >>= 1;
        }
        while ((c&1)==1) {
            c1++;
            c >>= 1;
        }

        // 错误：若n=111111...000, 那么就没有更大的数字
        // 如果是n的二进制不存在可翻转的0，或者n就是0
        if (c0 + c1 == 31 || c0 +c1 ==0) {
            return -1;
        }

        int p = c0+c1; // 前提：最右边，非拖尾0的位置
        n |= (1<<p); // 步骤1：翻转最右边，非拖尾0
        n &= ~((1<<p)-1); // 步骤2：将p右方的所有位清零
        n |= (1<<(c1-1))-1; // 步骤3：在右方插入(c1-1)个1

        return n;
    }
    // 取得前一个较小的数
    private int getPrev(int n) {
        int temp = n;
        int c0 = 0;
        int c1 = 0;
        while ((temp&1)==1) {
            c1++;
            temp >>= 1;
        }

        if (temp == 0) return -1;

        while (((temp &1)==0) &&(temp!=0)) {
            c0++;
            temp >>=1;
        }

        int p = c0+c1; // 最右边，非拖尾1的位置
        n &= ((~0)<<(p+1)); // 将位0到位p清零

        int mask = (1<<(c1+1)) -1; // (c1+1)个1
        n |= mask << (c0-1);

        return n;
    }
    /*
    面试题 16.06. 最小差
     */
    public int smallestDifference(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);
        int[] c = new int[a.length + b.length];
        boolean[] cc = new boolean[c.length];
        int i, j, index;
        i = j = index = 0;
        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                c[index] = a[i++];
                cc[index++] = true;
            }
            else {
                c[index] = b[j++];
                cc[index++] = false;
            }
        }
        while (i < a.length) {
            c[index] = a[i++];
            cc[index++] = true;
        }
        while (j < b.length) {
            c[index] = b[j++];
            cc[index++] = false;
        }
        System.out.println("c = " + Arrays.toString(c));
        int ans = Integer.MAX_VALUE;
        for (int k = 0; k < c.length - 1; k++) {
            if (cc[k] != cc[k + 1]) {
                int t = Math.abs(c[k + 1] - c[k]);
                ans = t > 0 ? Math.min(ans, t) : ans;
            }
        }
        return ans;
    }
    /*
    1331. 数组序号转换
     */
    public int[] arrayRankTransform(int[] arr) {
        if (arr == null || arr.length < 1) return arr;
        int[] temp = arr.clone();
        quickSort(temp, 0, arr.length - 1);
        HashMap<Integer, Integer> map = new HashMap<>();
        int index = 1;
        for (int i = 0; i < arr.length; i++) {
            if (!map.containsKey(temp[i])) {
                map.put(temp[i], index++);
            }
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = map.get(arr[i]);
        }
        return arr;
    }
    public int[] arrayRankTransformSlow2(int[] arr) {
        if (arr == null || arr.length < 1) return arr;
        PriorityQueue<Integer> queue = new PriorityQueue<>(arr.length);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++)
            if (!queue.contains(arr[i])) queue.add(arr[i]);
        int index = 1;
        while (!queue.isEmpty()) {
            int temp = queue.poll();
            map.put(temp, index++);
        }
        for (int i = 0; i < arr.length; i++) {
            int temp = map.get(arr[i]);
            arr[i] = temp;
        }
        return arr;
    }
    public int[] arrayRankTransformSlow(int[] arr) {
        if (arr == null) return arr;
        PriorityQueue<Integer> queue = new PriorityQueue<>(arr.length);
        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (!queue.contains(arr[i])) queue.add(arr[i]);
            if (map.containsKey(arr[i])) {
                HashSet<Integer> set = map.get(arr[i]);
                set.add(i);
                map.remove(arr[i]);
                map.put(arr[i], set);
            }
            else {
                HashSet<Integer> set = new HashSet<>();
                set.add(i);
                map.put(arr[i], set);
            }
        }
        int index = 1;
        while (!queue.isEmpty()) {
            int temp = queue.poll();
            HashSet<Integer> tempSet = map.get(temp);
            for (Integer x : tempSet) {
                arr[x] = index;
            }
            index++;
        }
        return arr;
    }
    /*
    1736. 替换隐藏数字得到的最晚时间
     */
    public String maximumTime(String time) {
        StringBuilder sb = new StringBuilder();
        if ((time.charAt(0) == '?' || time.charAt(0) == '2') && time.charAt(1) == '?') sb.append("23");
        else if (time.charAt(0) == '?' && time.charAt(1) <= '3') sb.append('2').append(time.charAt(1));
        else if (time.charAt(0) == '?') sb.append('1').append(time.charAt(1));
        else if (time.charAt(1) == '?') sb.append(time.charAt(0)).append('9');
        else sb.append(time.substring(0, 2));
        sb.append(':');
        if (time.charAt(3) == '?') sb.append('5');
        else sb.append(time.charAt(3));
        if (time.charAt(4) == '?') sb.append('9');
        else sb.append(time.charAt(4));
        return sb.toString();
    }
    /*
    1748. 唯一元素的和
     */
    public int sumOfUnique(int[] nums) {
        int ans = 0;
        HashMap<Integer, Integer> map = new HashMap<>(nums.length);
        for (int x : nums) {
            if (map.containsKey(x)) {
                int t = map.get(x) + 1;
                map.remove(x);
                map.put(x, t);
            }
            else map.put(x, 1);
        }
        for (Integer key : map.keySet()) {
            if (map.get(key) == 1) {
                ans += key;
            }
        }
        return ans;
    }
    /*
    1720. 解码异或后的数组
     */
    public int[] decode(int[] encoded, int first) {
        int[] ans = new int[encoded.length + 1];
        ans[0] = first;
        for (int i = 0; i < ans.length - 1; i++) {
            ans[i + 1] = ans[i] ^ encoded[i];
        }
        return ans;
    }
    /*
    面试题 16.10. 生存人数
    差分数组
     */
    public int maxAliveYear(int[] birth, int[] death) {
        int start = 1900, end = 2000;
        int[] num = new int[end - start + 2];
        for (int i = 0; i < birth.length; i++) {
            num[birth[i] - 1900]++;
            num[death[i] - 1900 + 1]--;
        }
        int max = 0, sum = 0, res = 0;
        for (int i = 0; i < end - start + 1; i++) {
            sum += num[i];
            if (sum > max) {
                max = sum;
                res = i;
            }
        }
        return start + res;
    }
    /*
    2583. 二叉树中的第 K 大层和
     */
    public long kthLargestLevelSum(TreeNode root, int k) {
        if (root == null) return -1;
        int oldNodeCount, newNodeCount, levelCount, tempCount;
        long tempSum = 0l;
        oldNodeCount = 1;
        newNodeCount = levelCount = tempCount = 0;
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
//        long[] ansTree = new long[k];
        PriorityQueue<Long> ansTree = new PriorityQueue<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode tempNode = queue.pop();
            tempCount++;
            if (tempNode.left != null) {
                queue.add(tempNode.left);
                newNodeCount++;
            }
            if (tempNode.right != null) {
                queue.add(tempNode.right);
                newNodeCount++;
            }
            tempSum += tempNode.val;
            if (tempCount == oldNodeCount) {
                oldNodeCount = newNodeCount;
                newNodeCount = 0;
                tempCount = 0;
                levelCount++;
//                insertHelper(ansTree, k, levelCount, tempSum);
                insertHelper(ansTree, k, levelCount, tempSum);
                tempSum = 0;
            }
        }
        if (levelCount < k) return -1;
//        return ansTree[k - 1];
        return ansTree.peek();
    }
    private void insertHelperSlow(long[] tree, int len, int level, long x) {
        //垃圾版本，遍历
        if (level >= len || (level < len && x > tree[len - 1]) || level == 1) {
            for (int i = 0; i <= len - 1; i++) {
                if (x > tree[i]) {
                    long t = tree[i];
                    tree[i] = x;
                    x = t;
                }
            }
        }
    }
    private void insertHelper(PriorityQueue<Long> tree, int len, int level, long x) {
        System.out.println(tree.peek());
        //使用堆
        if (level <= len) {
            tree.add(x);
        }
        else if (x > tree.peek()) {
            tree.poll();
            tree.add(x);
        }
    }
    /*
    58. 最后一个单词的长度
     */
    public int lengthOfLastWord(String s) {
        String[] strs = s.trim().split(" ");
        return strs[strs.length - 1].length();
    }
    /*
    695. 岛屿的最大面积
     */
    public int maxAreaOfIslandV2(int[][] grid) {
        if (grid == null) return 0;
        int ans = 0;
        int xlen = grid.length;
        int ylen = grid[0].length;
        int index = 0;
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        boolean[][] flag = new boolean[xlen][ylen];
        for (int i = 0; i < xlen; i++) {
            for (int j = 0; j < ylen; j++) {
                if (flag[i][j]) continue;
                flag[i][j] = true;
                if (grid[i][j] == 1) {
                    int temp = 0;
                    queue.add(i * 100 + j);
                    while (!queue.isEmpty()) {
                        temp++;
                        int t = queue.pop();
                        int x = t / 100;
                        int y = t % 100;
                        if (y > 0) {
                            if (!flag[x][y - 1] && grid[x][y - 1] == 1) queue.add(x * 100 + y - 1);
                            flag[x][y - 1] = true;
                        }
                        if (x > 0) {
                            if (!flag[x - 1][y] && grid[x - 1][y] == 1) queue.add((x - 1) * 100 + y);
                            flag[x - 1][y] = true;
                        }
                        if (y < ylen - 1) {
                            if (!flag[x][y + 1] && grid[x][y + 1] == 1) queue.add(x * 100 + y + 1);
                            flag[x][y + 1] = true;
                        }
                        if (x < xlen - 1) {
                            if (!flag[x + 1][y] && grid[x + 1][y] == 1) queue.add((x + 1) * 100 + y);
                            flag[x + 1][y] = true;
                        }
                    }
                    ans = Math.max(temp, ans);
                }
            }
        }
        return ans;
    }
    public int maxAreaOfIslandV1(int[][] grid) {
        if (grid == null) return 0;
        int ans = 0;
        int xlen = grid.length;
        int ylen = grid[0].length;
        class IslandIndex {
            short x;
            short y;
            IslandIndex(int xx, int yy) {
                x = (short) xx;
                y = (short) yy;
            }
        }
        ArrayDeque<IslandIndex> queue = new ArrayDeque<>();
        boolean[][] flag = new boolean[xlen][ylen];
        for (int i = 0; i < xlen; i++) {
            for (int j = 0; j < ylen; j++) {
                flag[i][j] = false;
            }
        }
        for (int i = 0; i < xlen; i++) {
            for (int j = 0; j < ylen; j++) {
                if (flag[i][j]) continue;
                flag[i][j] = true;
                if (grid[i][j] == 1) {
                    int temp = 0;
                    queue.add(new IslandIndex(i, j));
                    while (!queue.isEmpty()) {
                        temp++;
                        IslandIndex t = queue.pop();
                        int x = t.x;
                        int y = t.y;
                        System.out.println("x&y:" + x + " " + y);
                        if (y > 0) {
                            if (!flag[x][y - 1] && grid[x][y - 1] == 1) queue.add(new IslandIndex(x, y - 1));
                            flag[x][y - 1] = true;
                        }
                        if (x > 0) {
                            if (!flag[x - 1][y] && grid[x - 1][y] == 1) queue.add(new IslandIndex(x - 1, y));
                            flag[x - 1][y] = true;
                        }
                        if (y < ylen - 1) {
                            if (!flag[x][y + 1] && grid[x][y + 1] == 1) queue.add(new IslandIndex(x, y + 1));
                            flag[x][y + 1] = true;
                        }
                        if (x < xlen - 1) {
                            if (!flag[x + 1][y] && grid[x + 1][y] == 1) queue.add(new IslandIndex(x + 1, y));
                            flag[x + 1][y] = true;
                        }
                    }
                    System.out.println(i + ":" + j + ":" + ans + ":" + temp);
                    ans = Math.max(temp, ans);
                }
            }
        }
        return ans;
    }
    /*
    274. H 指数
     */
    public int hIndex(int[] citations) {
        if (citations == null) return 0;
        int len = citations.length;
        quickSort(citations, 0, len - 1);
//        System.out.println("citations = " + Arrays.toString(citations));;
        for (int i = 0; i < len; i++) {
            if (citations[i] >= len - i) return Math.min(citations[i], len - i);
        }
        return 0;
    }
    /*
    12. 整数转罗马数字
     */
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        char[] romanChar = {'M', 'D', 'C', 'L', 'X', 'V', 'I'};
        int[] romanValue = {1000, 500, 100, 50, 10, 5, 1};
        int cnt = 0;
        int i = 0;
        while (num > 0) {
            int num1, num2;
            num1 = num2 = 0;
            if (i < 6) {
                num2 = romanValue[i + 1] - romanValue[i + 2];
                if (i < 5) num1 = romanValue[i] - romanValue[i + 2];
            }
            if (num > romanValue[i]) {
                cnt = num / romanValue[i];
                for (int j = 0; j < cnt; j++) {
                    sb.append(romanChar[i]);
                }
                num = num % romanValue[i];
            }
            if (i < 5 && num >= num1) {
                sb.append(romanChar[i + 2]).append(romanChar[i]);
                num -= num1;
            }
            if (i < 6 && num / romanValue[i + 1] > 0) {
                sb.append(romanChar[i + 1]);
                num = num % romanValue[i + 1];
            }
            if (i < 6 && num >= num2) {
                sb.append(romanChar[i + 2]).append(romanChar[i + 1]);
                num -= num2;
            }
            i += 2;
        }
        return sb.toString();
    }
    /*
     * 6. N 字形变换
     */
    public String convert(String s, int numRows) {
        if (numRows == 1) return s;
        StringBuilder[] sbs = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            sbs[i] = new StringBuilder();
        }
        int i = 0;
        boolean flag = false;
        for (Character c : s.toCharArray()) {
            sbs[i].append(c);
            if (i == numRows - 1 || i == 0) {
                flag = !flag;
            }
            if (flag) i++;
            else i--;
        }
        StringBuilder sb = sbs[0];
        for (int k = 1; k < numRows; k++) {
            sb.append(sbs[k]);
        }
        return sb.toString();
    }
    /*
    2500. 删除每行中的最大值
     */
    public int deleteGreatestValue(int[][] grid) {
        if (grid == null) return 0;
        int len = grid[0].length;
        for (int i = 0; i < grid.length; i++) {
            quickSort(grid[i], 0, len - 1);
        }
        int ans = 0;
        for (int i = 0; i < len; i++) {
            int max = grid[0][i];
            for (int j = 0; j < grid.length; j++) {
                max = max > grid[j][i] ? max : grid[j][i];
            }
            ans += max;
        }
        return ans;
    }
    /*
    2682. 找出转圈游戏输家
     */
    public int[] circularGameLosers(int n, int k) {
        HashSet<Integer> set = new HashSet<>(n);
        for (int i = 1; i <= n; i++) {
            set.add(i);
        }
        int temp = 1;
        int j = 1;
        while (set.contains(temp)) {
            System.out.println(temp);
            set.remove(temp);
            temp = temp + k * j++;
            System.out.println("old temp is " + temp);
            temp = temp % n == 0 ? n : temp % n;
            System.out.println("temp is " + temp);
        }
        int[] ans = new int[set.size()];
        int i = 0;
        for (Integer x : set) {
            ans[i++] = x;
        }
        return ans;
    }
    /*
    100. 相同的树
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        Queue<TreeNode> pqueue = new ArrayDeque<>();
        Queue<TreeNode> qqueue = new ArrayDeque<>();
        if (p == null) {
            if (q == null) return true;
            else return false;
        }
        else {
            if (q == null) return false;
            else {

                pqueue.add(p);
                qqueue.add(q);
                while (!pqueue.isEmpty()) {
                    TreeNode tp = pqueue.poll();
                    TreeNode tq = qqueue.poll();
                    if (tp.val != tq.val || tp.left == null && tq.left != null || tp.right == null && tq.right != null || tp.left != null && tq.left == null || tp.right != null && tq.right == null) return false;
                    else {
                        if (tp.left != null) {
                            pqueue.add(tp.left);
                            qqueue.add(tq.left);
                        }
                        if (tp.right != null) {
                            pqueue.add(tp.right);
                            qqueue.add(tq.right);
                        }
                    }
                }
                if (qqueue.isEmpty()) return true;
                else return false;
            }
        }
    }
    /*
    67. 二进制求和
     */
    public String addBinary(String a, String b) {
        short add = 0;
        short sum = 0;
        if (a.length() > b.length()) { // b is big
            String t = a;
            a = b;
            b = t;
        }
        int alen = a.length();
        int blen = b.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < alen; i++) {
            char aa = a.charAt(alen - i - 1);
            char bb = b.charAt(blen - i - 1);
            if (aa == bb) {
                sum = add;
                if (aa == '1') add = 1;
                else add = 0;
            }
            else {
                short temp = (short) (1 + add);
                sum = (short) (temp % 2);
                add = (short) (temp / 2);
            }
            sb.append(sum);
        }
        int i = alen;
        for (i = alen; i < blen; i++) {
            short temp = (short) (b.charAt(blen - i - 1) - 48 + add);
            sum = (short) (temp % 2);
            add = (short) (temp / 2);
            sb.append(sum);
        }
        if (add != 0) sb.append(1);
        return  sb.reverse().toString();
    }
    /*
    142. 环形链表 II
     */
    public ListNode detectCycle(ListNode head) {
        ListNode fast,slow,temp;
        fast = slow = temp = head;
        do {
            if (fast.next == null || fast.next.next == null) return null;
            fast = fast.next.next;
            slow = slow.next;
        } while (fast != slow);
        HashSet<ListNode> set = new HashSet<>();
        do {
            set.add(slow);
            slow = slow.next;
        } while (slow != fast);
        while (!set.contains(temp)) {
            temp = temp.next;
        }
        return temp;
    }
    /*
    9. 回文数
     */
    public boolean isPalindrome(int x) {
        if (x >= 0 && x < 10) return true;
        else if (x < 0) return false;
        List<Short> list = new ArrayList<>();
        while (x != 0) {
            list.add((short) (x % 10));
            x = x / 10;
        }
        int head = 0;
        int tail = list.size() - 1;
        while (head < tail) {
            if (list.get(head) != list.get(tail)) return false;
            head++;
            tail--;
        }
        return true;
    }
    /*
    2824. 统计和小于目标的下标对数目
     */
    public int countPairs(List<Integer> nums, int target) {
        if (nums == null || nums.size() < 1) return 0;
        int n = nums.size();
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = nums.get(i);
        }
        quickSort(numbers, 0, n - 1);
        int ans,head,tail = n - 1;
        ans = head = 0;
        while (head != tail) {
            if (numbers[head] + numbers[tail] < target) {
                ans += tail - head;
                head++;
            }
            else {
                tail--;
            }
        }
        return ans;
    }
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }
    private static void swap (int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    private static <T> void swap (T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void quickSortTwoArray(int[] arr, int[] arr2, int low, int high) {
        if (low < high) {
            int pi = partitionTwoArray(arr, arr2, low, high);
            quickSortTwoArray(arr, arr2,  low, pi - 1);
            quickSortTwoArray(arr, arr2,  pi + 1, high);
        }
    }
    private static int partitionTwoArray(int[] arr, int[] arr2, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swapTwoArray(arr, arr2, i, j);
            }
        }
        swapTwoArray(arr, arr2, i + 1, high);
        return i + 1;
    }
    private static void swapTwoArray (int[] arr, int[] arr2, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        temp = arr2[i];
        arr2[i] = arr2[j];
        arr2[j] = temp;
    }



    public static void main(String []args){
//        while (true) {
            System.out.println("Plesae input:");
            Scanner in = new Scanner(System.in);
            Solution2023 solution = new Solution2023();
            int[] a = {9,-5,-5,5,-5,-4,-6,6,-6};
            int[] b = {3, 0, 6, 1, 5};
            int[] d = {1, 2, 3, 4, 5};
            int[][] c = {
                    {0,0,1,0,0,0,0,1,0,0,0,0,0},
                    {0,0,0,0,0,0,0,1,1,1,0,0,0},
                    {0,1,1,0,1,0,0,0,0,0,0,0,0},
                    {0,1,0,0,1,1,0,0,1,0,1,0,0},
                    {0,1,0,0,1,1,0,0,1,1,1,0,0},
                    {0,0,0,0,0,0,0,0,0,0,1,0,0},
                    {0,0,0,0,0,0,0,1,1,1,0,0,0},
                    {0,0,0,0,0,0,0,1,1,0,0,0,0}};
            int target = 3;
            List<Integer> alist = new ArrayList();
            for (int aa : a) {
                alist.add(aa);
            }
            String astr = "100";
            String bstr = "110010";
            String str = "AB";
//            String str = "PAYPALISHIRING";
//            for (Integer x:ans) {
//                System.out.println(x + "     ");
//            }

//            System.out.println(solution.countPairs(alist, target));
//            System.out.println(solution.isPalindrome(in.nextInt()));
//        }
//        System.out.println(solution.intToRoman(1958));
//        Solution2023.quickSortTwoArray(b, d, 0, b.length - 1);
//        for (int x : b) {
//            System.out.print(x + "\t");
//        }
//        System.out.println();
//        for (int x : d) {
//            System.out.print(x + "\t");
//        }
//        while (true) {
//            System.out.println(Arrays.toString(solution.findClosedNumbers(in.nextInt())));
//        }
        System.out.println(solution.canBeTypedWords("leet code", "e"));
    }
}