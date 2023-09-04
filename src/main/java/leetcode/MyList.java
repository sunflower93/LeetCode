package leetcode;

/**
 * Created by ShaoBin on 2016/3/9.
 */
public class MyList {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        int[] array = {12, 76, 35, 22, 16, 48, 90, 46, 9, 40};
//        int[] array1 = {12, 76, 3, 22, 16, 48, 90, 46, 9, 40};
//        int[] array11 = {1, 1, 2, 3, 3, 4, 5, 5, 5, 5};
//        ListNode list = new ListNode(array[0]);   //����������
//        ListNode list1 = new ListNode(array1[0]);
//        ListNode list11 = new ListNode(array11[0]);
//        for (int i = 1; i < array.length; i++) {
//            list.insert(list, array[i]);       //��������в�������
//        }
//        for (int i = 1; i < array1.length; i++) {
//            list1.insert(list1, array1[i]);       //��������в�������
//        }
//        for (int i = 1; i < array11.length; i++) {
//            list11.insert(list11, array11[i]);       //��������в�������
//        }
//        ListNode l = list;
//        ListNode ll = list1;
//        ListNode lll = list11;
//        while (list != null) {
//            System.out.print(list.val + "-");
//            list = list.next;
//        }
//        System.out.println();
//        while (list1 != null) {
//            System.out.print(list1.val + "-");
//            list1 = list1.next;
//        }
//        ListNode list2 = solution.reverseList(l);
//        System.out.println();
//        while (list2 != null) {
//            System.out.print(list2.val + "-");
//            list2 = list2.next;
//        }
//        ListNode list3 = solution.oddEvenList(ll);
//        System.out.println();
//        while (list3 != null) {
//            System.out.print(list3.val + "-");
//            list3 = list3.next;
//        }
//        ListNode list4 = solution.deleteDuplicates(lll);
//        System.out.println();
//        while (list4 != null) {
//            System.out.print(list4.val + "-");
//            list4 = list4.next;
//        }
        int[] arrays1 = {12, 16, 35, 42, 56, 68, 93};
        int[] arrays2 = {22, 36, 38, 42, 46, 48, 60, 66, 91, 94};
        ListNode sortlist1 = new ListNode(arrays1[0]);
        ListNode sortlist2 = new ListNode(arrays2[0]);
        for (int i = 1; i < arrays1.length; i++) {
            sortlist1.insert(sortlist1, arrays1[i]);       //��������в�������
        }
        for (int i = 1; i < arrays2.length; i++) {
            sortlist2.insert(sortlist2, arrays2[i]);       //��������в�������
        }
        ListNode l1 = sortlist1;
        ListNode l2 = sortlist2;
        while (l1 != null) {
            System.out.print(l1.val + "-");
            l1 = l1.next;
        }
        System.out.println();
        while (l2 != null) {
            System.out.print(l2.val + "-");
            l2 = l2.next;
        }
        System.out.println();
        ListNode anslist = solution.mergeTwoLists(sortlist1, sortlist2);
        while (anslist != null) {
            System.out.print(anslist.val + "-");
            anslist = anslist.next;
        }
        System.out.println();
    }
}
