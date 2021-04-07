package com.wjf.write_source_code.dataStructureAndAlgorithm.treeNode;

import com.wjf.write_source_code.dataStructureAndAlgorithm.TreeNode;

public class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }

    /**
     * 填充每个节点的下一个右侧节点指针
     * @param root
     * @return
     */
    public Node connect(Node root) {
        // base case
        if (root == null) {
            return null;
        }
        /**
         * 有左右字数交叉的问题 这里拆分出两个节点来递归处理
         */
        connectTwoNode(root.left, root.right);

        return root;
    }

    void connectTwoNode(Node node1, Node node2) {

        //base case
        if (node1 == null || node2 == null) {
            return;
        }
        node1.next = node2;
        connectTwoNode(node1.left, node1.right);
        connectTwoNode(node1.right, node2.left);
        connectTwoNode(node2.left, node2.right);

    }




    public Node conn(Node root){
        if(root==null){
            return null;
       }
        connTwo(root.left,root.right);
      return root;
    }
    public void connTwo(Node node1,Node node2){
        if(node1==null||node2==null){
            return;
        }
        node1.next=node2;
        connTwo(node1.left,node1.right);
        connTwo(node1.right,node2.left);
        connTwo(node2.left,node2.right);
    }
}
