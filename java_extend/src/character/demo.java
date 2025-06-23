package character;


public class demo {
    public static void main(String[] args) {

        /**
         * 能继承 ： public
         * 不能继承
         * 1.子类不能继承父类的构造方法
         * 2.私有方法 变量 的不能直接使用
         *
         * 构造方法的作用是初始化对象成员变量数据的。所以子类的初始化过程中，必须先执行父类的初始化动作。
         * 子类的构造方法中默认有一个`super()` ，表示调用父类的构造方法，父类成员变量初始化后，才可以给子类使用
         *
         * * 子类构造方法执行的时候，都会在第一行默认先调用父类无参数构造方法一次。
         * * 子类构造方法的第一行都隐含了一个**super()**去调用父类无参数构造方法，**super()**可以省略不写。
         */
        Zi zi = new Zi();
        System.out.println(zi.num1);
      //  System.out.println(zi.num2); // 私有的子类无法使用
        zi.show1();
       // zi.show2(); // 私有的子类无法使用


        /**
         * 变量 方法 重名
         * 如果子类父类中出现**不重名**的成员变量，这时的访问是**没有影响的**
         *
         *
         * 父类中出现了同名的成员变量时，子类会优先访问自己对象中的成员变量
         * 子父类中出现了同名的成员变量时，在子类中需要访问父类中非私有成员变量时，需要使用`super` 关键字，修饰父类成员变量，类似于之前学过的 `this`
         */
        System.out.println(zi.num3); // 不重名
        System.out.println(zi.num1); // 重名 输出 100 子类会优先访问自己对象中的成员变量
        System.out.println(zi.show()); // 重名 输出 100 子类会优先访问自己对象中的成员变量


        /**
         * 方法重写
         * **方法重写** ：子类中出现与父类一模一样的方法时（返回值类型，方法名和参数列表都相同），
         * 会出现覆盖效果，也称为重写或者复写。**声明不变，重新实现**。
         *
         *
         * 发生在子父类之间的关系。
         * 子类继承了父类的方法，但是子类觉得父类的这方法不足以满足自己的需求，子类重新写了一个与父类同名的方法，以便覆盖父类的该方 法。
         *
         * 注意 ：
         * 1. 方法重写是发生在子父类之间的关系。
         * 2. 子类方法覆盖父类方法，必须要保证权限大于等于父类权限。
         * 3. 子类方法覆盖父类方法，返回值类型、函数名和参数列表都要一模一样。
         */

        zi.show1();

        /**
         * super和this 调用构造
         *
         * super(...) -- 调用父类的构造方法，根据参数匹配确认
         * this(...) -- 调用本类的其他构造方法，根据参数匹配确认
         *
         *this(...)
         *  *    默认是去找本类中的其他构造方法，根据参数来确定具体调用哪一个构造方法。
         *  *    为了借用其他构造方法的功能。
         */

        Zi zi1 = new Zi(5,2,3);

    }
}


class Fu {
    public int num1 = 10;
    private int num2 = 20;

    public Fu(){
        System.out.println("父类无参");
    }

    public Fu(int num1,int num2){
        System.out.println("父类有参");
        this.num1 = num1;
        this.num2 = num2;
    }
    public void show1() {
        System.out.println("show1");
    }

    private void show2() {
        System.out.println("show2");
    }

    public int getNum2() {
        return num2;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }


}

class Zi extends Fu {
    public int num1 = 100;
    public int num3 = 20;

    public Zi(){
        this(1,2,3); // this(..)可以调用本类中的其他构造方法
        //super(); // 调用父类无参构造方法,默认就存在，可以不写，必须再第一行
        System.out.println("我是子类 无参");
    }

    public Zi(int num1,int num2,int num3){
        super(num1,num2); // 调用父类有参构造方法Fu(int num1,int num2)  初始化num1和num2
        System.out.println("我是子类 有参");
        this.num3 = num3;
    }

    public boolean show(){
        System.out.println(super.num1); // 重名 super 调用父类的
        return true;
    }

    //Override 重写注解校验  这个注解标记的方法，就说明这个方法必须是重写父类的方法，否则编译阶段报错
    // 建议重写都加上这个注解，一方面可以提高代码的可读性，一方面可以防止重写出错！
    @Override
    public void show1(){
        System.out.println("父类show1已经不能满足我了 我要自己实现 ");
    }
}