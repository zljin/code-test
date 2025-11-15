package com.zljin.cts.linklist;

import java.util.HashSet;
import java.util.Set;

/**
 * 找到两个链表的相交节点，若没有则返回为null
 */
public class S160 {

    //法2:双指针，数学规律
    public ListNode reverseKGroup(ListNode headA, ListNode headB) {
        if(headA == null || headB == null){
            return null;
        }

        ListNode p = headA;
        ListNode q = headB;

        while(p!=q){
            p = (p==null)?headB:p.next;
            q = (q==null)?headA:q.next;
        }
        return p;
    }


    //法1: 将链表A的数据放入Set中，再遍历B链表每个元素收否在Set集合中即可
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        ListNode cur = headA;
        while(cur!=null){
            set.add(cur);
            cur = cur.next;
        }
        cur = headB;
        while(cur!=null){
            if(set.contains(cur)){
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }

    public static class ListNode{
        int val;
        ListNode next;
    }
}
