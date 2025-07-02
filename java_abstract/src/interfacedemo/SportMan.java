package interfacedemo;

/**
 *接口中的抽象方法: 默认会自动加上public abstract修饰程序员无需自己手写
 *
 * 成员变量默认会加上： public static final修饰  并且是静态化的变量可以直接用接口名访问，所以也叫常量
 * 常量命名规范建议字母全部大写，多个单词用下划线连接
 *
 *
 * 接口是实现
 * class 类名 implements 接口1,接口2,接口3...{
 *
 * }
 *
 * 规范
 *1. 必须重写实现的全部接口中所有抽象方法
 * 2. 接口体现的是一种规范，接口对实现类是一种强制性的约束，要么全部完成接口申明的功能，要么自己也定义成抽象类。这正是一种强制性的规范
 *
 */
public interface SportMan {
    // public static final int AGE = 12 ;
    int AGE = 12;

    //    public abstract
    void run();
    void law(); // 遵守法律
    String compittion(String project);
}
