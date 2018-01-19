package org.alexburchak.geeksforgeeks.hard.alien_dictionary;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;

/**
 * @author alexburchak
 */
public class AlienDictionary {
    @DataProvider
    public Object[][] dataProvider() {
        return new Object[][]{
                new Object[]{new String[]{"baa", "abcd", "abca", "cab", "cad"}, 4, "bdac"},
                new Object[]{new String[]{"baa", "bacd", "bdac", "dac"}, 4, "acbd"},
                new Object[]{new String[]{"baa", "bdac", "bcd", "dac"}, 4, "abdc"},
                new Object[]{new String[]{"caa", "aaa", "aab" }, 3, "cab"},
                new Object[]{new String[]{"cbb", "cba", "ab" }, 3, "bca"}
        };
    }

    @Test(dataProvider = "dataProvider")
    public void test(String dict[], int k, String expected) {
        assertEquals(evaluate(dict, k), expected);
    }

    private String evaluate(String dict[], int k) {
        Map<Character, List<Character>> chars = new LinkedHashMap<>();

        for (int i = 0; i < dict.length - 1; i++) {
            String left = dict[i];
            String right = dict[i + 1];

            for (int j = 0; j < left.length(); j++) {
                if (right.length() < j) {
                    break;
                }

                char l = left.charAt(j);
                char r = right.charAt(j);

                if (l != r) {
                    chars.compute(r, (c, list) -> {
                        if (list == null) {
                            list = new ArrayList<>();
                        }
                        list.add(l);
                        return list;
                    });
                    break;
                }
            }
        }

        List<Character> result = new ArrayList<>(k);

        for (Map.Entry<Character, List<Character>> entry : chars.entrySet()) {
            char l = entry.getKey();
            List<Character> list = entry.getValue();

            iterate(result, chars, list);

            if (!result.contains(l)) {
                result.add(l);
            }
        }

        return result.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    private void iterate(List<Character> result, Map<Character, List<Character>> chars, List<Character> list) {
        for (Character c : list) {
            if (!result.contains(c)) {
                List<Character> list2 = chars.get(c);

                if (list2 != null) {
                    iterate(result, chars, list2);
                }

                result.add(c);
            }
        }
    }
}
