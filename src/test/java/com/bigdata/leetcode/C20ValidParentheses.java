package com.bigdata.leetcode;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * @ClassName C20ValidParentheses
 * @Description TODO
 * @Author HuHao（huhao1@cmcm.com）
 * @Date 2020/8/14 06:34
 * @Version 1.0
 *     text = '()'
 *     print solve1(text)
 *     text = '()[]{}'
 *     print solve1(text)
 *     text = '(]'
 *     print solve1(text)
 *     text = '([)]'
 *     print solve1(text)
 *     text = '{[]}'
 *     print solve1(text)
 **/

public class C20ValidParentheses {

    private List<String> texts = null;

    @Before
    public void before() {
        texts = new ArrayList<>();
        texts.add("()");
        texts.add("()[]{}");
        texts.add("(]");
        texts.add("([)]");
        texts.add("{[]}");
    }

    @After
    public void after() {
    }


    @Test
    public void solve2Test(){
        for (String text : texts) {
            System.out.println(solve1(text));
        }
    }

    public boolean solve1(String text){
        Map<Character, Character> qutos = new HashMap<>();
        qutos.put('{', '}');
        qutos.put('(', ')');
        qutos.put('[', ']');
        Stack<Character> stack = new Stack<>();
        stack.add('*'); // 避免一开始就是右括号
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (qutos.containsKey(c)) {
                stack.push(qutos.get(c));
            } else if (stack.pop() != c) {
                return false;
            }
        }
        return stack.size() == 1;
    }

    @Test
    public void testDeq(){
        Stack<Character> stack = new Stack<>();
        stack.push('*');
        stack.push('?');
        stack.push('&');
        while(!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }



}
