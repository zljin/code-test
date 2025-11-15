package com.zljin.cts.linklist;

/**
 * 找到链表的中间节点
 */
public class S876 {

    public static void main(String[] args) {

    }


    //方法3: 快慢指针
    //设快指针速度为2v，慢指针速度为v，则相同时间路程为2:1，这样就好理解了
    public ListNode middleNode3(ListNode head){
        ListNode slow = head;
        ListNode fast = head;
        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    //方法2: 慢指针，轮训一次链表计数，然后再轮训一遍
    public ListNode middleNode2(ListNode head){
        ListNode cur = head;

        int i = 0;

        while(cur!=null){
            i++;
            cur = cur.next;
        }

        int j = 0;
        int mid = i/2;
        cur = head;
        while(j<mid){
            cur = cur.next;
            j++;
        }
        return cur;
    }


    //方法1: 装入到数组里面
    public ListNode middleNode1(ListNode head) {

        ListNode[] array = new ListNode[100];

        ListNode cur = head;

        int i=0;

        while(cur!=null){
            array[i++] = cur;
            cur = cur.next;
        }

        int mid = i/2;
        return array[mid];
    }

    public static class ListNode{
        int val;
        ListNode next;
    }
}
