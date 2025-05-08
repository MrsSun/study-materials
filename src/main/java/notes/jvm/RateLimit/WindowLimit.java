package notes.jvm.RateLimit;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Description
 * @Author lichao11@xiaomi.com
 * @Date 2025/4/25
 */
public class WindowLimit {

    private volatile long startTime; //窗口开始时间
    private int windowTimeLength; //固定窗口时间长度
    private AtomicInteger windowCount; //固定窗口时间内请求数量统计
    private int windowMaxCount; //固定窗口时间内允许最大的请求数

    public WindowLimit(int windowTimeLength, int windowMaxCount) {
        this.startTime = System.currentTimeMillis();
        this.windowCount = new AtomicInteger(0);
        this.windowTimeLength = windowTimeLength;
        this.windowMaxCount = windowMaxCount;
    }
    public boolean isAllowed(){
        long currentTime = System.currentTimeMillis();
        updateStartTime(currentTime);

        if (windowCount.get() < windowMaxCount) {
            windowCount.incrementAndGet();
            return true;
        }

        return false;
    }

    public void updateStartTime(long currentTime){
        if (currentTime < startTime){
            return;
        }

        if (currentTime - startTime > windowTimeLength){
            synchronized (this){
                if (currentTime - startTime > windowTimeLength){
                    startTime = currentTime;
                    windowCount.set(0);
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        WindowLimit windowLimit = new WindowLimit(200, 10);
        int currentNum = 0;
        while (true){
            boolean result = windowLimit.isAllowed();
            System.out.println("currentNum = " + currentNum++ + ", result = " + result);
            Thread.sleep(10);
        }
    }

}
