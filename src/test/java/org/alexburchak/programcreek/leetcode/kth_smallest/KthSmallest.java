package org.alexburchak.programcreek.leetcode.kth_smallest;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author alexburchak
 */
public class KthSmallest {
    private static final int MATRIX1[][] = new int[][]{
            new int[]{ 1,  5,  6,  8},
            new int[]{ 7,  7, 12, 13},
            new int[]{ 9, 15, 16, 18},
            new int[]{10, 17, 18, 20}
    };
    private static final int MATRIX2[][] = new int[][]{
            new int[]{ 1,  5,  9},
            new int[]{10, 11, 13},
            new int[]{12, 13, 15}
    };
    private static final int MATRIX3[][] = new int[][]{
            new int[]{1, 3, 5},
            new int[]{5, 6, 8},
            new int[]{6, 9, 9}
    };

    @DataProvider
    public Object[][] dataProvider() {
        return new Object[][]{
                new Object[]{MATRIX1, 1, 1},
                new Object[]{MATRIX1, 2, 5},
                new Object[]{MATRIX1, 3, 6},
                new Object[]{MATRIX1, 4, 7},
                new Object[]{MATRIX1, 5, 7},
                new Object[]{MATRIX1, 6, 8},
                new Object[]{MATRIX1, 7, 9},
                new Object[]{MATRIX1, 8, 10},
                new Object[]{MATRIX1, 9, 12},
                new Object[]{MATRIX1, 10, 13},
                new Object[]{MATRIX1, 11, 15},
                new Object[]{MATRIX1, 12, 16},
                new Object[]{MATRIX1, 13, 17},
                new Object[]{MATRIX1, 14, 18},
                new Object[]{MATRIX1, 15, 18},
                new Object[]{MATRIX1, 16, 20},
                new Object[]{MATRIX2, 1, 1},
                new Object[]{MATRIX2, 2, 5},
                new Object[]{MATRIX2, 3, 9},
                new Object[]{MATRIX2, 4, 10},
                new Object[]{MATRIX2, 5, 11},
                new Object[]{MATRIX2, 6, 12},
                new Object[]{MATRIX2, 7, 13},
                new Object[]{MATRIX2, 8, 13},
                new Object[]{MATRIX2, 9, 15},
                new Object[]{MATRIX3, 1, 1},
                new Object[]{MATRIX3, 2, 3},
                new Object[]{MATRIX3, 3, 5},
                new Object[]{MATRIX3, 4, 5},
                new Object[]{MATRIX3, 5, 6},
                new Object[]{MATRIX3, 6, 6},
                new Object[]{MATRIX3, 7, 8},
                new Object[]{MATRIX3, 8, 9},
                new Object[]{MATRIX3, 9, 9}
        };
    }

    @Test(dataProvider = "dataProvider")
    public void test(int matrix[][], int k, int expected) {
        assertEquals(kthSmallest(matrix, k), expected);
    }

    private int kthSmallest(int[][] matrix, int k) {
        int rows[] = new int[matrix[0].length];
        int cur = matrix[0][0];

        while (true) {
            Integer next = null;

            for (int col = 0; col < rows.length && k > 0; ) {
                int row = rows[col];

                if (row < matrix.length) {
                    int value = matrix[row][col];

                    if (value != cur) {
                        if (next == null) {
                            next = value;
                        } else {
                            next = Math.min(next, value);
                        }
                    }

                    if (value == cur) {
                        if (--k == 0) {
                            return cur;
                        }
                        ++rows[col];
                        continue;
                    }
                }

                ++col;
            }

            cur = next;
        }
    }
}
