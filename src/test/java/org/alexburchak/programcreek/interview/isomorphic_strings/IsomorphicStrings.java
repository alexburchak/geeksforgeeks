package org.alexburchak.programcreek.interview.isomorphic_strings;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.testng.Assert.assertEquals;

/**
 * @author alexburchak
 */
public class IsomorphicStrings {
    @DataProvider
    public Object[][] dataProvider() {
        return new Object[][]{
                new Object[]{"egg", "add", true},
                new Object[]{"add", "egg", true},
                new Object[]{"foo", "bar", false},
                new Object[]{"bar", "foo", false}
        };
    }

    @Test(dataProvider = "dataProvider")
    public void test(String string1, String string2, boolean expected) {
        assertEquals(compare(string1, string2), expected);
    }

    private boolean compare(String string1, String string2) {
        if (string1.length() != string2.length()) {
            return false;
        }

        Map<Character, Character> map = new HashMap<>();
        Set<Character> set = new HashSet<>();

        for (int i = 0; i < string1.length(); i++) {
            char ch1 = string1.charAt(i);
            char ch2 = string2.charAt(i);

            Character m = map.get(ch1);
            if (m == null) {
                if (set.contains(ch2)) {
                    return false;
                }
                map.put(ch1, ch2);
                set.add(ch2);
            } else {
                if (ch2 != m) {
                    return false;
                }
            }
        }
        return true;
    }
}
