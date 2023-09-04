package leetcode;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class Solution {
    public int myAtoi(String str) {
        int ans = 0;
        int start = 0;
        for (start = 0;start < str.length();start++)
        {
            if (str.charAt(start) != ' ')break;
        }
        if (str.length() == 0 || (str.charAt(start) < '0' || str.charAt(start) > '9') && str.charAt(start) != '+' && str.charAt(start) != '-' && str.charAt(start) != ' ') return  ans;
        if (str.length() == 1 && str.charAt(0) >= '0' && str.charAt(0) <= '9')return str.charAt(0) - '0';
        if (str.length() == 1 && (str.charAt(0) <'0' || str.charAt(0) > '9'))return ans;
        if (str.charAt(start) == '-' && str.charAt(start + 1) >= '0' && str.charAt(start + 1) <= '9')
        {
            for (int i = start + 1;i < str.length();i++)
            {
                if (str.charAt(i) <'0' || str.charAt(i) >'9')return ans;
                if (ans < -214748364 || (ans == -214748364 && str.charAt(i) >= '8'))return -2147483648;
                else ans = ans * 10 - (str.charAt(i) - '0');
            }
        }
        else if (str.charAt(start) == '+' && str.charAt(start + 1) >= '0' && str.charAt(start + 1) <= '9')
        {
            for (int i = start + 1;i < str.length();i++)
            {
                if (str.charAt(i) <'0' || str.charAt(i) >'9')return ans;
                if (ans > 214748364 || (ans == 214748364) && str.charAt(i) >= '7')return 2147483647;
                else ans = ans * 10 + str.charAt(i) - '0';
            };
        }
        else if (str.charAt(start) >= '0' && str.charAt(start) <= '9')
        {
            for (int i = start;i < str.length();i++)
            {
                if (str.charAt(i) <'0' || str.charAt(i) >'9')return ans;
                if (ans > 214748364 || (ans == 214748364) && str.charAt(i) >= '7')return 2147483647;
                else ans = ans * 10 + str.charAt(i) - '0';
            }
        }
        return ans;
    }

    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }

    public int maxDepth(TreeNode root) {
        if (root == null)return 0;
        else {
            return 1+Math.max(maxDepth(root.left),maxDepth(root.right));
        }
    }

    public TreeNode invertTree(TreeNode root) {    //��ת��
        if (root == null) return null;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    public void moveZeroes(int[] nums) {        //��������0�Ƶ��������˳�򲻱�
        int i,j,t;
        for (i = 0;i < nums.length;i++) {
            for (j = 0;j < nums.length - 1;j++) {
                if (nums[j] == 0) {
                    t = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = t;
                }
            }
        }
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {       //�Ƚ������������Ƿ����
        if (p != null && q != null) {
            if (p.val != q.val) return false;
            return isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
        }
        else if (p == null && q == null) {
            return true;
        }
        return false;
    }

    public boolean isAnagram(String s, String t) {               //���ַ����Ƿ�����ͬ�ַ����
        if (s.length() != t.length()) return false;
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        Arrays.sort(sArr);
        Arrays.sort(tArr);
        return String.valueOf(sArr).equals(String.valueOf(tArr));
    }

    public boolean containsDuplicate(int[] nums) {                         //�Ƿ������ͬ����
//        Arrays.sort(nums);
//        for (int i = 1;i < nums.length;i++) {
//            if (nums[i] == nums[i - 1])return false;
//        }
//        return true;
        HashMap<Integer,Integer> hashMap = new HashMap<Integer,Integer>();
        for (int i = 0; i < nums.length; i++) {
            if(hashMap.containsKey(nums[i]))return true;
            hashMap.put(nums[i],1);
        }
        return false;
    }

    public int majorityElement(int[] nums) {                                //�Ҹ�����������һ�������
        HashMap<Integer,Integer> hashMap = new HashMap<Integer,Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (hashMap.containsKey(nums[i])) hashMap.put(nums[i] , hashMap.get(nums[i]) + 1);
            else hashMap.put(nums[i] , 1);
//            System.out.println(nums[i]);
//            System.out.println(hashMap.get(nums[i]));
//            System.out.println(nums.length / 2);
            if (hashMap.get(nums[i]) >= (nums.length + 1) / 2)return nums[i];
        }
        return 0;
    }

    public ListNode reverseList(ListNode head) {           //����ת
        if (head == null || head.next == null) return head;
        ListNode pre = head;
        ListNode p = head.next;
        pre.next = null;
        ListNode t;
        while (p != null) {
            t = p.next;
            p.next = pre;
            pre = p;
            p = t;
        }
        return pre;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {          //�ҳ������ڵ����������еĹ����ڵ�
        if(root==null || p==null || q==null) return null;
        if(Math.max(p.val, q.val) < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        else if(Math.min(p.val, q.val) > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        else return root;
    }

    public int hammingWeight(int n) {                        //����32λ�޷���������ת��Ϊ�������ж��ٸ�1
        System.out.println(n);
        int sum = 0;
        if (n == -2147483648) return 1;
        else if (n - 2147483647 - 2147483647 == 1)return 32;
        else if (n < 0) {
            sum++;
            n = n + 2147483647 + 1;
        }
        while (n != 0) {
            if (n % 2 == 1)sum++;
//            System.out.println(sum);
            n = n / 2;
        }
        return  sum;
    }

    public ListNode oddEvenList(ListNode head) {          //��ż��
        //����Ϸ����ж�
        if (head == null) {
            return null;
        } else if (head.next == null) {
            return head;
        }
        ListNode odd = new ListNode(0);  //�����������������λ�ýڵ�
        ListNode oddCurr = odd;          //�������������β�ڵ�
        ListNode even = new ListNode(0); //ż�����������ż��λ�ýڵ�
        ListNode evenCurr = even;        //ż�����������β�ڵ�
        //�ֱ��������������ż������
        ListNode tmp = head;
        int counter = 0;
        while (tmp != null) {
            counter++;
            if (counter % 2 != 0) {
                oddCurr.next = new ListNode(tmp.val);
                oddCurr = oddCurr.next;
            } else {
                evenCurr.next = new ListNode(tmp.val);
                evenCurr = evenCurr.next;
            }
            tmp = tmp.next;
        }
        oddCurr.next = even.next; //ż��������������������
        return odd.next;
    }

    public int climbStairs(int n) {                          //leetcode70   ��¥��
        if (n == 0)return 0;
        if (n == 1)return 1;
        if (n == 2)return 2;
        int a1 = 1;
        int a2 = 2;
        int a3 = 0;
        for (int i = 3;i <= n;i++) {
            a3 = a1 + a2;
            a1 = a2;
            a2 = a3;
        }
        return a3;
    }

    public ListNode deleteDuplicates(ListNode head) {                  //����������ɾ���ظ����
        if (head == null) return null;
        if (head.next == null) return head;
        ListNode temp = head;
        while (head.next != null) {
            if (head.val == head.next.val) {
                head.next = head.next.next;
            }
            else head = head.next;
        }
        return temp;
    }

    public boolean isPowerOfThree(int n) {         //�ж��ǲ���3��ڤ��
        double ans = Math.log10(n) / Math.log10(3);
        return ans - (int)ans == 0;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {             //������������ϲ�
        if (l1 == null && l2 == null) return null;
        else if (l1 == null) return l2;
        else if (l2 == null) return l1;
        ListNode temp = new ListNode(0);
        ListNode ans = temp;
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                temp.next = l2;
                return ans.next;
            }
            else if (l2 == null) {
                temp.next = l1;
                return ans.next;
            }
            else {
                if (l1.val > l2.val) {
                    temp.next = new ListNode(l2.val);
                    temp = temp.next;
                    l2 = l2.next;
                }
                else {
                    temp.next = new ListNode(l1.val);
                    temp = temp.next;
                    l1 = l1.next;
                }
            }
        }
        return ans;
    }

    public int trailingZeroes(int n) {
        if (n == 0) return 0;
        int ans = 0;
        while (n / 5 != 0) {
            n /= 5;
            ans += n;
        }
        return ans;
    }

    public int arrangeCoins(int n) {
        int ans = 0;
        while (n - ans > 0) {
            ans++;
            n -= ans;
        }
        return ans;
    }

    public String addStrings(String num1, String num2) {
        StringBuffer ans = new StringBuffer();
        int temp = 0;
        int mid = 0;
        int length = (num1.length() > num2.length()) ? num1.length() : num2.length();
        for (int i = 1; i <= length; i++) {
            if (i > num1.length()) {
                mid = num2.charAt(num2.length() - i) + temp - 48;
                if (mid >= 10) {
                    temp = 1;
                    mid -= 10;
                }
                else temp = 0;
                ans.append(String.valueOf(mid));
            }
            else if (i > num2.length()) {
                mid = num1.charAt(num1.length() - i) + temp - 48;
                if (mid >= 10) {
                    temp = 1;
                    mid -= 10;
                }
                else temp = 0;
                ans.append(String.valueOf(mid));
            }
            else {
                mid = num1.charAt(num1.length() - i) + num2.charAt(num2.length() - i) + temp - 96;
                if (mid >= 10) {
                    temp = 1;
                    mid -= 10;
                }
                else temp = 0;
                ans.append(String.valueOf(mid));
            }
        }
        if (temp != 0) {
            ans.append(String.valueOf(temp));
        }
        return ans.reverse().toString();
    }

    public int[] plusOne(int[] digits) {
        int t = 1;
        int[] ans;
        for (int i = digits.length - 1; i > 0 ; i--) {
            digits[i] += t;
            if (digits[i] >= 10) {
                t = 1;
                digits[i] -= 10;
            }
            else {
                t = 0;
            }
        }
        if (t == 1) {
            ans = new int[digits.length + 1];
            ans[0] = 1;
            for (int i = 1; i < ans.length; i++) {
                ans[i] = digits[i - 1];
            }
        }
        else ans = digits;
        return ans;
    }

    public int hammingDistance(int x, int y) {       //461
        int dist = 0;
        int val = x ^ y;
        while (val != 0) {
            dist++;
            val &= val - 1;
        }
        return dist;
    }

    public String[] findWords(String[] words) {     //500
        int[] a = {2,3,3,2,1,2,2,2,1,2,2,2,3,3,1,1,1,1,2,1,1,3,1,3,1,3};
        String[] mid = new String[words.length];
        int all = 0;
        for (int i = 0; i < words.length; i++) {
            String temp = words[i].toLowerCase();
            int sum = 0;
            for (int j = 0; j < temp.length(); j++) {
                sum += a[temp.charAt(j) - 97];
            }
            if (a[temp.charAt(0) - 97] == 1 && sum == temp.length()) {
                mid[all++] = words[i];
            }
            else if (a[temp.charAt(0) - 97] == 2 && temp.length() * 2 == sum) {
                mid[all++] = words[i];
            }
            else if (temp.length() * 3 == sum) {
                mid[all++] = words[i];
            }
        }
        String[] answers = new String[all];
        for (int i = 0; i < all; i++) {
            answers[i] = mid[i];
        }
        return answers;
    }

    public int findComplement(int num) {      //476
        int temp = num;
        int mask = 1;
        while(temp != 0){
            temp >>= 1;
            mask <<= 1;
        }
        return ((mask - 1) ^ num);
//        return ~num & ((Integer.highestOneBit(num) << 1) - 1);
    }

    public int[] nextGreaterElement(int[] findNums, int[] nums) {       //496
        int[] temp = new int[nums.length];
        int[] answer = new int[findNums.length];
        for (int i = 0; i < nums.length; i++) {
            int j = i + 1;
            for (j = i + 1; j < nums.length; j++) {
                if (nums[i] < nums[j]) {
                    temp[i] = nums[j];
                    i++;
                    j = i;
                }
            }
            if (j == nums.length) {
                temp[i] = -1;
            }
        }
        for (int i = 0; i < findNums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (findNums[i] == nums[j]) {
                    answer[i] = temp[j];
                    i++;
                    if (i == findNums.length)break;
                    j = -1;
                }
            }
        }
        return answer;
    }

    public List<String> fizzBuzz(int n) {
        List<String> answer = new LinkedList<String >();
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                answer.add("FizzBuzz");
            }
            else if (i % 3 == 0) {
                answer.add("Fizz");
            }
            else if (i % 5 == 0) {
                answer.add("Buzz");
            }
            else {
                answer.add(String.valueOf(i));
            }
        }
        return answer;
    }

    public int findMaxConsecutiveOnes(int[] nums) {       //485
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(-1);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                stack.push(i);
            }
        }
        int large = nums.length;
        int ans = 0;
        while (!stack.empty()) {
            int temp = stack.pop();
            if (ans < (large - temp - 1)) {
                ans = large - temp - 1;
                large = temp;
            }
            else {
                large = temp;
            }
        }
        return ans;
    }

    public List<Integer> findDisappearedNumbers(int[] nums) {       //448
        List<Integer> answer = new LinkedList<Integer>();
        for (int i = 1; i <= nums.length; i++) {
            answer.add(i);
        }
        for (int i = 0; i < nums.length; i++) {
            if (answer.contains(nums[i])) {
                answer.remove(Integer.valueOf(nums[i]));
            }
        }
        return answer;
    }

