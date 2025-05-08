package algorithm.stackSubject;

import java.util.Stack;


/**
 * 关键词：单调栈
 */
public class Quesetion {

    /**
     【题目】一个整数数组 A，找到每个元素：右边第一个比我小的下标位置，没有则用 -1 表示。
     输入：[5, 2]
     输出：[1, -1]
     */
    public static int[] getMinRightIndex(int[] nums){
        int[] result = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length; i++){
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]){
                result[stack.pop()] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()){
            result[stack.pop()] = -1;
        }
        return result;
    }

    /**
     * 数组左边离我最近且比我小的元素的位置
     */
    public static int[] getMinLeftIndex(int[] nums){
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]){
                result[i] = stack.pop();
            }
            stack.push(i);
        }
        while (!stack.isEmpty()){
            result[stack.pop()] = -1;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {5, 2};
        int[] result = getMinRightIndex(nums);
        for (int i = 0; i < result.length; i++){
            System.out.print(result[i] + " ");
        }

        int[] nums1 = {5, 2};
        int[] result1 = getMinRightIndex(nums1);
        for (int i = 0; i < result1.length; i++){
            System.out.print(result1[i] + " ");
        }
    }
}
