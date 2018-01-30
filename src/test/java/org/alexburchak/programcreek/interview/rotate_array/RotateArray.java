package org.alexburchak.programcreek.interview.rotate_array;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author alexburchak
 */
public class RotateArray {
    @DataProvider
    public Object[][] dataProvider() {
        return new Object[][]{
                new Object[]{new int []{1, 2, 3, 4, 5, 6, 7}, 3, new int[]{5, 6, 7, 1, 2, 3, 4}}
        };
    }

    @Test(dataProvider = "dataProvider")
    public void testCopy(int array[], int k, int expected[]) {
        assertEquals(rotateCopy(array, k), expected);
    }

    private int[] rotateCopy(int array[], int k) {
        int result[] = new int[array.length];

        System.arraycopy(array, 0, result, k, array.length - k);
        System.arraycopy(array, array.length - k, result, 0, k);

        return result;
    }

    @Test(dataProvider = "dataProvider")
    public void testShift(int array[], int k, int expected[]) {
        assertEquals(rotateShift(array, k), expected);
    }

    private int[] rotateShift(int array[], int k) {
        int result[] = new int[array.length];

        for (int i = 0, j = k; i < array.length; i++, j++) {
            if (j >= array.length) {
                j -= array.length;
            }
            result[j] = array[i];
        }

        return result;
    }
}
