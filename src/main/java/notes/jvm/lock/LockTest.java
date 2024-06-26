package notes.jvm.lock;

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

    public static void main(String[] args) {
        Object o  = new Object();


    }

    public static void print(Object o){
        synchronized (o){
//            System.out.println("进入同步代码块，MarkWord为：" + ClassL);
        }
    }

}
