package com.wjf.write_source_code.dataStructureAndAlgorithm.linkList;

/**
 *单链表相关
 */
public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }


    /**
     * 完全反转单链表 迭代写法
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        /**
         * ps 4个转换式之间首尾相接 可先写1,4 确保当前节点指针后移
         */
        while (curr != null) {
            ListNode nextTemp = curr.next;
            //当前节点指向反转
            curr.next = prev;
            //prev 指针后移 指向前一个缓存的链表
            prev = curr;
            //curr 指针后移 当前节点后续链表
            curr = nextTemp;
        }
        return prev;
    }
    /**
     * 完全反转单链表 递归写法
     * @param head
     * @return
     */
    public ListNode reverseList1(ListNode head) {
         /*
        直至取到最后一个节点 否则一直会进行递归调用
        */
        if(head==null||head.next==null){
            return head;
        }
        //阻塞一直递归调用 会调用 链表元素个数次该方法 直至最后从内向外弹出计算结果
        ListNode cur=reverseList1(head.next);
        /*
        将当前节点的值追加到反转的链表上
        */
        head.next.next=head;
        head.next=null;
        return cur;
    }



    void traverse(ListNode head) {
        System.out.print("[");
        for (ListNode p = head; p != null; p = p.next) {
            // 迭代访问 p.val
            System.out.print(p.val+",");
        }
        System.out.println("]");
    }





    //后驱节点
    ListNode sucNode=new ListNode();

    /**
     * 部分反转链表 反转从第left位置到第right位置的元素，其他位置元素保持不变
     * @param head
     * @param left 开始位置
     * @param right 结束位置
     * @return
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        //left刚好从1开始 则调用reverseListN（）方法
        if(left==1){
            return reverseListN(head,right);
        }
        //left不是从1开始，则left和rigit同时缩小 转换成 从1开始的 如 （3,5）->（1,3)
        head.next=reverseBetween(head.next,left-1,right-1);
        return head;
    }

    /**
     * 部分反转链表 反转链表从开始第1个位置 至end位置
     *
     * 该方法与完全反转的区别
     * 1、最后head节点的next 是第end位的节点而非null
     * 2、end位节点后 无需反转
     * @param head
     * @param end 结束位置
     * @return
     *
     */
    public ListNode reverseListN(ListNode head, int end) {
        //end 值为1 时 取到要反转的最后一个节点
        if(head.next==null||end==1){
            sucNode=head.next;
            return head;
        }
        ListNode cur= reverseListN(head.next,end-1);
        head.next.next=head;
        head.next=sucNode;//头节点的next 设置为后驱节点
        return cur;

    }
    public static void main(String[] args) {
        ListNode node1=new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(4,new ListNode(5)))));
        node1.traverse(node1);
        node1.reverseList1(node1);
    }
}
