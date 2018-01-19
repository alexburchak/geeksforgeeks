package org.alexburchak.geeksforgeeks.easy.zigzag_matrix;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author alexburchak
 */
public class ZigZagMatrix {
    @DataProvider
    public Object[][] dataProvider() {
        return new Object[][]{
                new Object[]{3, 3, new int[][]{new int[]{1, 2, 3}, new int[]{4, 5, 6}, new int[]{7, 8, 9}}, new int[]{1, 2, 4, 7, 5, 3, 6, 8, 9}},
                new Object[]{4, 3, new int[][]{new int[]{1, 2, 3, 4}, new int[]{5, 6, 7, 8}, new int[]{9, 10, 11, 12}}, new int[]{1, 2, 5, 9, 6, 3, 4, 7, 10, 11, 8, 12}},
                new Object[]{3, 4, new int[][]{new int[]{1, 2, 3}, new int[]{4, 5, 6}, new int[]{7, 8, 9}, new int[]{10, 11, 12}}, new int[]{1, 2, 4, 7, 5, 3, 6, 8, 10, 11, 9, 12}},
        };
    }

    @Test(dataProvider = "dataProvider")
    public void test(int width, int height, int data[][], int expected[]) {

        assertEquals(evaluate(width, height, data), expected);
    }

    private int[] evaluate(int width, int height, int data[][]) {
        int result[] = new int[width * height];
        int pos = 0;

        int x = 0;
        int y = 0;
        boolean x_inc = true;

        while (true) {
            result[pos++] = data[y][x];

            if (x == width - 1 && y == height - 1) {
                break;
            }

            if (x_inc) {
                if (x < width - 1) {
                    ++x;
                    if (y > 0) {
                        --y;
                    } else {
                        x_inc = false;
                    }
                } else {
                    if (y < height - 1) {
                        ++y;
                    } else {
                        ++x;
                    }
                    x_inc = false;
                }
            } else {
                if (y < height - 1) {
                    ++y;

                    if (x > 0) {
                        --x;
                    } else {
                        x_inc = true;
                    }
                } else {
                    if (x < width - 1) {
                        ++x;
                    } else {
                        ++y;
                    }
                    x_inc = true;
                }
            }
        }

        return result;
    }
}
