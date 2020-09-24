package com.bigdata.composite;

import java.util.ArrayList;
import java.util.List;

abstract class Node {
    String name;
    abstract void print();

}

class Leaf extends Node{

    public Leaf(String name) {
        this.name = name;
    }

    @Override
    void print() {
        System.out.println(name);
    }
}

class Branch extends Node {
    List<Node> nodes = new ArrayList<>();

    public Branch(String name) {
        this.name = name;
    }

    @Override
    void print() {
        System.out.println(name);
    }

    public void addNode(Node node){
        nodes.add(node);
    }

}

public class Tree {

    public static void main(String[] args) {
        Branch root = new Branch("A");
        Branch branch1 = new Branch("B1");
        Branch branch2 = new Branch("B2");
        Branch branch3 = new Branch("C1");

        Leaf leaf1 = new Leaf("b1");
        Leaf leaf2 = new Leaf("b2");
        Leaf leaf3 = new Leaf("b3");
        Leaf leaf4 = new Leaf("b4");

        root.addNode(branch1);
        root.addNode(branch2);

        branch1.addNode(leaf1);

        branch2.addNode(branch3);
        branch2.addNode(leaf2);
        branch2.addNode(leaf3);

        branch3.addNode(leaf4);

        walk(root,0);
    }

    public static void walk(Node node,int depth){
        for(int i=0;i<depth;i++){
            System.out.print("--");
        }
        System.out.println(node.name);
        if(node instanceof Branch){
            depth++;
            for(Node n:((Branch)node).nodes){
                walk(n,depth);
            }
        }
    }


}
