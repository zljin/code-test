package com.zljin.datastructure.list;

/**
 * @author leonard
 * @date 2022/6/15
 * @Description linkedList collection
 */
public class MyLinkedList<E> {

    private ListNode<E> dummyHead;

    private int size;

    public MyLinkedList() {
        this.dummyHead = new ListNode<>(null, null);
        this.size = 0;
    }


    public void add(int index, E e) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException();
        ListNode<E> pre = dummyHead;
        for (int i = 0; i < index; i++) {
            // find before index of purpose
            pre = pre.getNext();
        }
        pre.setNext(new ListNode<E>(e, pre.getNext()));
        this.size++;
    }


    public void addFirst(E e) {
        this.add(0, e);
    }


    public void addLast(E e) {
        this.add(size, e);
    }


    public E get(int index) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException();
        ListNode<E> cur = dummyHead.getNext();
        for (int i = 0; i < index; i++) {
            cur = cur.getNext();
        }
        return cur.getVal();
    }


    public ListNode<E> getNode(int index) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException();
        ListNode<E> cur = dummyHead.getNext();
        for (int i = 0; i < index; i++) {
            cur = cur.getNext();
        }
        return cur;
    }


    public E getFirst() {
        return this.get(0);
    }


    public E getLast() {
        return this.get(size);
    }


    public void set(int index, E e) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException();
        ListNode<E> cur = dummyHead.getNext();
        for (int i = 0; i < index; i++) {
            cur = cur.getNext();
        }
        cur.setVal(e);
    }


    public boolean contains(E e) {
        ListNode pre = dummyHead;
        while (pre.getNext() != null) {
            pre = pre.getNext();
            if (pre.getVal() == e) return true;
        }
        return false;
    }


    public E remove(int index) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException();
        ListNode<E> pre = dummyHead;
        for (int i = 0; i < index; i++) {
            pre = pre.getNext();
        }

        //clear delNode and gc
        ListNode<E> delNode = pre.getNext();
        delNode.setNext(null);

        pre.setNext(pre.getNext().getNext());
        this.size--;
        return delNode.getVal();
    }


    public E removeElement(E e) {
        ListNode<E> pre = dummyHead;
        while (pre.getNext() != null) {
            pre = pre.getNext();
            if (pre.getVal() == e) break;
        }

        if (pre.getNext() == null) return null;

        //clear delNode and gc
        ListNode<E> delNode = pre;
        delNode.setNext(null);

        pre.setNext(pre.getNext().getNext());

        return delNode.getVal();
    }


    public E removeLast() {
        return this.remove(size);
    }


    public E removeFirst() {
        return this.remove(0);
    }


    public int size() {
        return this.size;
    }


    public boolean isEmpty() {
        return size == 0;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("dummyHead->");
        ListNode<E> cur = dummyHead.getNext();
        while (cur != null) {
            sb.append(cur + "->");
            cur = cur.getNext();
        }
        sb.append("NULL");
        return sb.toString();
    }

    /**
     * 反转链表
     * https://leetcode.cn/problems/reverse-linked-list/
     * @return
     */
    public String reverse() {
        ListNode<E> prev = null;
        ListNode<E> current = dummyHead.getNext();
        while (current != null) {
            ListNode<E> nextTemp = current.getNext();
            current.setNext(prev);
            prev = current;
            current = nextTemp;
        }
        dummyHead.setNext(prev);
        return this.toString();
    }

    public static void main(String[] args) {
        MyLinkedList<String> strList = new MyLinkedList<>();
        strList.addFirst("jason");
        strList.addFirst("john");
        strList.addFirst("mike");
        strList.addFirst("brownson");
        strList.addLast("luker");
        strList.add(3,"kevin");
        System.out.println(strList.toString());
    }
}
