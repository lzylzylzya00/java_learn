java

## java面向对象

面向对象的三大特性 封装 继承 多态

### 封装

封装思想

​		**对象代表什么，就得封装对应的数据，并提供数据对应的行为**

封装代码实现

​	将类的某些信息隐藏在类内部，不允许外部程序直接访问，而是通过该类提供的方法来实现对隐藏信息的操作和访问

​	成员变量private，提供对应的getXxx()/setXxx()方法



类的定义：对一类事务的抽象



类的组成：1.属性 事物的特性 --手机的价格 品牌  （类成员变量）

​					2.行为  事务的行为 -- 手机打电话 （类方法）



类的使用： 类名 对象名 = new 类名（）；



对象的内存图 ：

有一个手机类java

```java
public class Phone{
    String brand;
    double price;
    String color;
    
    public void call(String who){
        System.out.println("给" + who + "打电话");
    }
}

public classs Demo01PhoneOne{
    public static void main(){
        Phone one = new Phone();
        System.out.println(one.brand);
        System.out.println(one.price);
        System.out.println(one.color);
        
        one.branch = "苹果"；
        one.price = 8388；
        one.color = "黑色"；
            
        System.out.println(one.brand);
        System.out.println(one.price);
        System.out.println(one.color);
        
        one.call("乔布斯");
            
    }
}
```

![image-20250620105327133](C:\Users\lzy\AppData\Roaming\Typora\typora-user-images\image-20250620105327133.png)

类加载过程：

1.在程序main() 运行之前，方法区最先有数据，把类Phone 和 Demo01PhoneOne 加载到方法区

2.执行main()方法  main方法进栈

3.main方法执行 Phone one = new Phone(); 把 Phone one 放到栈中，把new Phone,放到堆中，并且初始化数据，String null ，double 0.0  ，把方法区的成员方法地址0x333赋值给栈中的成员方法 。把new Phone()也赋值一个地址0x666

4.main方法执行打印语句，one地址 0x666 找到堆中的成员变量。  

5.main方法执行one的赋值，one地址 0x666 找到堆中的成员变量，并且赋值

6.main方法执行打印语句，one地址 0x666 找到堆中的成员变量。

7.main方法执行one.call方法，one地址 0x666找到堆中成员方法的地址0x333,0x333在方法区找到call方法运行。

8.call方法运行，call方法进栈运行，然后打印一句话，运行完后，call方法就会出栈，从内存中释放

9.main方法还没运行完，接下来在运行sendMessage()方法，同理运行完出栈，最后main方法运行完也要出栈，整个程序运行结束



类中的成员变量和局部

* 类中位置不同：成员变量（类中方法外）局部变量（方法内部或方法声明上）
* 内存中位置不同：成员变量（堆内存）局部变量（栈内存）
* 生命周期不同：成员变量（随着对象的存在而存在，随着对象的消失而消失）局部变量（随着方法的调用而存在，醉着方法的调用完毕而消失）
* 初始化值不同：成员变量（有默认初始化值）局部变量（没有默认初始化值，必须先定义，赋值才能使用）



类的构造方法

作用：创建对象   Student stu = **new Student();**

格式：

public class 类名{

​        修饰符 类名( 参数 ) {

​        }

}

功能：主要是完成对象数据的初始化



注意事项：

构造方法的创建

​	如果没有定义构造方法，系统将给出一个默认的无参数构造方法

​	如果定义了构造方法，系统将不再提供默认的构造方法

构造方法的重载

​	如果自定义了带参构造方法，还要使用无参数构造方法，就必须再写一个无参数构造方法

重要功能！

​	可以使用带参构造，为成员变量进行初始化



public privade this关键字

​	this 本质 ：当前对象的地址 方法调用者的地址

 ![image-20250620114327871](C:\Users\lzy\AppData\Roaming\Typora\typora-user-images\image-20250620114327871.png)

​	

this修饰的变量用于指代成员变量，其主要作用是（区分局部变量和成员变量的重名问题）

* 方法的形参如果与成员变量同名，不带this修饰的变量指的是形参，而不是成员变量
* 方法的形参没有与成员变量同名，不带this修饰的变量指的是成员变量



### 继承

引入

​	假如多个类中存在相同属性和行为时，我们可以将这些内容抽取到单独一个类中，那么多个类无需再定义这些属性和行为，只要**继承**那一个类即可

定义：

​    **继承**：就是子类继承父类的**属性**和**行为**，使得子类对象可以直接具有与父类相同的属性、相同的行为。子类可以直接访问父类中的**非私有**的属性和行为

好处：

​	提高**代码的复用性**（减少代码冗余，相同代码重复利用）。使类与类之间产生了关系。

#### 	特性

##### 	能继承那些

​		1.能继承 ： public  2. 不能继承 父类的构造方法 和 privite

```java
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
```

```java
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

public static void main(String[] args) {
  //  1.能继承 ： public  2. 不能继承 父类的构造方法 和 privite
     Zi zi = new Zi();
     System.out.println(zi.num1);
    //  System.out.println(zi.num2); // 私有的子类无法使用
        zi.show1();
       // zi.show2(); // 私有的子类无法使用
    
}
```

