package wait;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;


public class SimpleBlockingQueueTest {

    final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);
    final CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();

    @Test
    public void producerCustomerTest() throws InterruptedException {
        Thread producer = new Thread(
                () -> {
                    for (int i = 0; i <= 5; i++) {
                        queue.offer(i);
                    }
                }
        );
        Thread consomer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        list.add(queue.poll());
                    }
                }

        );
        producer.start();
        consomer.start();
        producer.join();
        consomer.interrupt();
        consomer.join();

        assertThat(list, is(Arrays.asList(0, 1, 2, 3, 4, 5)));
    }
}