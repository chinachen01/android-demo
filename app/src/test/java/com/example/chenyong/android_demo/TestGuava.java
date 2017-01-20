package com.example.chenyong.android_demo;


import android.databinding.repacked.google.common.collect.*;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by focus on 17/1/20.
 */

public class TestGuava {
    @Test
    public void foo() {
        ImmutableList<String> strings = ImmutableList.of("123123", "231234", "341235", "asdf", "asdfasdfksdfk");
        ImmutableList<String> after = FluentIterable.from(strings)
                .filter((string) -> string.length() % 2 == 0)
                .toList();
        for (String item :
                after) {
            assertEquals(item.length() %2,  0);
        }
        ArrayList<Integer> integers = Lists.newArrayList(1, 2, 3, 4);
        ImmutableList<Integer> newIntegers = FluentIterable.from(integers)
                .transform((item) -> item*2)
                .toList();
        assertEquals(newIntegers.get(0).intValue(), 2);
        assertEquals(newIntegers.get(1).intValue(), 4);
        assertEquals(newIntegers.get(2).intValue(), 6);
        assertEquals(newIntegers.get(3).intValue(), 8);
        ImmutableList<String> newStrings = FluentIterable.from(integers)
                .transform((item) -> item*2 + "")
                .toList();
        ArrayList<String> allString = Lists.newArrayList(newStrings);
        assertEquals(allString.get(0), "2");
        assertEquals(allString.get(1), "4");
        assertEquals(allString.get(2), "6");
        assertEquals(allString.get(3), "8");
    }
}
