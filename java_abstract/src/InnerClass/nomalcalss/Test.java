package InnerClass.nomalcalss;

public class Test {
    public static void main(String[] args) {
        // 宿主 外部类对象
      //  Outer outer = new Outer();
      //  Outer.Inner inner = outer.new Inner();
        // 方式1
        Outer.Inner inner = new Outer().new Inner(); // 合起来

        inner.innerMethod();

        // 方式2
        Outer.Inner inner2 = new Outer().getInstance();



        // 静态内部类  总结：静态内部类与其他类的用法完全一样。只是访问的时候需要加上外部类.内部类

        StaticOuer.Inner inner1 = new StaticOuer.Inner("张三");
        inner1.showName();

    }
}
