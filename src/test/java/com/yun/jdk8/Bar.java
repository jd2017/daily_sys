package com.yun.jdk8;

/**
 * Java编译器提供了@FunctionalInterface注解以确保接口确实是函数式接口
 * 如果我们错误地将接口定义为非函数接口并用@FunctionalInterface注解了错误的接口，则Java编译器会发出错误
 * 使用这个注解，我们可以确保我们不会错误地创建原本打算用作函数式接口的非函数式接口。需要注意的是，即使在@FunctionalInterface注解不存在的情况下，接口也可以用作函数式接口（可以替代为lambdas，方法引用和构造函数引用），正如我们前面的示例中所见的那样。这类似于@Override注解，
 * 即一个方法是可以被覆盖的，即使它不包含@Override注解。在这两种情况下，注解都是允许编译器执行期望意图的可选技术
 * 重点要注意的是，抽象类，即使它们只包含一个抽象方法，也不是函数式接口。
 */
@FunctionalInterface
public interface Bar {
    public int doSomething();
}
