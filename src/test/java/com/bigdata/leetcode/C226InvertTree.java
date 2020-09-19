package com.bigdata.leetcode;

import org.junit.Before;
import org.junit.Test;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @ClassName C226InvertTree
 * @Description TODO
 * @Author HuHao（huhao1@cmcm.com）
 * @Date 2020/8/12 20:24
 * @Version 1.0
 * 翻转一棵二叉树。
 *
 * 示例：
 *
 * 输入：
 *
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * 输出：
 *
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/invert-binary-tree
 **/

class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;

    public TreeNode(int value) {
        this.value = value;
    }

    public TreeNode(int value, TreeNode left, TreeNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
}

public class C226InvertTree {

    private TreeNode makeTree(){
        TreeNode t1 = new TreeNode(1);
        TreeNode t3 = new TreeNode(3);
        TreeNode t6 = new TreeNode(6);
        TreeNode t9 = new TreeNode(9);

        TreeNode t7 = new TreeNode(7);
        t7.left = t9;
        t7.right = t6;

        TreeNode t2 = new TreeNode(2);
        t2.left = t3;
        t2.right = t1;

        TreeNode t4 = new TreeNode(4);
        t4.left = t7;
        t4.right = t2;

        return t4;
    }

    private void printTree(TreeNode tree,int level){
        if(tree.left!=null){
            StringBuffer buffer = new StringBuffer("");
            for(int i=0;i<level;i++){
                buffer.append("\t");
            }
            System.out.println(String.format("%s%s->%s<-%s",buffer.toString(),tree.left.value,tree.value,tree.right.value));
            level ++;
            printTree(tree.left,level);
            printTree(tree.right,level);
        }
    }

    private TreeNode tree = null;

    @Before
    public void before(){
        tree = makeTree();
    }

    @Test
    public void solve1Test(){
        printTree(tree,0);
        TreeNode treeNode = solve1(tree);
        printTree(treeNode,0);
    }

    /**
     * # 递归
     * # 迭代从树顶节点开始，左边递归得到新右边，右边递归得到新左边，然后分别安置新左和新右，重新输出树顶(自身不变，左右互换)
     * # 时间复杂度o(n)，循环遍历所有节点
     * # 空间复杂度o(n), 树顶迭代时，扫描到所有节点
     * @param tree
     * @return
     */
    public TreeNode solve1(TreeNode tree){
        if(null==tree){
            return null;
        }
        TreeNode newRight = solve1(tree.left);
        TreeNode newLeft = solve1(tree.right);
        tree.right = newRight;
        tree.left = newLeft;
        return tree;
    }

    @Test
    public void solve2Test(){
        printTree(tree,0);
        TreeNode treeNode = solve2(tree);
        printTree(treeNode,0);
    }

    /**
     * # 迭代
     * # 申请有个任务队列，首先将当前节点加入启动循环遍历
     * # 每次遍历只将当前节点左右互换，然后在当前节点左右枝子节点（非空），分别又加入任务队列
     * # 迭代到根节点时，不会有新任务添加到队列，随着队列不断弹出新任务，最终收敛，结束迭代，此时树顶已经时完全翻转后的新树
     * # 时间复杂度o(n)，每个节点都被访问一次，
     * # 空间复杂度o(n)，顶点迭代时，会有近一半节点都加入队列 [n/2] = o(n)
     * @param tree
     * @return
     */
    public TreeNode solve2(TreeNode tree){
        Queue<TreeNode> queue = new LinkedBlockingDeque<>();
        queue.add(tree);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
            if(node.left!=null){
                queue.add(node.left);
            }
            if(node.right!=null){
               queue.add(node.right);
            }
        }
        return tree;
    }



}
