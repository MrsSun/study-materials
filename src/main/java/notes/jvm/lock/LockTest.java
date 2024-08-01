package notes.jvm.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
    /** 双层锁校验 + volatile实现单例模式*/
    public volatile Object o;
    public void lockExecute(){
        if(o == null){
            synchronized (o){
                if (o == null){
                    o = new Object();
                }
            }
        }
    }

    public static void main1(String[] args) {
        Object o  = new Object();

        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        lock.lock();
        try {
            condition.await();
        }catch (InterruptedException e){
            System.out.println(e);
        }

        condition.signal();
        lock.unlock();
    }

    //循环打印ABC
    public static int num = 0;
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Thread threadA = new Thread(() -> {
            while (num  <= 9){
                lock.lock();
                if (num <= 9 & num % 3 == 0){
                    System.out.print("num: " + num + ": A  ");
                    num++;
                }
                lock.unlock();
            }
        });
        Thread threadB = new Thread(() -> {
            while (num <= 9){
                lock.lock();
                if (num <= 9 & num % 3 == 1){
                    System.out.print("num: " + num + ": B  ");
                    num++;
                }
                lock.unlock();
            }
        });

        Thread threadC = new Thread(() -> {
            while (num <= 9){
                lock.lock();
                if (num <= 9 && num % 3 == 2){
                    System.out.println("num: " + num + ": C  ");
                    num++;
                }
                lock.unlock();
            }
        });

        threadA.start();
        threadB.start();
        threadC.start();

    }

    public static void main2(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
    }

}
