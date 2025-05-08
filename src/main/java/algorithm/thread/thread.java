package algorithm.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description
 * @Author lichao11@xiaomi.com
 * @Date 2025/1/24
 */
public class thread {

/*    public static void main(String[] args) {
        CompletableFuture.runAsync(() -> {
            try {
                test();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }*/

    public static void test() {
        try {
            System.out.println("hello world");
//                int num = 1/0;
        } catch (Exception e) {
            System.out.println("catch error");
            e.printStackTrace();
        } finally {
            int num = 1 / 0;
            System.out.println("finally");
        }
    }


    private static int count = 1;
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    public static void print(int num, String content) {
        try {
            for (int i = 0; i < 3; i++) {
                lock.lock();
                while (num != count % 3) {
                    condition.await();
                }
                System.out.println(Thread.currentThread().getName() + "打印内容 = " + content + ", count = " + count);
                count++;
                condition.signalAll();
                lock.unlock();
//                System.out.println(Thread.currentThread().getName() + "释放锁");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Thread(() -> printCircle(1, "A"), "线程1").start();
        new Thread(() -> printCircle(2, "B"), "线程2").start();
        new Thread(() -> printCircle(0, "C"), "线程3").start();

    }

    public static void printCircle(int num, String content){
        try {
            for (int i = 0; i < 3; i++){
                lock.lock();
                while (num != count % 3){
                    condition.await();
                }
                System.out.println("打印: " + content);
                count++;
                condition.signalAll();
                lock.unlock();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
