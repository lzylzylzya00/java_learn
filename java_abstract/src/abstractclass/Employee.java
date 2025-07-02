package abstractclass;

/**
 * 引入：
 *父类可能知道子类应该有哪个功能，但是功能具体怎么实现父类是不清楚的（由子类自己决定），父类只需要提供一个没有方法体的定义即可，具体实现交给子类自己去实现
 * 定义：
 * *我们把没有方法体的方法称为抽象方法。Java语法规定，包含抽象方法的类就是抽象类*
 *
 * - **抽象方法** ： 没有方法体的方法。
 *定义格式：
 * 修饰符 abstract 返回值类型 方法名 (参数列表)；
 * public abstract void run()；
 *
 * - **抽象类**：包含抽象方法的类。 如果一个类包含抽象方法，那么该类必须是抽象类。**注意：抽象类不一定有抽象方法，但是有抽象方法的类必须定义成抽象类
 *
 * 定义格式：
 *abstract class 类名字 {
 *
 * }
 *
 * 抽象类的使用
 *
 * **要求**：继承抽象类的子类**必须重写父类所有的抽象方法**。否则，该子类也必须声明为抽象类
 *
 * 意义 ： 抽象类存在的意义是为了被子类继承，否则抽象类将毫无意义。抽象类可以强制让子类，一定要按照规定的格式进行重写
 */
public abstract class Employee {
    private String id;
    private String name;
    private double salary;

    public Employee(){}

    public Employee(String id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    // 抽象方法
    // 抽象方法必须要放在抽象类中
    public abstract void work();

}
