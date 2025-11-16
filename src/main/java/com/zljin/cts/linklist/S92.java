package com.zljin.cts.linklist;

/**
 * 反转链表II
 * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right
 * 请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表
 *
 * 解题思路：
 *
 * 切割成3组
 *
 *
 * 可以反复学习
 *
 */
public class S92 {
    public static void main(String[] args) {

    }
    //将[left,right]区间直接通过reverseList,然后在插回去
    //dummy ->1->2->3->4->5
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode();
        dummy.next = head;

        ListNode pre = dummy;
        //1. 获取left前一个节点
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }

        //2. 从pre节点，再走right-left+1步，来到right节点
        ListNode rightNode = pre;
        for (int i = 0; i < right - left + 1; i++) {
            rightNode = rightNode.next;
        }

        //3.切断出子链表
        ListNode leftNode = pre.next;
        ListNode tailNode = rightNode.next;

        pre.next = null;
        rightNode.next = null;

        //4.reverse 中间子链表
        reverseList(leftNode);

        //5.重新接入
        pre.next = rightNode;
        leftNode.next = tailNode;
        return dummy.next;
    }

    //反转链表 -> S206
    private ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode nextTag = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nextTag;
        }
        return pre;
    }

    public static class ListNode {
        int val;
        ListNode next;
    }
}