//    public int findContentChildren(int[] g, int[] s) {        //455
//        for (int i = 0; i < g.length; i++) {
//            for (int j = 0; j < g.length - 1; j++) {
//                if (g[j] < g[j + 1]) {
//                    int t = g[j];
//                    g[j] = g[j + 1];
//                    g[j + 1] = t;
//                }
//            }
//        }
//        for (int i = 0; i < s.length; i++) {
//            for (int j = 0; j < s.length - 1; j++) {
//                if (s[j] < s[j + 1]) {
//                    int t = s[j];
//                    s[j] = s[j + 1];
//                    s[j + 1] = t;
//                }
//            }
//        }
//        int i = 0,j = 0;
//        int ans = 0;
//        while (i < g.length && j < s.length) {
//            if (g[i] <= s[j]) {
//                ans++;
//                i++;
//                j++;
//            }
//            else if (g[i] > s[j]) {
//                i++;
//            }
//        }
//        return ans;
//    }

    public int findContentChildren(int[] g, int[] s) {        //455
        Sort sort = new Sort();
        sort.quickSort(g);
        sort.quickSort(s);
        int i = g.length - 1,j = s.length - 1;
        int ans = 0;
        while (i >= 0 && j >= 0) {
            if (g[i] <= s[j]) {
                System.out.println("i:" + i +",j:" + j + ",gi:" + g[i] + ",sj:" + s[j]);
                ans++;
                i--;
                j--;
            }
            else if (g[i] > s[j]) {
                i--;
            }
        }
        return ans;
    }

    public String convertToBase7(int num) {      //504
        String ans = new String();
        int i = 0;
        int temp = num;
        if (num < 0) {
            temp = 0 - num;
        }
        while (temp != 0) {
            ans = (temp % 7) + ans;
            temp /= 7;
        }
        if (num < 0) {
            ans = "-" + ans;
        }
        return ans;
    }

    public int[] intersection(int[] nums1, int[] nums2) {        //349
//        Sort sort = new Sort();
//        sort.quickSort(nums1);
//        sort.quickSort(nums2);
//        int[] temp = new int[nums1.length > nums2.length ? nums2.length : nums1.length];
//        if (nums1.length == 0 || nums2.length == 0) return temp;
//        int i = 0, j = 0;
//        int count = 0;
//        while (i != nums1.length || j != nums2.length) {
//            if (i == nums1.length - 1 && j == nums2.length - 1) {
//                if (nums1[i] == nums2[j]) {
//                    if (count == 0) {
//                        temp[count++] = nums1[i];
//                    }
//                    else if(temp[count - 1] != nums1[i]) {
//                        temp[count++] = nums1[i];
//                    }
//                }
//                i++;
//                j++;
//            }
//            else if (nums1[i] == nums2[j]) {
//                if (count == 0) {
//                    temp[count++] = nums1[i];
//                }
//                else if(temp[count - 1] != nums1[i]) {
//                    temp[count++] = nums1[i];
//                }
//                if (i != nums1.length - 1 && j != nums2.length - 1) {
//                    i++;
//                    j++;
//                }
//                else break;
//            }
//            else if (nums1[i] > nums2[j]) {
//                if (j != nums2.length - 1) {
//                    j++;
//                }
//                else break;
//            }
//            else if (nums1[i] < nums2[j]) {
//                if (i != nums1.length - 1) {
//                    i++;
//                }
//                else break;
//            }
//        }
//        int[] ans = new int[count];
//        for (int k = 0; k < count; k++) {
//            ans[k] = temp[k];
//        }
//        return ans;
        if (nums1.length == 0 || nums2.length == 0) return new int[0];
        Set<Integer> a = new TreeSet<Integer>();
        Set<Integer> b = new TreeSet<Integer>();
        for (int i = 0; i < nums1.length; i++) {
            a.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            b.add(nums2[i]);
        }
        Set<Integer> c = new TreeSet<Integer>();
        Iterator<Integer> aiter = a.iterator();
        Iterator<Integer> biter = b.iterator();
        Integer ta = aiter.next();
        Integer tb = biter.next();
        while (true) {
            if (ta.equals(tb)) {
                c.add(ta);
                if (aiter.hasNext() && biter.hasNext()) {
                    ta = aiter.next();
                    tb = biter.next();
                }
                else break;
            }
            else if (ta.compareTo(tb) > 0 && biter.hasNext()) {
                tb = biter.next();
            }
            else if (ta.compareTo(tb) < 0 && aiter.hasNext()) {
                ta = aiter.next();
            }
            else {
                break;
            }
        }
        int[] ans = new int[c.size()];
        int i = 0;
        Iterator<Integer> citer = c.iterator();
        while (citer.hasNext()) {
            ans[i++] = citer.next();
        }
        return ans;
    }

    public boolean canConstruct(String ransomNote, String magazine) {       //383
        int[] temp = new int[26];
        for (int i = 0; i < magazine.length(); i++) {
            temp[magazine.charAt(i) - 97]++;
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            temp[ransomNote.charAt(i) - 97]--;
            if (temp[ransomNote.charAt(i) - 97] < 0) {
                return false;
            }
        }
        return true;
    }

    public boolean detectCapitalUse(String word) {      //520
        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) >= 65 && word.charAt(i) <= 90) {
                count++;
            }
        }
        if (count == word.length() || (count == 1 && word.charAt(0) <= 90) || count ==0) {
            return true;
        }
        else {
            return false;
        }
    }

    public int[] twoSum(int[] numbers, int target) {      //167
        int start = 0;
        int end = numbers.length - 1;
        int[] ans = new int[2];
        while (numbers[start] + numbers[end] != target) {
            if (start == end) {
                return ans;
            }
            else if (numbers[start] + numbers[end] > target) {
                end--;
                start = 0;
            }
            else {
                start++;
            }
        }
        ans[0] = start + 1;
        ans[1] = end + 1;
        return ans;
    }

    public int maxProfit(int[] prices) {
        //121
//        int ans = 0;
//        if (prices.length < 2) {
//            return ans;
//        }
//        int min = prices[0];
//        for (int i = 1; i < prices.length; i++) {
//            min = Math.min(prices[i], min);
//            ans = Math.max(ans, (prices[i] - min));
//        }
//        return ans;
        //122
//        int sum = 0;
//        for (int i = 1; i < prices.length; ++i) {
//            if (prices[i] > prices[i - 1]) {
//                sum += prices[i] - prices[i - 1];
//            }
//        }
//        return sum;
        //123
        if (prices.length < 2) {
            return 0;
        }
        int[] ans_right = new int[prices.length];
        int[] ans_left = new int[prices.length];
        int min = prices[0];
        ans_left[0] = 0;
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            ans_left[i] = Math.max(ans_left[i - 1], prices[i] - min);
        }
        int max = prices[prices.length - 1];
        ans_right[prices.length - 1] = 0;
        for (int i = prices.length - 2; i >= 0; i--) {
            max = Math.max(max, prices[i]);
            ans_right[i] = Math.max(ans_right[i + 1], max - prices[i]);
        }
        int ans = 0;
        for (int i = 0; i < prices.length; i++) {
            ans = (ans < (ans_left[i] + ans_right[i])) ? (ans_left[i] + ans_right[i]) : ans;
        }
        return ans;
        //124

    }

    public int longestPalindrome(String s) {
        int ans = 0;
        int[] v = new int[52];
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= 97) {
                v[s.charAt(i) - 97]++;
            }
            else {
                v[s.charAt(i) - 39]++;
            }
        }
        int flag = 0;
        for (int x:v) {
            if (x % 2 == 0) {
                ans += x;
            }
            else {
                flag = 1;
            }
        }
        return ans + flag;
    }

    public void deleteNode(ListNode node) {       //237
        if (node == null || node.next == null) {
            return;
        }
        node.val = node.next.val;
        node.next = node.next.next;
    }

    public int firstUniqChar(String s) {          //387
        int[] v = new int[26];
        for (int i = 0; i < s.length(); i++) {
            v[s.charAt(i) - 97]++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (v[s.charAt(i) - 97] == 1) {
                return i;
            }
        }
        return -1;
    }

    public int minMoves(int[] nums) {
        int sum = 0;
        int min = nums[0];
        if (nums.length < 2) {
            return  0;
        }
        else {
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                if (min > nums[i]) {
                    min = nums[i];
                }
            }
            return sum - min * nums.length;
        }
    }

