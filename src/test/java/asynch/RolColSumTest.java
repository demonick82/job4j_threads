package asynch;

import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

public class RolColSumTest {

    @Test
    public void linearSum() {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        RolColSum.Sums sums1 = new RolColSum.Sums();
        RolColSum.Sums sums2 = new RolColSum.Sums();
        RolColSum.Sums sums3 = new RolColSum.Sums();
        sums1.setColSum(12);
        sums1.setRowSum(6);
        sums2.setColSum(15);
        sums2.setRowSum(15);
        sums3.setColSum(18);
        sums3.setRowSum(24);
        RolColSum.Sums[] expected = {sums1, sums2, sums3};
        assertArrayEquals(RolColSum.sum(matrix), (expected));
    }

    @Test
    public void asyncSum() throws ExecutionException, InterruptedException {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        RolColSum.Sums sums1 = new RolColSum.Sums();
        RolColSum.Sums sums2 = new RolColSum.Sums();
        RolColSum.Sums sums3 = new RolColSum.Sums();
        sums1.setColSum(12);
        sums1.setRowSum(6);
        sums2.setColSum(15);
        sums2.setRowSum(15);
        sums3.setColSum(18);
        sums3.setRowSum(24);
        RolColSum.Sums[] expected = {sums1, sums2, sums3};
        assertArrayEquals(RolColSum.asyncSum(matrix), (expected));

    }
}