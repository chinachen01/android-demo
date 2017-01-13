package com.example.chenyong.android_demo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void nullAble() {
        Person person = null;
        countString(person);
    }
    public int countString(Person person) {
        return person.getName().length();
    }
}
class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }
    public String getName(){
        return name;
    }
}
