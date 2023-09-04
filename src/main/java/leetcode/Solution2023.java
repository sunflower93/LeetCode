package leetcode;

import java.util.*;

class Solution2023 {
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
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }



    public static void main(String []args){
//        while (true) {
            System.out.println("Plesae input:");
            Scanner in = new Scanner(System.in);
            Solution2023 solution = new Solution2023();
            int[] a = {9,-5,-5,5,-5,-4,-6,6,-6};
            int[] b = {3,0,6,1,5};
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
        System.out.println(solution.intToRoman(1958));
    }
}