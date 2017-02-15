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
}
