package pool;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;


public class ParallelSearchIndexTest {

    @Test
    public void whenIndexIsExist() {
        Integer[] array = {12, 93, 6, 8, 9, 1, 10, 5, 1, 56, 59, 5, 8, 11, 90};
        assertThat(ParallelSearchIndex.find(array, 93), is(1));
    }

    @Test
    public void whenIndexNotExist() {
        Integer[] array = {12, 93, 6, 8, 9, 1, 10, 5, 1, 56, 59, 5, 8, 11, 90};
        assertThat(ParallelSearchIndex.find(array, 23), is(-1));
    }
}