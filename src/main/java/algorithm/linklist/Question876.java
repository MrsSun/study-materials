package algorithm.linklist;

/**
 876. 链表的中间结点
 简单
 相关标签
 相关企业
 给你单链表的头结点 head ，请你找出并返回链表的中间结点。

 如果有两个中间结点，则返回第二个中间结点。
 */
public class Question876 {
    public ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        if (fast != null && fast.next != null){
            slow = slow.next;
        }
        return slow;
    }
}
