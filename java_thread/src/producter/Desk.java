package producter;

public class Desk {

    //定义一个标记
    //true 就表示桌子上有汉堡包的,此时允许吃货执行
    //false 就表示桌子上没有汉堡包的,此时允许厨师执行
    public static boolean flag = false;

    //汉堡包的总数量
    public static int count = 10;

    // 锁对象
    public static final Object lock = new Object();



}
