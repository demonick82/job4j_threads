package cas;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>(0);

    public void increment() {
        int currCount;
        int nextCount;
        do {
            currCount = count.get();
            nextCount = currCount + 1;
        } while (!count.compareAndSet(currCount, nextCount));
    }

    public int get() {
        return count.get();
    }
}