##### 继承的特性--成员变量

成员变量重名  java

子父类中出现了同名的成员变量时，子类会优先访问自己对象中的成员变量。如果此时想访问父类成员变量如何解决呢？我们可以使用super关键字



子父类中出现了同名的成员变量时，在子类中需要访问父类中非私有成员变量时，需要使用`super` 关键字，修饰父类成员变量，类似于之前学过的 `this` 。



**使用格式：**

```java
super.父类成员变量名
```



需要注意的是：**super代表的是父类对象的引用，this代表的是当前对象的引用。**

```java
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

  public boolean show(){ // zi的方法
        System.out.println(super.num1); // 重名 super 调用父类的
        return true;
    }
```

成员变量不重名  

​	直接使用 不影响



##### 继承的特性--成员方法

成员方法不重名

如果子类父类中出现**不重名**的成员方法，这时的调用是**没有影响的**。对象调用方法时，会先在子类中查找有没有对应的方法，若子类中存在就会执行子类中的方法，若子类中不存在就会执行父类中相应的方法。代码如下

```Java
class Fu {
	public void show() {
		System.out.println("Fu类中的show方法执行");
	}
}
class Zi extends Fu {
	public void show2() {
		System.out.println("Zi类中的show2方法执行");
	}
}
public  class Demo05 {
	public static void main(String[] args) {
		Zi z = new Zi();
     	//子类中没有show方法，但是可以找到父类方法去执行
		z.show(); 
		z.show2();
	}
}
```

成员方法重名

如果子类父类中出现**重名**的成员方法，则创建子类对象调用该方法的时候，子类对象会优先调用自己的方法。

```Java
class Fu {
	public void show() {
		System.out.println("Fu show");
	}
}
class Zi extends Fu {
	//子类重写了父类的show方法
	public void show() {
		System.out.println("Zi show");
	}
}
public class ExtendsDemo05{
	public static void main(String[] args) {
		Zi z = new Zi();
     	// 子类中有show方法，只执行重写后的show方法
		z.show();  // Zi show
	}
}
```



方法重写

**方法重写** ：子类中出现与父类一模一样的方法时（返回值类型，方法名和参数列表都相同），会出现覆盖效果，也称为重写或者复写。**声明不变，重新实现**。

@Override:注解，重写注解校验！

* 这个注解标记的方法，就说明这个方法必须是重写父类的方法，否则编译阶段报错。

* 建议重写都加上这个注解，一方面可以提高代码的可读性，一方面可以防止重写出错！



使用场景 

发生在子父类之间的关系。
子类继承了父类的方法，但是子类觉得父类的这方法不足以满足自己的需求，子类重新写了一个与父类同名的方法，以便覆盖父类的该方 法



```java
   //Override 重写注解校验  这个注解标记的方法，就说明这个方法必须是重写父类的方法，否则编译阶段报错
    // 建议重写都加上这个注解，一方面可以提高代码的可读性，一方面可以防止重写出错！
    @Override
    public void show1(){
        System.out.println("父类show1已经不能满足我了 我要自己实现 ");
    }
```

##### 继承特性--构造方法

1. 构造方法的名字是与类名一致的。所以子类是无法继承父类构造方法的。
2. 构造方法的作用是初始化对象成员变量数据的。所以子类的初始化过程中，必须先执行父类的初始化动作。子类的构造方法中默认有一个`super()` ，表示调用父类的构造方法，父类成员变量初始化后，才可以给子类使用

**继承后子类构方法器特点:子类所有构造方法的第一行都会默认先调用父类的无参构造方法**

```Java
class Person {
    private String name;
    private int age;

    public Person() {
        System.out.println("父类无参");
    }

    // getter/setter省略
}

class Student extends Person {
    private double score;

    public Student() {
        //super(); // 调用父类无参,默认就存在，可以不写，必须再第一行
        System.out.println("子类无参");
    }
    
     public Student(double score) {
        //super();  // 调用父类无参,默认就存在，可以不写，必须再第一行
        this.score = score;    
        System.out.println("子类有参");
     }

}

public class Demo07 {
    public static void main(String[] args) {
        Student s1 = new Student();
        System.out.println("----------");
        Student s2 = new Student(99.9);
    }
}

输出结果：
父类无参
子类无参
----------
父类无参
子类有参
```



构造方法调用--super()和this()

super和this完整的用法如下

```Java
this.成员变量    	--    本类的
super.成员变量    	--    父类的

this.成员方法名()  	--    本类的    
super.成员方法名()   --    父类的
    
    
super(...) -- 调用父类的构造方法，根据参数匹配确认
this(...) -- 调用本类的其他构造方法，根据参数匹配确认    
    
```

super(...)  演示

