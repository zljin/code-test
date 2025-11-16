package com.zljin.cts.linklist;

import java.util.HashSet;
import java.util.Set;

/**
 * check 环形链表
 */
public class S141 {

    //方法二：直接依赖Set集合插入，若有环型肯定会重复插入
    public boolean hasCycle(ListNode head) {
        Set<ListNode> hashSet = new HashSet<>();
        ListNode cur = head;
        while(cur!=null){
            if(hashSet.contains(cur)){
                return true;
            }
            hashSet.add(cur);
            cur = cur.next;
        }
        return false;
    }

    //双指针快慢，如果为环状，那么必会相交，否则单链条的话，肯定会停下载
    public boolean hasCycle2(ListNode head) {
        if(head == null || head.next == null){
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;

        while(fast!=null){
            if(fast.next==null){
                return false;
            }
            if(slow == fast){
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }

    public static class ListNode{
        int val;
        ListNode next;
    }
}
