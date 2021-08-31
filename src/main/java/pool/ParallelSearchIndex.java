package pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelSearchIndex<T> extends RecursiveTask<Integer> {

    private final T[] array;
    private final T obj;
    private final int from;
    private final int to;

    public ParallelSearchIndex(T[] array, T obj, int from, int to) {
        this.array = array;
        this.obj = obj;
        this.from = from;
        this.to = to;
    }

    @Override
    protected Integer compute() {
        if ((to - from) <= 10) {
            for (int i = from; i < to; i++) {
                if (array[i].equals(obj)) {
                    return i;
                }
            }
            return -1;
        }
        int mid = (to - from) / 2;
        ParallelSearchIndex<T> leftIndex = new ParallelSearchIndex(array, obj, from, mid);
        ParallelSearchIndex<T> rightIndex = new ParallelSearchIndex(array, obj, mid + 1, to);
        leftIndex.fork();
        rightIndex.fork();
        int left = leftIndex.join();
        int right = rightIndex.join();
        return left != -1 ? left : right;
    }

    public static <T> Integer find(T[] array, T obj) {
        ForkJoinPool pool = new ForkJoinPool();
        return pool.invoke(new ParallelSearchIndex<>(array, obj, 0, array.length));
    }

    public static void main(String[] args) {
        Integer[] array = {12, 93, 6, 8, 9, 1, 10, 5, 1, 56, 59, 5, 8, 11, 90};
        System.out.println(ParallelSearchIndex.find(array, 90));
    }
}
