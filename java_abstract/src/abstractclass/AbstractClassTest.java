package abstractclass;

public class AbstractClassTest {
    public static void main(String[] args) {
        // 创建抽象类,抽象类不能创建对象
        // 假设抽象类让我们创建对象,里面的抽象方法没有方法体,无法执行.所以不让我们创建对象
       // Employee e = new Employee();

        // 创建子类
        Manager manager = new Manager();
        manager.work();

        Cook cook = new Cook();
        cook.work();

    }
}
