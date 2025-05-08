package algorithm.stackSubject;

import java.util.Stack;

/**
 * @Description
 * @Author lichao11@xiaomi.com
 * @Date 2025/4/30
 */
public class Test {

    public static int[] method(int[] arr){
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++){
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]){
                result[stack.pop()] = i;
            }
            stack.push(i);

        }
        while (!stack.isEmpty()){
            int index = stack.pop();
            result[index] = -1;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {5,2,1,0,6,7};
        int[] result = method(arr);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + ", ");
        }

        
    }
}
