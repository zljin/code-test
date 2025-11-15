package com.zljin.cts.linklist;

/**
 * 反转链表
 */
public class S206 {

    public static void main(String[] args) {
        S206 s206 = new S206();
    }

    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while(cur.next!=null){
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }

    public static class ListNode{
        int val;
        ListNode next;
    }
}
