package org.alexburchak.programcreek.interview.word_ladder;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.testng.Assert.assertEquals;

/**
 * @author alexburchak
 */
public class WordLadder {
    @DataProvider
    public Object[][] dataProvider() {
        return new Object[][]{
                // hit -> hot -> dot -> dog -> cog
                new Object[]{"hit", "cog", new String[]{"hot", "dot", "dog", "lot", "log"}, 5},
                // hit -> hot -> dot -> log -> cog
                new Object[]{"hit", "cog", new String[]{"hot", "dot", "lot", "log"}, 5},
                // none
                new Object[]{"hit", "cog", new String[]{"hot", "dot", "lot"}, -1},
        };
    }

    @Test(dataProvider = "dataProvider")
    public void test(String start, String end, String dict[], int expected) {
        assertEquals(compute(start, end, dict), expected);
    }

    private int compute(String start, String end, String dict[]) {
        if (start.equals(end)) {
            return 0;
        }

        Set<String> chars = Stream.concat(Stream.of(end), Arrays.stream(dict))
                .flatMap(w -> w.chars()
                        .mapToObj(c -> String.valueOf((char) c))
                )
                .collect(Collectors.toSet());

        int length = compute(start, end, new HashSet<>(Arrays.asList(dict)), chars);
        return length != Integer.MAX_VALUE
                ? length + 1
                : -1;
    }

    private int compute(String start, String end, Set<String> dict, Set<String> chars) {
        int length = Integer.MAX_VALUE;

        for (int i = 0; i < start.length(); i++) {
            String ch1 = start.substring(i, i + 1);

            for (String ch2 : chars) {
                if (ch1.equals(ch2)) {
                    continue;
                }

                String replace = start.replaceAll(ch1, ch2);

                if (replace.equals(end)) {
                    return 1;
                }

                if (dict.contains(replace)) {
                    dict.remove(replace);

                    length = Math.min(length, compute(replace, end, dict, chars));

                    dict.add(replace);
                }
            }
        }

        return length != Integer.MAX_VALUE
                ? length + 1
                : Integer.MAX_VALUE;
    }
}
