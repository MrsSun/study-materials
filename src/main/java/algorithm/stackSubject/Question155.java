package algorithm.stackSubject;

import java.util.Stack;

/**
 * 155. 最小栈
 * https://leetcode.cn/problems/min-stack/description/
 * 解题思路：维护一个最小值栈，push比栈顶小于等于的值，pop与栈顶等于的值
 */
public class Question155 {
    class MinStack {
        private Stack<Integer> valueStack;
        private Stack<Integer> minValueStack;
        public MinStack() {
            valueStack = new Stack<>();
            minValueStack = new Stack<>();
        }

        public void push(int val) {
            valueStack.push(val);
            if (minValueStack.isEmpty() || val <= minValueStack.peek()){
                minValueStack.push(val);
            }
        }

        public void pop() {
            int value = valueStack.pop();
            if (value == minValueStack.peek()){
                minValueStack.pop();
            }
        }

        public int top() {
            return valueStack.peek();
        }

        public int getMin() {
            return minValueStack.peek();
        }
    }
}