//    public int[] plusOne(int[] digits) {
//        int t = 1;
//        int[] ans;
//        for (int i = digits.length - 1; i >= 0 ; i--) {
//            digits[i] += t;
//            if (digits[i] >= 10) {
//                t = 1;
//                digits[i] -= 10;
//            }
//            else {
//                t = 0;
//            }
//        }
//        if (t != 0) {
//            ans = new int[digits.length + 1];
//            ans[0] = 1;
//            for (int i = 1; i < ans.length; i++) {
//                ans[i] = digits[i - 1];
//            }
//            return ans;
//        }
//        else return digits;
//    }

    public int sumOfLeftLeaves(TreeNode root) {
        int ans = 0;
        if (root == null) return ans;
        if ((root.left == null && root.right == null)) return ans;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode parents = root;
        TreeNode node = root;
        while (node != null || !stack.empty()) {
            if (node.right != null) {
                stack.push(node.right);
            }
            else if (node.left == null && node.right == null) {
                if (parents.left != null && parents.left.equals(node)) {
                    System.out.println("now:" + node.val);
                    ans += node.val;
                }
            }
            if (node.left == null && !stack.empty()) {
                parents = node;
                node = (TreeNode) stack.pop();
            }
            else {
                parents = node;
                node = node.left;
            }
        }
        return ans;
    }

