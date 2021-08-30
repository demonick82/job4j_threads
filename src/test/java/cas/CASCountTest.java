package cas;

import org.junit.Test;

import java.util.stream.IntStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CASCountTest {
    @Test
    public void increment() throws InterruptedException {
        CASCount count = new CASCount();
        Thread t1 = new Thread(() -> IntStream.range(0, 5).forEach(el -> count.increment()));
        Thread t2 = new Thread(() -> IntStream.range(0, 5).forEach(el -> count.increment()));
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        assertThat(count.get(), is(10));
    }
}