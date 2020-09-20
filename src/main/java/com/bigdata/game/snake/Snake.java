package com.bigdata.game.snake;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Snake {

    // 蛇头、蛇尾，同时定义，方便加头，去尾（不用在每次操作时都需要递推计算）
    Node head,tail;
    // 蛇移动方向（初始化向左）
    Dir dir = Dir.L;

    Snake(){
        // 初始化只有一节，且位于 （20，20）位置,
        head = new Node(20,20);
        tail = head;
    }

    public void addToHead(){
        Node n = null;
        // 坐标系 ↘
        switch (dir){
            case L:
                // 左移 减列
                n = new Node(head.row,head.col-1);
                break;
            case R:
                // 右移 加列
                n = new Node(head.row,head.col+1);
                break;
            case U:
                // 上移 减行
                n = new Node(head.row-1,head.col);
                break;
            case D:
                // 下移 加行
                n = new Node(head.row+1,head.col);
                break;
        }
        // 新头指向旧头
        n.next = head;
        // 旧头指向新头
        head.prev = n;
        // 新头归位
        head = n;
    }

    public void deleteTail(){
        if(tail==null){
            return;
        }
        // 尾巴移到倒数第二位
        tail = tail.prev;
        // 从新尾巴定位到老尾巴，剪段向前指的引用 （切断旧尾指向新尾的引用）
        tail.next.prev = null;
        // 切断新尾指向旧尾的引用
        tail.next = null;
        // 只有双向引用都切断，才能避免内存泄露（JVM默认有引用存在，对象就不能删除）
    }


    public void paint(Graphics g) {
        // 蛇是有一个一个节点Node组成的，因此绘制蛇之前，要先定义Node
        // 绘制蛇从头节点开始
        Node n = head;

        // 循环遍历节点，然后具体每个节点的绘制工作，交给节点自身完成
        while(n!=null){
            n.paint(g);
            n = n.next;
        }

        // 每次重画过程 跟踪移动
        move();
    }

    private void move() {
        addToHead();
        deleteTail();
        // 边界检查
        boundaryCheck();
    }

    private void boundaryCheck() {
        // 超出网格后，从对应另外一边还原
        if(head.row < 0){
            head.row = Yard.NodeCount;
        }
        if(head.row > Yard.NodeCount){
            head.row = 0;
        }
        if(head.col < 0){
            head.col = Yard.NodeCount;
        }
        if(head.col > Yard.NodeCount){
            head.col = 0;
        }
    }

    // 监听键盘方向操作，并对应到head 移动方向上 （头的方向对应移动趋势）
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode){
            case KeyEvent.VK_LEFT:
                dir = Dir.L;
                break;
            case KeyEvent.VK_RIGHT:
                dir = Dir.R;
                break;
            case KeyEvent.VK_UP:
                dir = Dir.U;
                break;
            case KeyEvent.VK_DOWN:
                dir = Dir.D;
                break;
        }
    }

    public void eat(Egg egg) {
        if(head.row == egg.row && head.col == egg.col){
            addToHead();
            egg.reAppear();
        }
    }
}

