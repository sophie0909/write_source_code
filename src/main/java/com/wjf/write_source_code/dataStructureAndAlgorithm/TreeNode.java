package com.wjf.write_source_code.dataStructureAndAlgorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树相关
 */
public class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }


    /**
     * 反转二叉树
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        //base case

        if (root == null) {
            return null;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    /**
     * 将二叉树反转为一个单链表
     *
     * @param root
     */
    public void flatten(TreeNode root) {
        //base case
        if (root == null) {
            return;
        }
        flatten(root.left);
        flatten(root.right);

        /**** 后序遍历位置 ****/
        // 1、左右子树已经被拉平成一条链表
        TreeNode left = root.left;
        TreeNode right = root.right;

        // 2、将左子树作为右子树
        root.left = null;
        root.right = left;

        // 3、将原先的右子树接到当前右子树的末端
        TreeNode p = root;
        while (p.right != null) {
            p = p.right;
        }
        p.right = right;
    }

    /**
     * 654. 最大二叉树
     * 给定一个不含重复元素的整数数组 nums 。一个以此数组直接递归构建的 最大二叉树 定义如下：
     * <p>
     * 二叉树的根是数组 nums 中的最大元素。
     * 左子树是通过数组中 最大值左边部分 递归构造出的最大二叉树。
     * 右子树是通过数组中 最大值右边部分 递归构造出的最大二叉树。
     * 返回有给定数组 nums 构建的 最大二叉树 。
     *
     * @param nums
     * @return
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return buildMaxTree(nums, 0, nums.length - 1);
    }

    public TreeNode buildMaxTree(int[] nums, int lo, int hi) {
        //base case
        if (lo > hi) {
            return null;
        }
        int index = -1;
        //注意 因为nums里可能会出现0，这里max初始值不能取0 这里取整形的最下值
        int max = Integer.MIN_VALUE;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] > max) {
                index = i;
                max = nums[i];
            }
        }
        TreeNode root = new TreeNode(max);
        //最大元素 左边的数组
        root.left = buildMaxTree(nums, lo, index - 1);
        //最大元素 右边的数组
        root.right = buildMaxTree(nums, index + 1, hi);
        return root;
    }

    List<Integer> preorder = new ArrayList<>();

    /**
     * 144. 二叉树的前序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        //base case
        if (root == null) {
            return new ArrayList<>();
        }
        // 前序遍历
        preorder.add(root.val);
        preorderTraversal(root.left);
        preorderTraversal(root.right);
        return preorder;
    }

    /**
     * 94. 二叉树的中序遍历
     */
    List<Integer> inorder = new ArrayList<>();

    public List<Integer> inorderTraversal(TreeNode root) {
        //base case
        if (root == null) {
            return new ArrayList<>();
        }

        inorderTraversal(root.left);
        // 中序遍历
        inorder.add(root.val);
        inorderTraversal(root.right);
        return inorder;
    }

    List<Integer> postorder = new ArrayList<>();

    public List<Integer> postorderTraversal(TreeNode root) {
        //base case
        if (root == null) {
            return new ArrayList<>();
        }

        postorderTraversal(root.left);
        postorderTraversal(root.right);
        // 后序遍历
        postorder.add(root.val);
        return postorder;
    }

    /**
     * 105. 从前序与中序遍历序列构造二叉树
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree1(int[] preorder, int[] inorder) {
        return buildTree1(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    TreeNode buildTree1(int[] preorder, int preStart, int preEnd,
                        int[] inorder, int inStart, int inEnd) {

        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }

        // root 节点对应的值就是前序遍历数组的第一个元素
        int rootVal = preorder[preStart];
        // rootVal 在中序遍历数组中的索引
        int index = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == rootVal) {
                index = i;
                break;
            }
        }

        int leftSize = index - inStart;

        // 先构造出当前根节点
        TreeNode root = new TreeNode(rootVal);
        // 递归构造左右子树
        root.left = buildTree1(preorder, preStart + 1, preStart + leftSize,
                inorder, inStart, index - 1);

        root.right = buildTree1(preorder, preStart + leftSize + 1, preEnd,
                inorder, index + 1, inEnd);
        return root;
    }

    /**
     * 106. 从中序与后序遍历序列构造二叉树
     *
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        return buildTree2(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    TreeNode buildTree2(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd) {
        //base case
        if (inStart > inEnd) {
            return null;
        }
        int index = 0;
        //后序遍历 最后一项为 根节点的值
        int rootVal = postorder[postEnd];
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == rootVal) {
                index = i;
                break;
            }
        }
        int leftSize = index - inStart;
        TreeNode root = new TreeNode(rootVal);
        root.left = buildTree2(inorder, inStart, index - 1, postorder, postStart, postStart + leftSize - 1);
        root.right = buildTree2(inorder, index + 1, inEnd, postorder, postStart + leftSize, postEnd - 1);
        return root;
    }

    //记录结果
    int res = 0;
    //记录当前元素的排名
    int rank = 0;

    /**
     * 230. 二叉搜索树中第K小的元素
     * 中序遍历 二叉搜索树 将会得到一个升序集合
     *
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        find(root, k);
        return res;
    }

    public void find(TreeNode root, int k) {
        //base case
        if (root == null) {
            return;
        }

        kthSmallest(root.left, k);
        rank++;
        if (rank == k) {
            res = root.val;
        }
        find(root.right, k);
    }

    /**
     * 538. 把二叉搜索树转换为累加树
     *
     * @param root
     * @return
     */
    public TreeNode convertBST(TreeNode root) {
        traverse(root);
        return root;
    }

    // 记录累加和
    int sum = 0;

    void traverse(TreeNode root) {
        //base case
        if (root == null) {
            return;
        }
        traverse(root.right);
        // 维护累加和
        sum += root.val;
        // 将 BST 转化成累加树
        root.val = sum;
        traverse(root.left);
    }


    /**
     * 98. 验证二叉搜索树
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {

        return validBST(root,null,null);
    }
    public boolean validBST(TreeNode root,TreeNode min,TreeNode max) {
        //base case
        if(root==null){
            return true;
        }
        if(min!=null&&root.val<=min.val){
            return false;
        }
        if(max!=null&&root.val>=max.val){
            return false;
        }
        return validBST(root.left,min,root)&&
                validBST(root.right,root,max);
    }

    /**
     * 700. 二叉搜索树中的搜索
     * @param root
     * @param val
     * @return
     */
    public TreeNode searchBST(TreeNode root, int val) {
        //base case
        if(root==null){
            return null;
        }
        if(root.val==val){
            return root;
        }else if(root.val>val){
            return searchBST(root.left,val);
        }else{
            return searchBST(root.right,val);
        }
    }

    /**
     * 701. 二叉搜索树中的插入操作
     * @param root
     * @param val
     * @return
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
      if(root==null){
          return new TreeNode(val);
      }
      if(root.val>val){
          root.left=insertIntoBST(root.left,val);
      }
      if(root.val<val){
          root.right=insertIntoBST(root.right,val);
      }
      return root;
    }


    /**
     * 450. 删除二叉搜索树中的节点
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root.val==key){
            //删除
            TreeNode leftNode=root.left;
            root=root.left;
            root.right=leftNode;
            //1.当前节点为叶子节点
            //2.当前节点只有左子树
            //3.当前节点只有右子树
            //4.当前节点既有左子树 又有右子树
        }else if(root.val>key){
          //存在在左子树中
            root.left=deleteNode(root.left,key);
        }else{
            //存在在右子树中
            root.right=deleteNode(root.right,key);
        }
        return root;
    }
    //============================================297================================================//
    /**
     * 297. 二叉树的序列化与反序列化
     * 1、前序遍历解法
     * @param root
     * @return
     */
    String delimter=",";
    String nullSpace="#";
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }
    /* 辅助函数，将二叉树存入 StringBuilder */
    void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(nullSpace).append(delimter);
            return;
        }

        /****** 前序遍历位置 ******/
        sb.append(root.val).append(delimter);
        /***********************/

        serialize(root.left, sb);
        serialize(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] strArr=data.split(delimter);
        LinkedList<String> list=new LinkedList();
        for (String s : strArr) {
            list.add(s);
        }
        return deserialize(list);
    }
    public TreeNode deserialize(LinkedList<String> data) {
        //base case
        if(data.isEmpty()){
            return null;
        }
        /**
         * 根据前序遍历结果（包含null） 生成二叉树
         */
        String first=data.removeFirst();
        if(first.equals(nullSpace)){
            return null;
        }
        TreeNode node=new TreeNode(Integer.parseInt(first));
        node.left=deserialize(data);
        node.right=deserialize(data);
        return node;
    }

    /**
     * 297. 二叉树的序列化与反序列化
     * 2、后序遍历解法
     *
     * 二叉树无法通过中序遍历获取根节点的位置，所以无法通过中序遍历来序列化和反序列化二叉树
     * @param root
     * @return
     */
    public String serializeM(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeM(root, sb);
        return sb.toString();
    }
    /* 辅助函数，将二叉树存入 StringBuilder */
    void serializeM(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(nullSpace).append(delimter);
            return;
        }
        serializeM(root.left, sb);
        /****** 中序遍历位置 ******/
        sb.append(root.val).append(delimter);
        /***********************/
        serializeM(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserializeM(String data) {
        String[] strArr=data.split(delimter);
        LinkedList<String> list=new LinkedList();
        for (String s : strArr) {
            list.add(s);
        }
        return deserializeM(list);
    }
    public TreeNode deserializeM(LinkedList<String> data) {
        //base case
        if(data.isEmpty()){
            return null;
        }
        /**
         * 根据后序遍历结果（包含null） 生成二叉树
         */
        String last=data.removeLast();
        if(last.equals(nullSpace)){
            return null;
        }
        TreeNode node=new TreeNode(Integer.parseInt(last));
        //根据字符创中排列的元素位置  先构造右子树 后构造左子树
        node.right=deserializeM(data);
        node.left=deserializeM(data);
        return node;
    }

    /**
     * 二叉树的层序遍历方式
     * @param root
     */

    void levelTraverse(TreeNode root) {
        if (root == null) return;
        // 初始化队列，将 root 加入队列
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            TreeNode cur = q.poll();

            /* 层级遍历代码位置 */
            System.out.println(root.val);
            /*****************/

            if (cur.left != null) {
                q.offer(cur.left);
            }

            if (cur.right != null) {
                q.offer(cur.right);
            }
        }
    }

    /**
     * 297. 二叉树的序列化与反序列化
     * 3、层序遍历解法
     *
     * 二叉树无法通过中序遍历获取根节点的位置，所以无法通过中序遍历来序列化和反序列化二叉树
     * @param root
     * @return
     */
    public String serializeLevel(TreeNode root) {
        if (root == null) return "";
        StringBuilder sb = new StringBuilder();
        // 初始化队列，将 root 加入队列
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            TreeNode cur = q.poll();

            /* 层级遍历代码位置 */
            if (cur == null) {
                sb.append(nullSpace).append(delimter);
                continue;
            }
            sb.append(cur.val).append(delimter);
            /*****************/

            q.offer(cur.left);
            q.offer(cur.right);
        }

        return sb.toString();
    }

    // Decodes your encoded data to tree.  爆炸4444
    public TreeNode deserializeLevel(String data) {
        String[] strArr=data.split(delimter);
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(new TreeNode(Integer.parseInt(strArr[0])));
        for (int i = 1; i < strArr.length; i++) {
            // 队列中存的都是父节点
            TreeNode parent = queue.poll();
            // 父节点对应的左侧子节点的值
            String left = strArr[i++];
            if (!left.equals(nullSpace)) {
                parent.left = new TreeNode(Integer.parseInt(left));
                queue.offer(parent.left);
            } else {
                parent.left = null;
            }
            // 父节点对应的右侧子节点的值
            String right = strArr[i++];
            if (!right.equals(nullSpace)) {
                parent.right = new TreeNode(Integer.parseInt(right));
                queue.offer(parent.right);
            } else {
                parent.right = null;
            }
        }

        return null;
    }

    //============================================297================================================//
    public static void main(String[] args) {
        //  new TreeNode().constructMaximumBinaryTree(new int[]{3,2,1,6,0,5});
        new TreeNode().buildTree2(new int[]{9, 3, 15, 20, 7}, new int[]{9, 15, 7, 20, 3});
    }


}
