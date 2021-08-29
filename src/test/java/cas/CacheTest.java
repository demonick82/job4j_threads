package cas;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class CacheTest {

    @Test
    public void add() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 1);
        base1.setName("1");
        Base base2 = new Base(2, 2);
        base2.setName("2");
        Base base3 = new Base(3, 3);
        base3.setName("3");
        Map<Integer, Base> inspected = Map.of(base1.getId(), base1, base2.getId(), base2, base3.getId(), base3);
        cache.add(base1);
        cache.add(base2);
        cache.add(base3);
        assertEquals(cache.getMemory(), inspected);
    }

    @Test
    public void delete() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 1);
        base1.setName("1");
        Base base2 = new Base(2, 2);
        base2.setName("2");
        Base base3 = new Base(3, 3);
        base3.setName("3");
        Map<Integer, Base> inspected = Map.of(base1.getId(), base1, base2.getId(), base2);
        cache.add(base1);
        cache.add(base2);
        cache.add(base3);
        cache.delete(base3);
        assertEquals(cache.getMemory(), inspected);
    }

    @Test
    public void update() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 1);
        base1.setName("1");
        Base base2 = new Base(2, 2);
        base2.setName("2");
        Base base3 = new Base(3, 3);
        base3.setName("3");
        Base baseUpdate = new Base(3, 3);
        baseUpdate.setName("4");
        Base baseInspected = new Base(3, 4);
        baseInspected.setName("4");
        Map<Integer, Base> inspected = Map.of(base1.getId(), base1, base2.getId(), base2, baseInspected.getId(), baseInspected);
        cache.add(base1);
        cache.add(base2);
        cache.add(base3);
        cache.update(baseUpdate);
        assertEquals(cache.getMemory(), inspected);
    }

    @Test(expected = OptimisticException.class)
    public void updateWithException() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 1);
        base1.setName("1");
        Base base2 = new Base(2, 2);
        base2.setName("2");
        Base base3 = new Base(3, 3);
        base3.setName("3");
        Base baseUpdate = new Base(3, 4);
        baseUpdate.setName("4");
        Base baseInspected = new Base(3, 4);
        baseInspected.setName("4");
        Map<Integer, Base> inspected = Map.of(base1.getId(), base1, base2.getId(), base2, baseInspected.getId(), baseInspected);
        cache.add(base1);
        cache.add(base2);
        cache.add(base3);
        cache.update(baseUpdate);
        assertEquals(cache.getMemory(), inspected);
    }

}