package leetcode.NewYear2022.MyList;

/**
 * Created by shaobin on 2022/1/17.
 */
public class ListNode {
    int val;
    ListNode next;
    ListNode (int x) {
        val = x;
        next = null;
    }
    ListNode (int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
