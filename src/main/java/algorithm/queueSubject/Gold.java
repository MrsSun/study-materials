package algorithm.queueSubject;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Description
 * @Author lichao11@xiaomi.com
 * @Date 2024/12/27
 */
public class Gold {

    public static int getGold(int[] nums, int k){
        Deque<Integer> deque = new LinkedList<>();
        deque.addFirst(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            if (deque.size() > k){
                deque.pollFirst();
            }
            int num = nums[i] + deque.peekFirst();
            while (deque.size() > 0 && num > deque.peekLast()){
                deque.pollLast();
            }
            deque.addLast(num);
        }
        return deque.pollFirst();
    }

    public static void main(String[] args) {
        int[] nums = {1,-1,-100,-1000,100,3};
        int k = 2;
        System.out.println(getGold(nums, k));
    }


}