//    public List<Integer> findDisappearedNumbers(int[] nums) {
//        List<Integer> anslist = new LinkedList<Integer>();
//        for (int i = 0; i < nums.length; ) {
//            if (nums[i] == 0) {
//                i++;
//                continue;
//            }
//            if (nums[i] != i + 1 && nums[i] != nums[nums[i] - 1]) {
//                int t = nums[i];
//                nums[i] = nums[t - 1];
//                nums[t - 1] = t;
//            }
//            else if (nums[i] != i + 1 && nums[i] == nums[nums[i] - 1]) {
//                nums[i] = 0;
//            }
//            else {
//                i++;
//            }
//        }
//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i] == 0) {
//                anslist.add(i + 1);
//            }
//        }
//        return anslist;
//    }

    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {      //136
            ans = ans ^ nums[i];
        }
        return ans;
    }

    public int getSum(int a, int b) {       //371
        if (a >= 0) {
            for (int i = 0; i < a; i++) {
                b++;
            }
        }
        else {
            for (int i = 0; i > a; i--) {
                b--;
            }
        }
        return b;
    }

    public char findTheDifference(String s, String t) {       //389
        char ans = 0;
        int[] a = new int[26];
        for (int i = 0; i < t.length(); i++) {
            a[t.charAt(i) - 97]++;
        }
        for (int i = 0; i < s.length(); i++) {
            a[s.charAt(i) - 97]--;
        }
        for (int i = 0; i < a.length; i++) {
            if (a[i] == 1) {
                ans = (char)(i + 97);
            }
        }
        return ans;
    }

    public int[] constructRectangle(int area) {         //492
        int[] ans = new int[2];
        ans[0] = (int)Math.sqrt(area);
        ans[1] = ans[0];
        while (area % ans[1] != 0) {
            ans[1]--;
        }
        ans[0] = area / ans[1];
        return ans;
    }

    public String[] findRelativeRanks(int[] nums) {        //506
        String[] ans = new String[nums.length];
        int[] temp = new int[nums.length];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = i;
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length - 1; j++) {
                if (nums[j] < nums[j + 1]) {
                    int t = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = t;
                    t = temp[j];
                    temp[j] = temp[j + 1];
                    temp[j + 1] = t;
                }
            }
        }
        for (int i = 0; i < temp.length; i++) {
            if (i == 0) {
                ans[temp[i]] = "Gold Medal";
            }
            else if (i == 1) {
                ans[temp[i]] = "Silver Medal";
            }
            else if (i == 2) {
                ans[temp[i]] = "Bronze Medal";
            }
            else {
                ans[temp[i]] = String.valueOf(i + 1);
            }
        }
        return ans;
    }

    public String toHex(int num) {       //405
        if (num == 0) {
            return "0";
        }
        long tnum = num;
        if (num < 0) {
            tnum = 0x0ffffffffL + num + 1;
        }
        String ans = "";
        while (tnum != 0) {
            long t = tnum % 16;
            String temp = null;
            if (t < 10) {
                temp = String.valueOf(t);
            }
            else switch ((int) t) {
                case 10 : temp = "a"; break;
                case 11 : temp = "b"; break;
                case 12 : temp = "c"; break;
                case 13 : temp = "d"; break;
                case 14 : temp = "e"; break;
                case 15 : temp = "f"; break;
            }
            ans = temp + ans;
            tnum /= 16;
        }
        return ans;
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {        //107
        if (root == null) return new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        int[] depth = new int[1000];
        int i = 0;
        depth[i++] = 1;
        int j = 0;
        queue.offer(root);
        while (!queue.isEmpty()) {
            j++;
            TreeNode temp = queue.poll();
            if (temp.right != null) {
                queue.add(temp.right);
                depth[i]++;
            }
            if (temp.left != null) {
                queue.add(temp.left);
                depth[i]++;
            }
            stack.push(temp);
            if (j == depth[i - 1]) {
                j = 0;
                i++;
            }
        }
        j = i - 2;
        List<List<Integer>> ans = new LinkedList<>();
        for (int k = 0; k < i - 1; k++) {
            List<Integer> lists = new LinkedList<>();
            for (int l = 0; l < depth[j]; l++) {
                lists.add(stack.pop().val);
            }
            j--;
            ans.add(lists);
        }
        return ans;
    }

    public int findBottomLeftValue(TreeNode root) {        //513
        int ans = root.val;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int[] floor = new int[1000];    //��¼ÿ��ڵ����
        for (int x : floor) x = 0;
        floor[0] = 1;                   //��һ��ֻ��rootһ���ڵ�
        int num = 0;                    //������¼ɨ��ò�ڼ����ڵ�
        int floor_num = 1;              //����
        while (!queue.isEmpty()) {
            num++;
            TreeNode temp = queue.poll();
            if (num == 1) {
                ans = temp.val;
            }
            if (temp.left != null) {
                queue.add(temp.left);
                floor[floor_num]++;
            }
            if (temp.right != null) {
                queue.add(temp.right);
                floor[floor_num]++;
            }
            if (num == floor[floor_num - 1]) {
                num = 0;
                floor_num++;
            }
        }
        return ans;
    }

    public List<Integer> largestValues(TreeNode root) {        //515
        List<Integer> ans = new LinkedList<>();
        if (root == null) return ans;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int[] floor = new int[1000];    //��¼ÿ��ڵ����
        for (int x : floor) x = 0;
        floor[0] = 1;                   //��һ��ֻ��rootһ���ڵ�
        int num = 0;                    //������¼ɨ��ò�ڼ����ڵ�
        int floor_num = 1;              //����
        int max = root.val;             //������¼�ò�������
        while (!queue.isEmpty()) {
            num++;
            TreeNode temp = queue.poll();
            if (num == 1) {
                max = temp.val;
            }
            if (temp.left != null) {
                queue.add(temp.left);
                floor[floor_num]++;
            }
            if (temp.right != null) {
                queue.add(temp.right);
                floor[floor_num]++;
            }
            if (max < temp.val) {
                max = temp.val;
            }
            if (num == floor[floor_num - 1]) {
                num = 0;
                floor_num++;
                ans.add(max);
            }
        }
        return ans;
    }



    public static void main(String []args){
        while (true) {
            System.out.println("Plesae input:");
            Scanner in = new Scanner(System.in);
            Solution solution = new Solution();
//            String a = in.nextLine();
//            String b = in.nextLine();
//            System.out.println(solution.addStrings(a,b));
//            String[] words = {"abdfs","cccd","a","qwwewm", "qz","wq","asdddafadsfa","adfadfadfdassfawde"};
//            int[] a = {4,1,2};
//            int[] b = {1,3,4,2};
//            int[] aa = {2,4};
//            int[] bb = {1,2,3,4};
//            int[] d = {1,5,2,6,2,8,4,6};
//            List<Integer> ans = solution.findDisappearedNumbers(d);
//            for (Integer x:ans
//                 ) {
//                System.out.println(x + "     ");
//            }
            int[] a = {6,1,3,2,4,7};
            int[] b = {1,1};
////            System.out.println(solution.intersection(a,b));
//            int[] ans = solution.intersection(a,b);
//            for (Integer x:ans) {
//                System.out.println(x + "     ");
//            }
            int c = in.nextInt();
            System.out.println(solution.toHex(c));
            int aaa = in.nextInt();
        }

    }
}