```Java
class Person {
    private String name ="凤姐";
    private int age = 20;

    public Person() {
        System.out.println("父类无参");
    }
    
    public Person(String name , int age){
        this.name = name ;
        this.age = age ;
    }

    // getter/setter省略
}

class Student extends Person {
    private double score = 100;

    public Student() {
        //super(); // 调用父类无参构造方法,默认就存在，可以不写，必须再第一行
        System.out.println("子类无参");
    }
    
     public Student(String name ， int age，double score) {
        super(name ,age);// 调用父类有参构造方法Person(String name , int age)初始化name和age
        this.score = score;    
        System.out.println("子类有参");
     }
      // getter/setter省略
}

public class Demo07 {
    public static void main(String[] args) {
        // 调用子类有参数构造方法
        Student s2 = new Student("张三"，20，99);
        System.out.println(s2.getScore()); // 99
        System.out.println(s2.getName()); // 输出 张三
        System.out.println(s2.getAge()); // 输出 20
    }
}
```

注意：

子类的每个构造方法中均有默认的super()，调用父类的空参构造。手动调用父类构造会覆盖默认的super()。**

**super() 和 this() 都必须是在构造方法的第一行，所以不能同时出现。**

super(..)是根据参数去确定调用父类哪个构造方法的。

上面案例图解

​	在每次创建子类对象时，先初始化父类空间，再创建其子类对象本身。目的在于子类对象中包含了其对应的父类空间，便可以包含其父类的成员，如果父类成员非private修饰，则子类可以随意使用父类成员。代码体现在子类的构造七调用时，一定先调用父类的构造方法。理解图解如下：

![image-20250625174657526](C:\Users\lzy\AppData\Roaming\Typora\typora-user-images\image-20250625174657526.png)



总结：

* 子类构造方法执行的时候，都会在第一行默认先调用父类无参数构造方法一次。
* 子类构造方法的第一行都隐含了一个**super()**去调用父类无参数构造方法，**super()**可以省略不写。

this(...)

 *    默认是去找本类中的其他构造方法，根据参数来确定具体调用哪一个构造方法。
 *    为了借用其他构造方法的功能。

```java
package com.itheima._08this和super调用构造方法;
/**
 * this(...):
 *    默认是去找本类中的其他构造方法，根据参数来确定具体调用哪一个构造方法。
 *    为了借用其他构造方法的功能。
 *
 */
public class ThisDemo01 {
    public static void main(String[] args) {
        Student xuGan = new Student();
        System.out.println(xuGan.getName()); // 输出:徐干
        System.out.println(xuGan.getAge());// 输出:21
        System.out.println(xuGan.getSex());// 输出： 男
    }
}

class Student{
    private String name ;
    private int age ;
    private char sex ;

    public Student() {
  // 很弱，我的兄弟很牛逼啊，我可以调用其他构造方法：Student(String name, int age, char sex)
        this("徐干",21,'男');
    }

    public Student(String name, int age, char sex) {
        this.name = name ;
        this.age = age   ;
        this.sex = sex   ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }
}
```



## Java final static关键字

### final

声明变量

- 对于基本类型，final 使数值不变；
- 对于引用类型，final 使引用不变，也就不能引用其它对象，但是被引用的对象本身是可以修改的。

```java
// final test 基本类型
class A{
    final int x = 1;
  //  x = 2 // 对于基本类型，final 使数值不变
    int a;

    final void test1(){ // 不能被重写

    }
}

// 引用类型
   final A y = new A();
        y.a = 2; // 被引用的对象本身是可以修改
      //  y = new A();  // 对于引用类型，final 使引用不变，也就不能引用其它对象

```

声明方法

声明方法不能被子类重写

声明类

不允许被继承



### static

声明变量

- 静态变量：又称为类变量，也就是说这个变量属于类的，类所有的实例都共享静态变量，可以直接通过类名来访问它。静态变量在内存中只存在一份。
- 实例变量：每创建一个实例就会产生一个实例变量，它与该实例同生共死。

```java
class C{
    private int x; // 实例变量
    private static int y; // 静态变量

  
}
```

声明方法

静态方法在类加载的时候就存在了，它不依赖于任何实例。所以静态方法必须有实现，也就是说它不能是抽象方法。

只能访问所属类的静态字段和静态方法，方法中不能有 this 和 super 关键字，因为这两个关键字与具体对象关联。

```java
  //只能访问所属类的静态字段和静态方法，方法中不能有 this 和 super 关键字，因为这两个关键字与具体对象关联
public class A {

    private static int x;
    private int y;
    public static void fun1(){ // 静态方法
      //  int a = x ; // x是非静态 所以 不能访问
      //  int a = this.y; // this 不行
        int a = y;
    }
}
```

静态语句块

```
public class A {
    static {
        System.out.println("123");
    }

    public static void main(String[] args) {
        A a1 = new A();
        A a2 = new A();
    }
}
```

结果 ： 123

静态内部类

非静态内部类依赖于外部类的实例，也就是说需要先创建外部类实例，才能用这个实例去创建非静态内部类。而静态内部类不需要。



