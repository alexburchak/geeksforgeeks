package org.alexburchak.geeksforgeeks.medium.max_length_chain;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;

/**
 * @author alexburchak
 */
public class MaxLengthChain {
    @DataProvider
    public Object[][] dataProvider() {
        return new Object[][]{
                new Object[]{
                        new int[][]{
                                new int[]{5, 24},
                                new int[]{39, 60},
                                new int[]{15, 28},
                                new int[]{27, 40},
                                new int[]{50, 90}
                        },
                        new int[][]{
                                new int[]{5, 24},
                                new int[]{27, 40},
                                new int[]{50, 90}
                        },
                },
                new Object[]{
                        new int[][]{
                                new int[]{5, 10},
                                new int[]{1, 11}
                        },
                        new int[][]{
                                new int[]{5, 10}
                        },
                }
        };
    }

    @Test(dataProvider = "dataProvider")
    public void test(int pairs[][], int expected[][]) {
        assertEquals(
                evaluate(pairs).stream()
                        .map(a -> a[0] + "-" + a[1])
                        .collect(Collectors.joining(" ")),
                Arrays.stream(expected)
                        .map(a -> a[0] + "-" + a[1])
                        .collect(Collectors.joining(" "))
        );
    }

    private List<int[]> evaluate(int pairs[][]) {
        AtomicReference<Integer> state = new AtomicReference<>(null);

        return Arrays.stream(pairs)
                .sorted(Comparator.comparingInt(a2 -> a2[1]))
                .filter(a -> {
                    if (state.get() == null || state.get() < a[0]) {
                        state.set(a[1]);
                        return true;
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }
}
