package com.zljin.codes;

import com.zljin.bean.ListNode;

/**
 * https://leetcode.cn/problems/reverse-linked-list/
 */
public class ReverseLinkedListDemo {
    public static void main(String[] args) {

    }

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        while (current != null) {
            // 保存下一个节点
            ListNode nextTemp = current.getNext();
            // 反转当前节点的指针
            current.setNext(prev);
            // 移动 prev 到当前节点
            prev = current;
            // 移动到下一个节点
            current = nextTemp;
        }
        // prev 将是新的头节点
        return prev;
    }
}
