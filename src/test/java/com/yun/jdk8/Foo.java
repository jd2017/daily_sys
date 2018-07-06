package com.yun.jdk8;
@FunctionalInterface
public interface Foo {
    public int doSomething();
    public default int doSomethingElse() {
        return 1;
    }
}
