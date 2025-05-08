package algorithm.stackSubject;

import java.util.Stack;

/**
 有N条鱼每条鱼的位置及大小均不同，他们沿着X轴游动，有的向左，有的向右。游动的速度是一样的，两条鱼相遇大鱼会吃掉小鱼。从左到右给出每条鱼的大小和游动的方向（0表示向左，1表示向右）。问足够长的时间之后，能剩下多少条鱼？


 Input

 第1行：1个数N，表示鱼的数量(1 <= N <= 100000)。
 第2 - N + 1行：每行两个数A[i], B[i]，中间用空格分隔，分别表示鱼的大小及游动的方向(1 <= A[i] <= 10^9，B[i] = 0 或 1，0表示向左，1表示向右）。

 Output

 输出1个数，表示最终剩下的鱼的数量。

 Input示例

 5
 4 0
 3 1
 2 0
 1 0
 5 0

 Output示例

 2
 题目链接：http://www.51nod.com/onlineJudge/questionCode.html#!problemId=1289
 分析：
 模拟一下，对于第i条鱼，如果方向朝右，用一个数组去记录此时的鱼的大小，当存在向左的鱼的时候，让这向左游动的鱼与此鱼去比较，如果C[flag]<w[i],那么flag死，flag--；继续去访问flag前面的值，否则i死，继续去访问i+1之后的元素
 下面给出AC代码：
 */
public class QuestionFish {

    public static int eatFish(int fishNum, int[] fishWight, int[] fishDirection){
        //特殊情况判断一下，不需要走后续流程
        if (fishNum == 0 || fishNum == 1){
            return fishNum;
        }
        Stack<Integer> aliveFish = new Stack<>();
        //遍历鱼群
        for(int i = 0; i <fishNum; i++){
            //标记当前鱼是否被吃
            boolean isEat = false;
            //需要消除的情况就是，有向左游的鱼，并且栈顶有向右游的鱼
            while (fishDirection[i] == 0 && !aliveFish.isEmpty() && fishDirection[aliveFish.peek()] == 1){
                if (fishWight[i] < fishWight[aliveFish.peek()]){ //如果当前鱼比栈顶鱼小，则被吃
                    isEat = true;
                    break;
                }else { //如果当前鱼比栈顶鱼大，则栈顶鱼被吃
                    aliveFish.pop();
                }
            }
            if (!isEat){ //没有被吃的鱼，则入栈
                aliveFish.push(i);
            }
        }
        System.out.println(aliveFish);
        return aliveFish.size();
    }


    public static void main(String[] args) {
        int fishNum = 6;
        int[] fishWight = {6,4,3,2,1,5};
        int[] fishDirection = {0,1,1,0,0,0};
        System.out.println(eatFish(fishNum, fishWight, fishDirection));
    }

}
