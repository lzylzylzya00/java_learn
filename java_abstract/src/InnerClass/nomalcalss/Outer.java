package InnerClass.nomalcalss;

/**
 *内部类 ：  将一个类A定义在另一个类B里面，里面的那个类A就称为**内部类**，B则称为**外部类**。可以把内部类理解成寄生，外部类理解成宿主。
 *
 * 什么时候使用 ： 一个事物内部还有一个独立的事物，内部的事物脱离外部的事物无法独立使用
 * 1. 人里面有一颗心脏。
 * 2. 汽车内部有一个发动机。
 *
 * 分类
 * 1. **成员内部内**，类定义在了成员位置 (类中方法外称为成员位置，无static修饰的内部类)
 * 2. **静态内部类**，类定义在了成员位置 (类中方法外称为成员位置，有static修饰的内部类)
 * 3. **局部内部类**，类定义在方法内
 * 4. **匿名内部类**  没有名字的内部类，可以在方法中，也可以在类中方法外
 */
public class Outer { // // 外部类
    private int a = 30;


    /**
     * 成员内部类可以被一些修饰符所修饰，比如： private，默认，protected，public，static等
     *
     * 对象中有一个隐含的Outer.this记录外部类对象的地址值
     */
    public class Inner{  // // 在成员位置定义一个类
        private int a = 20;

        public void innerMethod(){
            int a = 10;
            System.out.println(a); // 10   答案：a
            System.out.println(this.a); // 20	答案：this.a
            System.out.println(Outer.this.a); // 30	答案：Outer.this.a  内部类持有外部类的 引用
        }
    }


    // 方式2
    public Inner getInstance(){
        return new Inner();
    }
}
