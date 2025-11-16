package com.zljin.cts.linklist;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定单链表的头节点 head ，将所有索引为奇数的节点和索引为偶数的节点分别分组，
 * 保持它们原有的相对顺序，然后把偶数索引节点分组连接到奇数索引节点分组之后，返回重新排序的链表。
 * 第一个节点的索引被认为是 奇数 ， 第二个节点的索引为 偶数 ，以此类推。
 * 你必须在 O(1) 的额外空间复杂度和 O(n) 的时间复杂度下解决这个问题。
 */
public class S328 {
    public static void main(String[] args) {

    }
    //1->2->3->4
    public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode cur = head;
        ListNode odd = head;//奇数
        int i=1;

        while(cur.next!=null){
            i++;
            if(i%2==0){
                cur = cur.next;
                continue;
            }

            ListNode tempTag = cur.next;
            cur.next = tempTag.next;
            tempTag.next = odd.next;
            odd.next = tempTag;
            odd = odd.next;
        }
        return head;
    }


    public static class ListNode{
        int val;
        ListNode next;
    }
}
