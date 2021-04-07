package com.wjf.write_source_code.dataStructureAndAlgorithm.treeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * N叉树
 */
public class NTreeNode {
    public int val;
    public List<NTreeNode> children;

    public NTreeNode() {}

    public NTreeNode(int _val) {
        val = _val;
    }

    public NTreeNode(int _val, List<NTreeNode> _children) {
        val = _val;
        children = _children;
    }

    List<Integer> postorder=new ArrayList<>();
    public List<Integer> postorder(NTreeNode root) {
        //base case
        if(root==null){
            return new ArrayList<>();
        }
        for(int i=0;i<root.children.size();i++){
            NTreeNode child=root.children.get(i);
            postorder(child);
        }
        postorder.add(root.val);
        return postorder;
    }
}
