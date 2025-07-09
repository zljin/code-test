package com.zljin.oj;


import com.zljin.datastructure.list.MyLinkedList;

/**
 * https://leetcode.cn/problems/reverse-linked-list/
 */
public class ReverseLinkedList {
    public static void main(String[] args) {
        MyLinkedList<String> strList = new MyLinkedList<>();
        strList.addLast("jason");
        strList.addLast("john");
        strList.addLast("mike");
        strList.addLast("brownson");
        strList.addLast("luker");
        strList.addLast("kevin");
        System.out.println(strList);
        System.out.println("after reverse List");
        System.out.println(strList.reverse());
    }
}
