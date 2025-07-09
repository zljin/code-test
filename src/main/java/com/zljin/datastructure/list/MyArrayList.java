package com.zljin.datastructure.list;

public class MyArrayList<E> {


    private Object[] elementData;
    private int size;

    public MyArrayList() {
        this.elementData = new Object[10];
        this.size = 0;
    }

    public MyArrayList(int capacity) {
        this.elementData = new Object[capacity];
        this.size = 0;
    }

    public void add(E e) {
        addLast(e);
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void addLast(E e) {
        add(size, e);
    }

    public void add(int index, E e) {
        checkIndexForAdd(index);
        resize();
        for (int i = size; i > index; i--) {
            elementData[i] = elementData[i - 1];
        }
        elementData[index] = e;
        size++;
    }

    public void remove(int index) {
        checkIndex(index);
        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        elementData[size - 1] = null; // 清除最后一个元素的引用
        size--;
    }



    public void update(int index, E e) {
        checkIndex(index);
        elementData[index] = e;
    }

    public E get(int index) {
        checkIndex(index);
        return (E) elementData[index];
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void resize() {
        if (size < elementData.length) {
            return;
        }
        int newCapacity = elementData.length * 2;
        Object[] newElementData = new Object[newCapacity];
        System.arraycopy(elementData, 0, newElementData, 0, size);
        elementData = newElementData;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            elementData[i] = null; // 清除所有元素的引用
        }
        size = 0;
    }


    // 测试代码
    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.addFirst(10);
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println("Size: " + list.size()); // 输出: Size: 3
        System.out.println("Element at index 1: " + list.get(1)); // 输出: Element at index 1: 2

        list.addFirst(0);
        System.out.println("Element at index 0: " + list.get(0)); // 输出: Element at index 0: 0

        list.remove(1);
        System.out.println("Element at index 1 after removal: " + list.get(1)); // 输出: Element at index 1 after removal: 2

        list.update(1, 5);
        System.out.println("Element at index 1 after update: " + list.get(1)); // 输出: Element at index 1 after update: 5

    }
}
