import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;


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

    public TreeNode invertTree(TreeNode root) {    //反转树
        if (root == null) return null;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    public void moveZeroes(int[] nums) {        //将数组中0移到最后，其他顺序不变
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

    public boolean isSameTree(TreeNode p, TreeNode q) {       //比较两个二叉树是否相等
        if (p != null && q != null) {
            if (p.val != q.val) return false;
            return isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
        }
        else if (p == null && q == null) {
            return true;
        }
        return false;
    }

    public boolean isAnagram(String s, String t) {               //两字符串是否有相同字符组成
        if (s.length() != t.length()) return false;
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        Arrays.sort(sArr);
        Arrays.sort(tArr);
        return String.valueOf(sArr).equals(String.valueOf(tArr));
    }

    public boolean containsDuplicate(int[] nums) {                         //是否存在相同整数
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

    public int majorityElement(int[] nums) {                                //找个数大于数组一半的众数
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


    public ListNode reverseList(ListNode head) {           //链表反转
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

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {          //找出两个节点在搜索树中的公共节点
        if(root==null || p==null || q==null) return null;
        if(Math.max(p.val, q.val) < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        else if(Math.min(p.val, q.val) > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        else return root;
    }

    public int hammingWeight(int n) {                        //计算32位无符号整形数转化为二进制有多少个1
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

    public ListNode oddEvenList(ListNode head) {          //奇偶链
        //输入合法性判断
        if (head == null) {
            return null;
        } else if (head.next == null) {
            return head;
        }
        ListNode odd = new ListNode(0);  //奇数链表：仅存放奇数位置节点
        ListNode oddCurr = odd;          //奇数链表的链表尾节点
        ListNode even = new ListNode(0); //偶数链表：仅存放偶数位置节点
        ListNode evenCurr = even;        //偶数链表的链表尾节点
        //分别生成奇数链表和偶数链表
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
        oddCurr.next = even.next; //偶数链表接在奇数链表后面
        return odd.next;
    }

    public int climbStairs(int n) {                          //leetcode70   爬楼梯
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

    public ListNode deleteDuplicates(ListNode head) {                  //已排序链表删除重复结点
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

    public boolean isPowerOfThree(int n) {         //判断是不是3的冥数
        double ans = Math.log10(n) / Math.log10(3);
        return ans - (int)ans == 0;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {             //已排序两链表合并
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

    public static void main(String []args){
        while (true) {
            System.out.println("Plesae input:");
            Scanner in = new Scanner(System.in);
            Solution solution = new Solution();
            String a = in.nextLine();
            String b = in.nextLine();
            System.out.println(solution.addStrings(a,b));
        }

    }
}