```java
class OutClass {
    class InnerClass2{}

    static class StaticInnerClass{}
}
main(){
       OutClass outClass = new OutClass();
     //   InnerClass innerClass = OutClass.InnerClass; // 拿不到 非静态
        OutClass.InnerClass2 innerClass = outClass.new InnerClass2();
        OutClass.StaticInnerClass staticInnerClass = new OutClass.StaticInnerClass(); // 直接拿到
}
```



初始化顺序

![image-20250521162107423](C:\Users\lzy\AppData\Roaming\Typora\typora-user-images\image-20250521162107423.png)















java网络编程

java集合

## java四种引用

### 强引用

只要强引用存在，垃圾回收器将永远不会回收被引用的对象，哪怕内存不足时，
JVM也会直接抛出OutOfMemoryError

java默认强引用

以下代码jvm设置堆内存大小  -Xms2M -Xmx3M  起始2m  最大3m

```java
 /**
     * 只要强引用存在，垃圾回收器将永远不会回收被引用的对象，哪怕内存不足时，
     * JVM也会直接抛出OutOfMemoryError
     */
    private static void strongReference(){
        byte[] buff = new byte[4*1024*1024]; // 只要obj还指向Object对象，Object对象就不会被回收
    }

结果直接抛出oom
```

### 软引用

在内存足够的时候，软引用对象不会被回收，只有在内存不足时， 在内存足够的时候，软引用对象不会被回收，只有在内存不足时，系统则会回收软引用对象，如果回收了软引用对象之后仍然没有足够的内存，
才会抛出内存溢出异常。这种特性常常被用来实现缓存技术，比如网页缓存，图片缓存等

```java
 private static ArrayList<Object> list = new ArrayList<>();
    private static void softReference(){


        for (int i = 0; i < 10; i++) {
            byte[] buff = new byte[1024 * 1024];
            SoftReference<byte[]> sr = new SoftReference<>(buff);
            list.add(sr); // 10m
        }

        System.gc();
        for (int i = 0; i < list.size(); i++){
            Object obj = ((SoftReference) list.get(i)).get();  // 内存不足的情况下，软引用将会被自动回收
            System.out.println(obj);
        }

    }

```

结果：

![image-20250521144647756](C:\Users\lzy\AppData\Roaming\Typora\typora-user-images\image-20250521144647756.png)

可以看到只有一个实例其它都被释放了



byte[] buff 引用指向对象, 且 buff 是一个strong reference, 但是 SoftReference sr 指向的对象仍然被回收了，
 这是因为Java的编译器发现了在之后的代码中, **buff 已经没有被使用了, 所以自动进行了优化**  softReference2是一个使用的例子

```java
 private static void softReference2() {
        byte[] buff = null;

        for (int i = 0; i < 10; i++) {
            buff = new byte[1024 * 1024];
            SoftReference<byte[]> sr = new SoftReference<>(buff);
            list.add(sr);
        }

        System.gc(); //主动通知垃圾回收

        for(int i=0; i < list.size(); i++){
            Object obj = ((SoftReference) list.get(i)).get();
            System.out.println(obj);
        }

        System.out.println("buff: " + buff.toString());  // buff 使用了  buff强引用不会被回收 oom
    }
```

运行结果： oom

### 弱引用

无论内存是否足够，只要 JVM 开始进行垃圾回收，那些被弱引用关联的对象都会被回收

```java
   private static void weakReference(){
        for (int i = 0; i < 10; i++){
            byte[] buff = new byte[1024*1024];
            WeakReference<byte[]> sr = new WeakReference<>(buff);
            list.add(sr);
        }

        System.gc(); //主动通知垃圾回收

        for(int i=0; i < list.size(); i++){
            Object obj = ((WeakReference) list.get(i)).get();
            System.out.println(obj);
        }
    }
```

结果： 全是null  GC回收的时候 全部被释放了



### 虚引用

PhantomReference<A>  就是没有任何引用 



### Java内存模型

![image-20250521150028047](C:\Users\lzy\AppData\Roaming\Typora\typora-user-images\image-20250521150028047.png)

所有的实类都在堆中  GC回收的对象也是在堆中

![image-20250521145731647](C:\Users\lzy\AppData\Roaming\Typora\typora-user-images\image-20250521145731647.png)



java_io

java并发

## Java多线程基础

### 线程的创建方式

1.Thread方式  

实现 Thread接口 然后直接 调用start

```java
public class MyThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {

            //  Thread.sleep(1000);

            System.out.println("线程名字 = " + Thread.currentThread().getName() + i);

        }
    }
}

  public void creatThread(){
        MyThread thread = new MyThread();
        thread.start();  // 直接start
    }
```

2.实现Runnable 

按参数传进 Thread

```Java
public class MyRunbale implements Runnable{
    @Override
    public void run() {
        for (int i = 0;i < 100 ;i++){
            System.out.println("我是runnable" +i);
        }
    }
}

  public void creatThreadRunable(){
        MyRunbale myRunbale = new MyRunbale();
        Thread thread = new Thread(myRunbale);
        thread.start();
    }
```



3.实现 Callable<T>

有返回值的

