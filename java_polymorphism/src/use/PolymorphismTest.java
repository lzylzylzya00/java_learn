package use;

public class PolymorphismTest {
    public static void main(String[] args) {
        Student s = new Student();
        s.setName("张三");
        s.setAge(18);
        s.show1();


        Teacher t = new Teacher();
        t.setName("王建国");
        t.setAge(30);

        Administractor admin = new Administractor();
        admin.setName("管理员");
        admin.setAge(35);



        // 调用了 不同的方法
        register(s);
        register(t);
        register(admin);

        /**
         * **多态**： 是指同一行为，具有多个不同表现形式
         * **多态体现的格式**：
         * 父类类型 变量名 = new 子类/实现类构造器;
         * 变量名.方法名();
         *
         *  **前提【重点】**
         *
         * 1. 有继承或者实现关系
         *
         * 2. 方法的重写【意义体现：不重写，无意义】
         *
         * 3. 父类引用指向子类对象【格式体现】
         *
         *  运行特点：
         * 调用成员变量时：编译看左边，运行看左边
         *
         * 调用成员方法时：编译看左边，运行看右边（必须是重写的方法）
         */

        Person person = new Student();

        //编译看左边的父类中有没有name这个属性，没有就报错
        //在实际运行的时候，把父类name属性的值打印出来
        System.out.println(person.name);

        //编译看左边的父类中有没有show这个方法，没有就报错
        //在实际运行的时候，运行的是子类中的show方法
        person.show();

        /**
         * 当使用多态方式调用方法时，首先检查父类中是否有该方法，如果没有，则编译错误。
         * 也就是说，**不能调用**子类拥有，而父类没有的方法。编译都错误
         */
        // 调用不了子类特有的方法
      //  student.show1();


        /**
         * 转型
         * **向上转型**：多态本身是子类类型向父类类型向上转换（自动转换）的过程，这个过程是默认的。
         * 当父类引用指向一个子类对象时，便是向上转型。
         * 使用格式
         *
         * 父类类型  变量名 = new 子类类型();
         * 如：Animal a = new Cat();
         *
         * **向下转型**：父类类型向子类类型向下转换的过程，这个过程是强制的。
         * 一个已经向上转型的子类对象，将父类引用转为子类引用，可以使用强制类型转换的格式，便是向下转型。
         *
         * 子类类型 变量名 = (子类类型) 父类变量名;
         * 如:Aniaml a = new Cat();
         *    Cat c =(Cat) a;
         */

        // 使用 student向下转型
        Student student = (Student) person;
        student.show1();

        // 转型异常  person本来persopn是student类型  现在强转为Teacher类型
        /**
         * 为了避免ClassCastException的发生，Java提供了 `instanceof` 关键字，给引用变量做类型的校验，格式如下：
         * 变量名 instanceof 数据类型
         * 如果变量属于该数据类型或者其子类类型，返回true。
         * 如果变量不属于该数据类型或者其子类类型，返回false。
         */
        System.out.println(person instanceof Teacher);
        if (person instanceof Teacher){
            Teacher teacher = (Teacher) person;
        }



    }

    /**多态使用场景
     *
     *
     * * 当一个方法的形参是一个类，我们可以传递这个类所有的子类对象。
     * * 当一个方法的形参是一个接口，我们可以传递这个接口所有的实现类对象。
     * * 而且多态还可以根据传递的不同对象来调用不同类中的方法。
     * @param p
     */
   // 如果没有多态，在下图中register方法只能传递学生对象，其他的Teacher和administrator对象是无法传递给register方法方法的，在这种情况下，只能定义三个不同的register方法分别接收学生，老师和管理员。
    //这个方法既能接收老师，又能接收学生，还能接收管理员
    //只能把参数写成这三个类型的父类
    public static void register(Person p){
        p.show();
    }

}
