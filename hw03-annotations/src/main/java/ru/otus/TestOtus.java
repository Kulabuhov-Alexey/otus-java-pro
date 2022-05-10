package ru.otus;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

public class TestOtus {

    @Before
    public void before() {
        System.out.println("Before method");
    }

    @Before
    public void before2() {
        System.out.println("Before2 method");
    }

    @Test
    public void test() {
        System.out.println("Test method");
    }

    @Test
    public void test2() {
        System.out.println("Test2 method");
        throw new RuntimeException();
    }

    @After
    public void after() {
        System.out.println("After method");
    }
}