```Java
public class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        for (int i = 0; i < 100; i++) {
            System.out.println(" 线程 = " + Thread.currentThread().getName() + i);
        }
        return "callAble调用完成";
    }
}

 public void creatThreadCallable() throws ExecutionException, InterruptedException {
        // 线程开启之后需要执行里面的call方法
        MyCallable myCallable = new MyCallable();
        //可以获取线程执行完毕之后的结果.也可以作为参数传递给Thread对象
        FutureTask<String> stringFutureTask = new FutureTask<>(myCallable);

        Thread thread = new Thread(stringFutureTask);

        // 开启线程
        thread.start();
        String s = stringFutureTask.get(); // 阻塞主线程 一直等待
        System.out.println(s);

    }
```



### 线程Thread的常见方法

```Java
void  setName(String name)  将此线程的名称更改为等于参数name
String  getName() 返回此线程的名称
Thread  currentThread() 返回对当前正在执行的线程对象的引用    
 static void sleep(long millis)  + | 使当前正在执行的线程停留（暂停执行）指定的毫秒数 |    
    
 // 优先级
    //优先级: 1 - 10 默认值:5
     *   final int getPriority() 返回此线程的优先级
     *   final void setPriority(int newPriority)   更改此线程的优先级线程默认优先级是5；线程优先级的范围是：1-10(低 -- 高)
     *
    
    void setDaemon(boolean on)  将此线程标记为守护线程，当运行的线程都是守护线程时，Java虚拟机将退出
    当普通线程执行完之后,那么守护线程也没有继续运行下去的必要了.（eg: 微信聊天）
```



### 线程安全同步

场景：

有三个买票窗口，一共卖100张票

```java
public class SellTicket implements Runnable {
    private static int tickets = 100;

    @Override
    public void run() {
        while (true){
           if (tickets <= 0){
                        break;
                    }else {
        
                        tickets--;
                        System.out.println(Thread.currentThread().getName() + " tickets = " + tickets); // 这个方法打印有点问题
                    
                }
            }
        
        
        
        public static void main(String[] args) {
        SellTicket sellTicket = new SellTicket();

        Thread thread1 = new Thread(sellTicket, "窗口1");
        Thread thread2 = new Thread(sellTicket, "窗口2");
        Thread thread3 = new Thread(sellTicket, "窗口3");

        thread1.start();
        thread2.start();
        thread3.start();
    }
```

现象： 相同的票出现了多次  出现了负数的票

原因 ： 线程执行的随机性导致的,可能在卖票过程中丢失cpu的执行权,导致出现问题

出现条件：  是多线程环境   有共享数据

解决方案：

加锁 ： 把多条语句操作共享数据的代码给锁起来，让任意时刻只能有一个线程执行即可

1.synchronized同步代码块

```Java
synchronized(任意对象) {
*  多条语句操作共享数据的代码
* }

     synchronized (SellTicket.class){

                    if (tickets <= 0){
                        break;
                    }else {
                        // 休眠0.1s
                        tickets--;
                        System.out.println(Thread.currentThread().getName() + " tickets = " + tickets); // 这个方法打印有点问题
                    }

                }
```

2.synchronized同步方法

```java
    /**
     * 同步方法的锁对象是什么呢?
     *
     * this 类对象
     * this 锁是基于当前实例对象的锁。它用于同步当前实例对象的方法或代码块。当一个线程进入 synchronized (this) 块时，它会获取当前实例对象的锁。
     * 作用范围：仅限于当前实例对象
     *
     * @return
     */
  
  private synchronized boolean sellTicket() {
        if (tickets == 0){
            return true;
        }else {
            tickets--;
            System.out.println(Thread.currentThread().getName() +java " tickets = " + tickets); // 这个方法打印有点问题
            return false;
        }

    }
```

3.synchronized同步静态方法

```java
// 静态同步方法
    /**
     * 修饰符 static synchronized 返回值类型 方法名(方法参数) {
     *
     * 同步静态方法的锁对象是什么呢?
     *
     * ​	类名.class
     * 类名.class 锁是基于类的 Class 对象的锁。它用于同步整个类的所有实例对象。当一个线程进入 synchronized (类名.class) 块时，它会获取该类的 Class 对象的锁。
     *作用范围：整个类的所有实例对象。
     * 用途：用于同步整个类的多个线程，确保同一时间只有一个线程可以访问被同步的代码块。常用于静态方法的同步
     */

    private static synchronized boolean sellTicketStatic() {
        if (tickets == 0){
            return true;
        }else {
            tickets--;
            System.out.println(Thread.currentThread().getName() + " tickets = " + tickets); // 这个方法打印有点问题
            return false;
        }

    }
```



4.重入锁ReentrantLock

```Java
  /**
     * 新的锁对象 lock
     * - 加锁解锁方法
     * Lock是接口不能直接实例化，这里采用它的实现类ReentrantLock来实例化
     *
     *   | 方法名           | 说明   |
     *   | ------------- | ---- |
     *   | void lock()   | 获得锁  |
     *   | void unlock() | 释放锁  |
     */

    private final static ReentrantLock lock = new ReentrantLock();
    private static boolean sellTicket3() {
        lock.lock();
        try {
            if (tickets == 0){
                return true;
            }else {
                tickets--;
                System.out.println(Thread.currentThread().getName() + " tickets = " + tickets); // 这个方法打印是不安全的
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            lock.unlock(); // 最终都会释放锁
        }


    }
```

