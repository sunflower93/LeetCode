package leetcode.NewYear2022.MyList;

import java.util.HashMap;

/**
 * Created by shaobin on 2022/1/17.
 */
public class Solution {
    // 复制带随机指针的链表，深拷贝
    public Node copyRandomList(Node head) {
        Node ans = new Node(0);
        Node temp = ans;
        Node iter = head;
        HashMap<Node, Node> map = new HashMap<>();
        while (iter != null) {
            int v = iter.val;
            Node t = new Node(v);
            map.put(iter, t);
            temp.next = t;
            temp = temp.next;
            iter = iter.next;
        }
        temp = ans.next;
        while (head != null) {
            Node r = head.random;
            temp.random = map.get(r);
            temp = temp.next;
            head = head.next;
        }
        return ans.next;
    }

    //环形链表，判断链表是否有环
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
            if (fast == null) break;
            fast = fast.next;
            if (slow == fast) return true;
        }
        return false;
    }

    //合并两个链表，按正序排列
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode ans = new ListNode(0);
        ListNode temp = ans;
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                temp.next = l2;
                return ans.next;
            }
            if (l2 == null) {
                temp.next = l1;
                return ans.next;
            }
            if (l1.val < l2.val) {
                temp.next = l1;
                temp = temp.next;
                l1 = l1.next;
            }
            else {
                temp.next = l2;
                temp = temp.next;
                l2 = l2.next;
            }
        }
        System.out.println(1);
        return ans.next;
    }
    //寻找链表的中点
    public ListNode getMiddle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    //排序链表
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode middle =getMiddle(head);
        ListNode next = middle.next;
        middle.next = null;
        return mergeTwoLists(sortList(head),sortList(next));
    }

    // 获取链表长度
    public int getListNodeLength(ListNode head) {
        int ans = 0;
        while (head != null) {
            ans++;
            head = head.next;
        }
        return ans;
    }
    // 判断两个链表相交
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA = getListNodeLength(headA);
        int lenB = getListNodeLength(headB);
        while (lenA > lenB) {
            headA = headA.next;
            lenA--;
        }
        while (lenB > lenA) {
            headB = headB.next;
            lenB--;
        }
        while (headA != null) {
            if (headA == headB) return headA;
            headA = headA.next;
            headB = headB.next;
        }
        return headA;
    }

    //翻转链表
    public ListNode reverseList(ListNode head) {
        ListNode root = null;
        ListNode temp = head;
        while (temp != null) {
            ListNode next = temp.next;
            temp.next = root;
            root = temp;
            temp = next;
        }
        return root;
    }

    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }
    //回文链表
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode fast = head;
        ListNode slow = head;
        while(fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        slow = reverse(slow.next);
        while(slow != null) {
            if (head.val != slow.val) {
                return false;
            }
            head = head.next;
            slow = slow.next;
        }
        return true;
    }

    //删除节点node
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    //奇偶链表，先奇位节点后偶位节点，其他相对顺序不变，时间O(1)，空间O(n)
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode odd = head;
        ListNode even = head.next;
        ListNode oddTemp = odd;
        ListNode evenTemp = even;
        while (oddTemp.next != null && evenTemp.next != null) {
            oddTemp.next = oddTemp.next.next;
            evenTemp.next = evenTemp.next.next;
            oddTemp = oddTemp.next;
            evenTemp = evenTemp.next;
        }
        oddTemp.next = even;
        return odd;
    }

    public static void main(String[] args) {
        int[] a = {1,1,2,1};
        ListNode head = new ListNode(0);
        ListNode temp = head;
        for (int x : a) {
            ListNode t = new ListNode(x);
            temp.next = t;
            temp = temp.next;
        }
        Solution solution = new Solution();
        System.out.println(solution.isPalindrome(head.next));
    }
}
