package leetcode;

public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
    public void insert(ListNode head, int data) {
        if (head.next == null) {
            head.next = new ListNode(data);
        }
        else insert(head.next,data);
    }
    public ListNode removeElements(ListNode head, int val) {         //203
        ListNode temp = new ListNode(-1);
        temp.next = head;
        ListNode p = temp;
        ListNode q = head;
        while(q!=null) {
            if(q.val == val) {
                p.next = q.next;
            }
            else {
                p = p.next;
            }
            q = q.next;
        }
        return temp.next;
    }
}
