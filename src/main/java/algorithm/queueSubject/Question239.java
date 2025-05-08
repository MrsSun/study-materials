package algorithm.queueSubject;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description
 * https://leetcode.cn/problems/sliding-window-maximum/description/
239. 滑动窗口最大值
给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
返回 滑动窗口中的最大值 。
示例 1：

输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
输出：[3,3,5,5,6,7]
解释：
滑动窗口的位置                最大值
---------------               -----
[1  3  -1] -3  5  3  6  7       3
1 [3  -1  -3] 5  3  6  7       3
1  3 [-1  -3  5] 3  6  7       5
1  3  -1 [-3  5  3] 6  7       5
1  3  -1  -3 [5  3  6] 7       6
1  3  -1  -3  5 [3  6  7]      7
示例 2：

输入：nums = [1], k = 1
输出：[1]
 */
public class Question239 {

    public static int[] maxSlidingWindow(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];
        Deque<Integer> queue = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            while (queue.size() != 0 && queue.peekLast() < nums[i]){
                queue.pollLast();
            }
            queue.add(nums[i]);
            if (i >= k-1){
                result[i-k+1] = queue.peek();
                if (nums[i-k+1] == queue.peek()){
                    queue.poll();
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,3,1,2,0,5};
        int[] result = maxSlidingWindow(nums,3);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + ", ");
        }
        System.out.println("\n");

        int[] nums2 = new int[]{1};
        int[] result2 = maxSlidingWindow(nums2,1);
        for (int i = 0; i < result2.length; i++) {
            System.out.print(result2[i] + ", ");
        }
    }

}
