package com.devent.test.handler;

import java.util.ArrayList;
import java.util.List;

public class TestHandler {

    private List<Integer> testList;

    public TestHandler() {
    }

    public TestHandler createList() {
        this.testList = new ArrayList<>();
        return this;
    }

    public TestHandler testHandler(Integer num) {
        testList.add(num);
        testList.forEach(System.out::println);
        System.out.println("ending-------------");
        return this;
    }


    public static void main(String[] args) {
        new TestHandler().createList().testHandler(1).testHandler(2).testHandler(3);
    }
}
