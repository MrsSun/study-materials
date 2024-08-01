package algorithm;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ArraySubject {
    public static void thenApply() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        CompletableFuture cf = CompletableFuture.supplyAsync(() -> { //实现了Supplier的get()方法
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("supplyAsync " + Thread.currentThread().getName());
            return "hello ";
        },executorService).thenAccept(s -> { //实现了Comsumper的accept()方法
            try {
                System.out.println((s + "world"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        System.out.println(Thread.currentThread().getName());
        while (true) {
            System.out.println("轮询");
            if (cf.isDone()) {
                System.out.println("CompletedFuture...isDown");
                break;
            }
        }
    }


    public static boolean search(int[] a, int key){
        int start = 0, end = a.length - 1;
        while (start <= end){
            int middle = (start + end) / 2;
            if (a[middle] < key){
                start = middle + 1;
            }else if(a[middle] > key){
                end = middle - 1;
            }else {
                return true;
            }
        }
        return false;

    }


    public static void main(String[] args) {
        try{
            thenApply();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
