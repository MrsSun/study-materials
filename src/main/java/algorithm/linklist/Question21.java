package algorithm.linklist;

/**
 21. 合并两个有序链表
 简单
 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 */
public class Question21 {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = new ListNode();
        ListNode head2 = head;
        while (list1 != null && list2 != null){
            if (list1.val <= list2.val){
                head.next = list1;
                list1 = list1.next;
                head = head.next;
            }else {
                head.next = list2;
                list2 = list2.next;
                head = head.next;
            }
        }
        if (list1 != null){
            head.next = list1;
        }
        if (list2 != null){
            head.next = list2;
        }
        return head2.next;
    }
}
