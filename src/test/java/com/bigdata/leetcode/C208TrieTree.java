package com.bigdata.leetcode;

import org.junit.Test;

/**
 * @ClassName C208TrieTree
 * @Description TODO
 * @Author HuHao（huhao1@cmcm.com）
 * @Date 2020/8/15 20:34
 * @Version 1.0
 **/

/**
 * Trie (发音为 "try") 或前缀树是一种树数据结构，用于检索字符串数据集中的键。这一高效的数据结构有多种应用：
 * 1. 自动补全
 * 2. 拼写检查
 * 3. IP 路由 (最长前缀匹配)
 * 4. T9 (九宫格) 打字预测
 * 5. 单词游戏
 * 还有其他的数据结构，如平衡树和哈希表，使我们能够在字符串数据集中搜索单词。为什么我们还需要 Trie 树呢？尽管哈希表可以在 O(1)O(1) 时间内寻找键值，却无法高效的完成以下操作：
 *
 * 找到具有同一前缀的全部键值。
 * 按词典序枚举字符串的数据集。
 * Trie 树优于哈希表的另一个理由是，随着哈希表大小增加，会出现大量的冲突，时间复杂度可能增加到 O(n)O(n)，
 * 其中 nn 是插入的键的数量。与哈希表相比，Trie 树在存储多个具有相同前缀的键时可以使用较少的空间。此时 Trie 树只需要 O(m)O(m) 的时间复杂度，其中 mm 为键长。
 * 而在平衡树中查找键值需要 O(m \log n)O(mlogn) 时间复杂度
 *
 *  Trie 树的结点结构
 * Trie 树是一个有根的树，其结点具有以下字段：。
 *
 * 最多 RR 个指向子结点的链接，其中每个链接对应字母表数据集中的一个字母。
 * 本文中假定 RR 为 26，小写拉丁字母的数量。
 * 布尔字段，以指定节点是对应键的结尾还是只是键前缀。
 *
 * 作者：LeetCode
 * 链接：https://leetcode-cn.com/problems/implement-trie-prefix-tree/solution/shi-xian-trie-qian-zhui-shu-by-leetcode/
 */

/**
 * Trie 节点
 */
class TrieNode{
    // 使用数组结构存储当前节点往后链接的节点
    private TrieNode[] links;
    private final int R = 26; // 每个节点往后链接的节点的可能性
    private boolean isEnd; // 标记当前节点是否是某单词的结束节点

    public TrieNode() {
        // 26个字母按序存储，因为有序，故类hash
        links = new TrieNode[R]; // 初始化当前节点 往下链接的可能性
    }

    public boolean containsKey(char ch){
        // 指定下标为空，即表明不包含对应字符
        return links[ch-'a']!=null;
    }

    public TrieNode get(char ch){
        return links[ch-'a']; // 下标取值
    }

    public void put(char ch,TrieNode node){
        links[ch-'a'] = node; // 下标存储
    }

    public void setEnd(){
        isEnd = true;
    }

    public boolean isEnd() {
        return isEnd;
    }
}

class Trie {
    private TrieNode root; // 前缀树的根，是指就是 'a' 节点

    public Trie() {
        this.root = new TrieNode();
    }

    /**
     * 插入
     * @param word
     */
    public void insert(String word){
        TrieNode current = root;// 从根开始查找
        for(int i=0;i<word.length();i++){
            char ch = word.charAt(i);
            if(!current.containsKey(ch)){ // 未找到就从当前将当前字符嵌入
                current.put(ch,new TrieNode());
            }
            current = current.get(ch); // 搜索定位到当前字符，进入下一轮循环，直至插完
        }
        current.setEnd(); // 将尾节点设置为结束标记
    }

    /**
     * 定位到匹配不到的位置，然后返回失联前节点(最长匹配节点)，为startsWith search 提供参考
     * @param word
     * @return
     */
    public TrieNode searchPrefix(String word){
        TrieNode current = root;
        for(int i=0;i<word.length();i++){
            char ch = word.charAt(i);
            if(current.containsKey(ch)){ // 从根开始，如果匹配上，就定位到当前节点，继续向下匹配
                current = current.get(ch);
            }else{
                return null; // 未匹配完，就停止了，返回空
            }
        }
        return current; // 最长匹配节点
    }

    public boolean search(String word){
        TrieNode current = searchPrefix(word);
        return current!=null && current.isEnd(); // 最长匹配不为空，且为尾，则证明包含单词
    }

    public boolean startsWith(String word){
        TrieNode current = searchPrefix(word);
        return current!=null; // 未匹配完，说明不是以其开头
    }


}


public class C208TrieTree {

    @Test
    public void solve1(){
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));   // 返回 true
        System.out.println(trie.search("app"));     // 返回 false
        System.out.println(trie.startsWith("app")); // 返回 true
        trie.insert("app");
        System.out.println(trie.search("app"));     // 返回 true
    }


}
