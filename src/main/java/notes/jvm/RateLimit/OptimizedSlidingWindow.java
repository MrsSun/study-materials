package notes.jvm.RateLimit;

/**
 * @Description
 * @Author lichao11@xiaomi.com
 * @Date 2025/4/25
 */
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class OptimizedSlidingWindow {
    private final AtomicReferenceArray<Window> windows;
    private final int sampleCount;   // 子窗口数量
    private final long windowLengthMs; // 单个子窗口长度（毫秒）
    private int maxRequests = 100;
    private long windstartTime;

    public OptimizedSlidingWindow(int sampleCount, long intervalMs) {
        this.sampleCount = sampleCount;
        this.windowLengthMs = intervalMs / sampleCount;
        this.windows = new AtomicReferenceArray<>(sampleCount);
        this.windstartTime = System.currentTimeMillis();
    }

    public boolean allowRequest() {
        long currentTime = System.currentTimeMillis();
        int idx = (int) (((currentTime - windstartTime ) / windowLengthMs) % sampleCount);
//        long windowStart = currentTime - (currentTime % windowLengthMs);

        Window currentWindow = windows.get(idx);
        if (currentWindow == null || currentWindow.start + windowLengthMs < currentTime) {
            currentWindow = new Window(currentTime, new AtomicLong(0));
            windows.compareAndSet(idx, windows.get(idx), currentWindow);
        }

        long count = currentWindow.count.incrementAndGet();
        long total = sumValidWindows(currentTime);
        return total <= maxRequests; // maxRequests为阈值
    }

    private long sumValidWindows(long currentTime) {
        long sum = 0;
        for (int i = 0; i < sampleCount; i++) {
            Window window = windows.get(i);
            if (window != null && currentTime - window.start <= windowLengthMs * sampleCount) {
                sum += window.count.get();
            }
        }
        return sum;
    }

    private static class Window {
        long start;
        AtomicLong count;

        Window(long start, AtomicLong count) {
            this.start = start;
            this.count = count;
        }
    }
}