### 线程的协作

生产者消费者模式是一个十分经典的多线程协作的模式，弄懂生产者消费者问题能够让我们对多线程编程的理解更加深刻。

所谓生产者消费者问题，实际上主要是包含了两类线程：

​	一类是生产者线程用于生产数据

​	一类是消费者线程用于消费数据

为了解耦生产者和消费者的关系，通常会采用共享的数据区域，就像是一个仓库

生产者生产数据之后直接放置在共享数据区中，并不需要关心消费者的行为

消费者只需要从共享数据区中去获取数据，并不需要关心生产者的行为



Object类的等待和唤醒方法

void wait()    导致当前线程等待，直到另一个线程调用该对象的 notify()方法或 notifyAll()方法

void notify()   唤醒正在等待对象监视器的单个线程

void notifyAll()   唤醒正在等待对象监视器的所有线程



案例需求

+ 桌子类(Desk)：定义表示包子数量的变量,定义锁对象变量,定义标记桌子上有无包子的变量

+ 生产者类(Cooker)：实现Runnable接口，重写run()方法，设置线程任务

  1.判断是否有包子,决定当前线程是否执行

  2.如果有包子,就进入等待状态,如果没有包子,继续执行,生产包子

  3.生产包子之后,更新桌子上包子状态,唤醒消费者消费包子

+ 消费者类(Foodie)：实现Runnable接口，重写run()方法，设置线程任务

  1.判断是否有包子,决定当前线程是否执行

  2.如果没有包子,就进入等待状态,如果有包子,就消费包子

  3.消费包子后,更新桌子上包子状态,唤醒生产者生产包子

+ 测试类(Demo)：里面有main方法，main方法中的代码步骤如下

  创建生产者线程和消费者线程对象

  分别开启两个线程

方式1：

Desk.java

```java
public class Desk {

    //定义一个标记
    //true 就表示桌子上有汉堡包的,此时允许吃货执行
    //false 就表示桌子上没有汉堡包的,此时允许厨师执行
    public static boolean flag = false;   // 共享数据

    //汉堡包的总数量
    public static int count = 10; // 共享数据

    // 锁对象
    public static final Object lock = new Object();

}
```

Cooker.java

```java
public class Cooker extends Thread{
    //    生产者步骤：
//            1，判断桌子上是否有汉堡包
//    如果有就等待，如果没有才生产。
//            2，把汉堡包放在桌子上。
//            3，叫醒等待的消费者开吃。

    @Override
    public void run() {
     while (true){
            synchronized (Desk.lock){
                if (Desk.count == 0){
                    break;
                }else {
                    if (!Desk.flag){
                        // 生产
                        System.out.println("厨师正在生产汉堡包");
                        Desk.flag = true;
                        Desk.lock.notifyAll();
                    }else {
                        try {
                            Desk.lock.wait(); 
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }

```

Foodie.java

```java
public class Foodie extends Thread{
    @Override
    public void run() {
        //        1，判断桌子上是否有汉堡包。
//        2，如果没有就等待。
//        3，如果有就开吃
//        4，吃完之后，桌子上的汉堡包就没有了
//                叫醒等待的生产者继续生产
//        汉堡包的总数量减一

    while (true){
            synchronized (Desk.lock){
                if (Desk.count == 0){
                    break;
                }else {
                    if (Desk.flag){
                        // 有食物
                        System.out.println("吃面包");
                        Desk.count--;
                        Desk.flag = false;
                        Desk.lock.notifyAll();
                    }else {
                        // 没有就等待
                        try {
                            Desk.lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }


            }

        }

```

测试类

```Java
public static void main(String[] args) {
        ArrayBlockingQueue<String> bd = new ArrayBlockingQueue<>(1);

        Foodie f = new Foodie(bd);
        Cooker c = new Cooker(bd);

        f.start();
        c.start();
    }
```

方式2：阻塞队列的方式

等待唤醒机制

```
ArrayBlockingQueue
```

阻塞队列继承结构

![image-20250617093441810](C:\Users\lzy\AppData\Roaming\Typora\typora-user-images\image-20250617093441810.png)

+ 常见BlockingQueue:

  ArrayBlockingQueue: 底层是数组,有界

  LinkedBlockingQueue: 底层是链表,无界.但不是真正的无界,最大为int的最大值

+ BlockingQueue的核心方法:（具体的实现也是加锁的方式）

  put(anObject): 将参数放入队列,如果放不进去会阻塞

  take(): 取出第一个数据,取不到会阻塞

  ```java
  public class Demo02 {
      public static void main(String[] args) throws Exception {
          // 创建阻塞队列的对象,容量为 1
          ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(1);
  
          // 存储元素
          arrayBlockingQueue.put("汉堡包");
  
          // 取元素
          System.out.println(arrayBlockingQueue.take());
          System.out.println(arrayBlockingQuejavaue.take()); // 取不到会阻塞
  
          System.out.println("程序结束了");
      }
  }
  ```

  

