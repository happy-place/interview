package com.bigdata.leetcode;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName C206ReverseList
 * @Description TODO
 * @Author HuHao（huhao1@cmcm.com）
 * @Date 2020/8/13 07:37
 * @Version 1.0
 **/

class ListNode{
    int value;
    ListNode next;

    public ListNode(int value) {
        this.value = value;
    }

    public ListNode(int value, ListNode next) {
        this.value = value;
        this.next = next;
    }
}

public class C206ReverseList {

    private ListNode listNode = null;

    @Before
    public void before(){
        listNode = makeList(4);
    }

    private ListNode makeList(int n){
        List<ListNode> nodes = new ArrayList<>();
        for(int i=0;i<n;i++){
            nodes.add(new ListNode(i));
        }
        for(int i=0;i<n-1;i++){
            nodes.get(i).next = nodes.get(i+1);
        }
        return nodes.get(0);
    }

    private void printList(ListNode list){
        List<String> buffer = new ArrayList<>();
        while(list!=null){
            buffer.add(list.value+"");
            list = list.next;
        }
        System.out.println(String.join(",",buffer));
    }


    @Test
    public void solve1Test(){
       printList(listNode);
       printList(solve1(listNode));
    }

    /**
     * # 迭代法
     * # 从链头开始循环，每次循环，将当前节点的下个节点先取出，然后将当前下个节点位置赋值为上轮循环寄存的节点，
     * 然后吧当前节点寄存，并将遍历引用拨到下一个节点
     * # 时间复杂度为 o(n) 逐个遍历
     * # 空间复杂度为 o(1) 只申请一个临时存储空间
     * @param head
     * @return
     */
    private ListNode solve1(ListNode head){
        ListNode pre = null;
        while(head!=null){
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        // 旧尾就是新头
        return pre;
    }

    @Test
    public void solve2Test(){
        printList(listNode);
        printList(solve2(listNode));
    }

    /**
     * # 递归法
     * # n1->n2->...->nk->nk+1->...->nm
     * # 假设迭代到nk节点，即 nk为head，则 nk 以后的 nk+1->...->nm 交给递归处理 得到了想要顺序 solve2(head.next)
     * # nk 要加入新顺序需要满足 nk.next.next=nk，即 head.next.next=head
     * # nk 顺序调整完毕，切断 nk 与 之前下个节点 nk+1 的联系，即 head.next = None
     * # 抛出新链头 p
     * # if head is None or head.next is None: return head 是为了保证 只有两个节点时链表陷入死循环
     * # 时间复杂度o(n) 遍历所有元素
     * # 空间复杂度o(n) 每次遍历，递归搜索一次 递归会使用到隐式栈空间，递归深度可能会达到n层
     * @param head
     * @return
     */
    private ListNode solve2(ListNode head){
        if(head==null || head.next==null){
            return head;
        }
        ListNode p = solve2(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

}
