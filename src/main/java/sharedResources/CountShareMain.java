package sharedResources;

public class CountShareMain {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            Count count = new Count();
            Thread first = new Thread(count::increment);
            Thread second = new Thread(count::increment);
            first.start();
            second.start();
            first.join();
            second.join();
            System.out.println(count.get());
        }
    }
}