```Java
public class Cooker extends Thread {

    private ArrayBlockingQueue<String> bd;

    public Cooker(ArrayBlockingQueue<String> bd) {
        this.bd = bd;
    }
//    生产者步骤：
//            1，判断桌子上是否有汉堡包
//    如果有就等待，如果没有才生产。
//            2，把汉堡包放在桌子上。
//            3，叫醒等待的消费者开吃。

    @Override
    public void run() {
        while (true) {
            try {
                bd.put("汉堡包");
                System.out.println("厨师放入一个汉堡包");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Foodie extends Thread {
    private ArrayBlockingQueue<String> bd;

    public Foodie(ArrayBlockingQueue<String> bd) {
        this.bd = bd;
    }

    @Override
    public void run() {
//        1，判断桌子上是否有汉堡包。
//        2，如果没有就等待。
//        3，如果有就开吃
//        4，吃完之后，桌子上的汉堡包就没有了
//                叫醒等待的生产者继续生产
//        汉堡包的总数量减一

        //套路:
        //1. while(true)死循环
        //2. synchronized 锁,锁对象要唯一
        //3. 判断,共享数据是否结束. 结束
        //4. 判断,共享数据是否结束. 没有结束
        while (true) {
            try {
                String take = bd.take();
                System.out.println("吃货将" + take + "拿出来吃了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

public class Demo {
    public static void main(String[] args) {
        ArrayBlockingQueue<String> bd = new ArrayBlockingQueue<>(1);

        Foodie f = new Foodie(bd);
        Cooker c = new Cooker(bd);

        f.start();
        c.start();
    }
}
```

### 线程池

背景：

​	系统创建一个线程的成本是比较高的，因为它涉及到与操作系统交互，当程序中需要创建大量生存期很短暂的线程时，频繁的创建和销毁线程对系统的资源消耗有可能大于业务处理是对系，为了提高性能，我们就可以采用线程池。线程池在启动的时，会创建大量空闲线程，当我们向线程池提交任务的时，线程池就，会启动一个线程来执行该任务。等待任务执行完毕以后，线程并不会死亡，而是再次返回到线程池中称为空闲状态。等待下一次任务的执行。

线程池设计思路

1. 准备一个任务容器
2. 一次性启动多个(2个)消费者线程
3. 刚开始任务容器是空的，所以线程都在wait
4. 直到一个外部线程向这个任务容器中扔了一个"任务"，就会有一个消费者线程被唤醒
5. 这个消费者线程取出"任务"，并且执行这个任务，执行完毕后，继续等待下一次任务的到来

线程池执行流程

![image-20250617155730045](C:\Users\lzy\AppData\Roaming\Typora\typora-user-images\image-20250617155730045.png)



使用：

#### Executors线程池的创建

Executors中所提供的**静态**方法来创建线程池

```Java
static ExecutorService newCachedThreadPool()   
static newFixedThreadPool(int nThreads)    
ExecutorService executorService2 = Executors.newSingleThreadExecutor();
ScheduledExecutorService executorService3 = Executors.newScheduledThreadPool(5);

```

缓存线程池 newCachedThreadPool（）

创建一个默认的线程池对象.池子中默认是空的.默认最多可以容纳int类型的最大值.

```Java
 		// 缓存线程池
        // 特点 ： 核心线程数为 0，最大线程数为 Integer.MAX_VALUE，线程空闲 60 秒后回收，使用 			SynchronousQueue
        // 适用场景：适合执行大量短生命周期的异步任务
        // 潜在风险：线程数上限接近无限，在任务量突增时可能创建大量线程，压榨系统资源
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            final int taskId = i;
            executorService.execute(()->{
                System.out.println("缓存线程" + Thread.currentThread().getName()
                        + "开始执行任务" + taskId);
            });
        }
```

固定线程池：static ExecutorService newFixedThreadPool(int nThreads) : 创建一个指定最多线程数量的线程池

```Java
 		// 固定线程数的线程池
        // 特点 ： 核心线程数等于最大线程数，不会回收线程，使用无界队列 LinkedBlockingQueue
        // 适用场景 ： 适合处理固定数量的长期任务，保持稳定的并发度
        // 潜在风险：使用无界队列，当任务持续快速提交而处理速度较慢时，可能导致队列过大，引发内存溢出(OOM)
        ExecutorService executorService1 = Executors.newFixedThreadPool(10);
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            final int taskId = i;
            executorService1.execute(()->{
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("固定线程" + Thread.currentThread().getName()
                        + "开始执行任务" + taskId);
            });
        }
```



单线程池： newSingleThreadExecutor

