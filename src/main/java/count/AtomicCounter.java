package count;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {
    public static AtomicInteger processedCounter = new AtomicInteger(0);
    public static AtomicInteger rejectedCounter = new AtomicInteger(0);
    public static AtomicInteger overallCounter = new AtomicInteger(0);
}
