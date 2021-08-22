package resoursesynch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SingleLockList<T> implements Iterable<T> {
    private final List<T> list;

    public SingleLockList(List<T> list) {
        this.list = copy(list);
    }

    public void add(T value) {
        list.add(value);
    }

    public T get(int index) {
        return list.get(index);
    }

    private List<T> copy(List<T> list) {
        return new ArrayList<>(list);
    }

    @Override
    public Iterator<T> iterator() {
        return copy(list).iterator();
    }

}
