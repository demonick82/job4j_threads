package asynch;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {

    public static class Sums {
        private int rowSum;
        private int colSum;

        public int getRowSum() {
            return rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Sums sums = (Sums) o;
            return rowSum == sums.rowSum
                    && colSum == sums.colSum;
        }

        @Override
        public int hashCode() {
            return Objects.hash(rowSum, colSum);
        }
    }

    public static Sums[] sum(int[][] matrix) {
        Sums[] sums = new Sums[matrix.length];
        for (int row = 0; row < matrix.length; row++) {
            sums[row] = new Sums();
            for (int col = 0; col < matrix[row].length; col++) {
                sums[row].rowSum += matrix[row][col];
                sums[row].colSum += matrix[col][row];
            }
        }
        return sums;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Sums[] sums = new Sums[matrix.length];
        Map<Integer, CompletableFuture<Sums>> future = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            future.put(i, getSums(matrix, i));
        }
        for (Integer integer : future.keySet()) {
            sums[integer] = future.get(integer).get();
        }
        return sums;
    }

    public static CompletableFuture<Sums> getSums(int[][] matrix, int index) {
        return CompletableFuture.supplyAsync(() -> {
            Sums sums = new Sums();
            int row = 0;
            int col = 0;
            for (int i = 0; i < matrix.length; i++) {
                row += matrix[index][i];
                col += matrix[i][index];
            }
            sums.setRowSum(row);
            sums.setColSum(col);
            return sums;
        });
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        for (Sums sums : RolColSum.sum(matrix)) {
            System.out.println(sums.getRowSum() + " " + sums.getColSum());
        }
        for (Sums sums : RolColSum.asyncSum(matrix)) {
            System.out.println(sums.getRowSum() + " " + sums.getColSum());
        }
    }
}
