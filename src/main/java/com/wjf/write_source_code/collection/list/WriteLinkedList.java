package com.wjf.write_source_code.collection.list;

import java.util.LinkedList;

/**
 * 基于链表的结构实现
 */
public class WriteLinkedList<E> {
    /**
     * 查找和移除的前的查找 采用二分法
     * 链表容量1/2之前，和1/2之后的数据分别遍历查找
     * @param args
     */

    /**
     * 记录当前链表头结点
     */
    transient Node<E> first;


    /**
     * 记录当前链表尾节点
     */
    transient Node<E> last;
    //当前链表node节点个数
    transient int size = 0;

    public void add(E e) {
        // 获取当前的尾节点
        Node<E> l = last;
        // 采用尾插法
        Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null) {
            // 第一次新增node节点
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    /**
     * 以size为10的链表来说
     * 查找第index=4的元素
     * 4<10/2=5  遍历从index=0开始至4
     * 查找index=8的元素
     * 8>5 遍历从index=5至8
     * @param index
     * @return
     */
    public Node<E> getNode(int index) {
//        Node x = first; // 折半
//        for (int i = 0; i < index; i++) {
//            x = x.next;
//        }
//        return x;
        if (index < size / 2) {
            Node<E> x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node<E> x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }

    public boolean remove(int index) {
        // node3
        Node<E> delNode = getNode(index);
        if (delNode == null) {
            return false;
        }
        // node2
        Node<E> prev = delNode.prev;
        // node4
        Node<E> next = delNode.next;
        //  判断删除的节点是否为头结点 如果是为头结点的情况下 则下一个节点尾头结点
        if (prev == null) {
            first = next;
        } else {
            //node2.next=node4
            prev.next = next;
            delNode.prev = null;// 告诉gc清理垃圾
        }

        if (next == null) {
            last = prev;
        } else {
            // node4.prev=node2
            next.prev = prev;
            delNode.next = null;// 告诉gc清理垃圾
        }
        delNode.item = null;
        size--;
        return true;
    }


    public static void main(String[] args) {
        LinkedList<String> linkedList=new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            linkedList.add(i+"");
        }
        System.out.println(linkedList.toString());
        linkedList.remove("0");
        System.out.println(linkedList.toString());


    }

    /**
     * 双向链表
     *
     * @param <E>
     */
    private static class Node<E> {
        // 元素
        E item;
        // next；
        Node<E> next;
        // 上一个节点
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}
