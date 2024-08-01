package algorithm.linklist;

/**
 https://leetcode.cn/problems/reverse-linked-list/
 */
public class Question206 {

    public ListNode reverseList(ListNode head) {
        ListNode curNode = head, preNode = null;
        while (curNode != null){
            ListNode next = curNode.next;
            curNode.next = preNode;
            preNode = curNode;
            curNode = next;
        }
        return preNode;
    }

}
