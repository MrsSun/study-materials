package algorithm.linklist;

/**
 * https://leetcode.cn/problems/intersection-of-two-linked-lists/
 * 160. 相交链表
 *
 */
public class Question160 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA, b = headB;
        while (a != null && b != null){
            a = a.next;
            b = b.next;
        }
        while (a != null){
            headA = headA.next;
            a = a.next;
        }
        while (b != null){
            headB = headB.next;
            b = b.next;
        }
        while (headA != null && headB != null){
            if (headA == headB){
                return headA;
            }
            headA = headA.next;
            headB = headB.next;
        }
        return null;
    }
}