```Java
  // 单线程池
        // 特点：核心线程数和最大线程数都为 1，使用无界队列 LinkedBlockingQueue
        // 适用场景：适合需要保证任务顺序执行的场景，如日志记录系统
       // 潜在风险：使用无界队列，任务堆积可能导致 OOM；单线程执行效率有限
        ExecutorService executorService2 = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 10; i++) {
            final int taskId = i;
            executorService2.execute(()->{
                System.out.println("单线程" + Thread.currentThread().getName()
                        + "开始执行任务" + taskId);
            });
        }

```



定时线程 ：newScheduledThreadPool

```
		 // 定时线程
        // 特点：支持定时及周期性任务执行，核心线程数固定，最大线程数为 Integer.MAX_VALUE
        // 适用场景：需要执行定时任务或周期性任务的场景
        // 潜在风险：使用 DelayedWorkQueue 可能堆积大量待执行任务，导致内存压力
        ScheduledExecutorService executorService3 = Executors.newScheduledThreadPool(5);
        for (int i = 0; i < 10; i++) {
            final int taskId = i;
            executorService3.schedule(()->{
                System.out.println("定时线程" + Thread.currentThread().getName()
                        + "开始执行任务" + taskId);
            },3,TimeUnit.SECONDS); // 三秒后执行
        }
```

#### 工作队列

​	

```Java
      // ArrayBlockingQueue ： 基于数组的有界阻塞队列，必须指定队列大小  适用场景：明确知道任务量的有界场景，可以防止资源耗尽

        // SynchronousQueue 队列 ： take 和 put都阻塞，无容量，直接交付任务  适用场景： 需快速响应的场景 一个不存储元素的阻塞队列，消费者线程调用 take() 方法的时候就会发生阻塞，直到有一个生产者线程生产了一个元素，消费者线程就可以拿到这个元素并返回；生产者线程调用 put() 方法的时候也会发生阻塞，直到有一个消费者线程消费了一个元素，生产者才会返回

        // LinkedBlockingQueue ：可指定容量的链表队列   需设置容量避免 OOM
去
        // DelayedWorkQueue ： 延迟获取元素的无界队列  使用场景 ： 延迟任务执行场景
```

线程大小计算示例

```Java
// 队列大小计算示例：
// 假设峰值QPS=1000，任务处理平均耗时=200ms
// 冗余系数2.0用于应对流量突发和任务处理时间波动，确保系统稳定性
int queueSize = (int)(1000 * (200 / 1000.0) * 2.0); // 峰值QPS × 平均处理耗时 × 冗余系数
BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(queueSize);
```

#### 线程池对象 

上面两个方式内部实现都是创建了线程池对象，最好自己创建线程池对象（阿里规范）

![image-20250617101207325](C:\Users\lzy\AppData\Roaming\Typora\typora-user-images\image-20250617101207325.png)

```Java
* 创建线程池对象 :**
*
* ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(核心线程数量,最大线程数量,空闲线程最大存活时间,任务队列,创建线程工厂,任务的拒绝策略);
*
* corePoolSize：   核心线程的最大值，不能小于0
* maximumPoolSize：最大线程数，不能小于等于0，maximumPoolSize >= corePoolSize
* keepAliveTime：  空闲线程最大存活时间,不能小于0
* unit：           时间单位
* workQueue：      任务队列，不能为null
* threadFactory：  创建线程工厂,不能为null
* handler：        任务的拒绝策略,不能为null
*
*明确线程池对多可执行的任务数 = 队列容量 + 最大线程数
*
* RejectedExecutionHandler是jdk提供的一个任务拒绝策略接口，它下面存在4个子类。
*
* ```java
* ThreadPoolExecutor.AbortPolicy:         丢弃任务并抛出RejectedExecutionException异常。是默认的策略。
* ThreadPoolExecutor.DiscardPolicy：          丢弃任务，但是不抛出异常 这是不推荐的做法。
* ThreadPoolExecutor.DiscardOldestPolicy：    抛弃队列中等待最久的任务 然后把当前任务加入队列中。
* ThreadPoolExecutor.CallerRunsPolicy:        调用任务的run()方法绕过线程池直接执行。
```



```Java
 ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1,3,2, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1),
                Executors.defaultThreadFactory(),new ThreadPoolExecutor.CallerRunsPolicy());

        //  提交5个任务，而该线程池最多可以处理4个任务，当我们使用AbortPolicy这个任务处理策略的时候，就会抛出异常， 控制台报错，仅仅执行了4个任务，有一个任务被丢弃了 -- AbortPolicy()

        // 当我们使用DiscardPolicy这个任务处理策略的时候，控制台不会报错，仅仅执行了4个任务，有一个任务被丢弃了
         
        // DiscardOldestPolicy 线程池中等待时间最长，丢弃 执行了4个任务

        // CallerRunsPolicy 我们可以看到次策略没有通过线程池中的线程执行任务，而是直接调用任务的run()方法绕过线程池直接执行
        for (int i = 0;i < 5;i++ ){
            // 定义一个变量，来指定指定当前执行的任务;这个变量需要被final修饰
            final int y = i ;
            poolExecutor.submit(() ->{
                        System.out.println(Thread.currentThread().getName() + "---->> 执行了任务" + y);
                    });
        }
```

