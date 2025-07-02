package InnerClass.nomalcalss.notnameinnerclass;

public class Test {
    /**
     *
     * 如果我们希望定义一个只要使用一次的类，就可考虑使用匿名内部类。匿名内部类的本质作用  简化代码
     *
     * 格式：
     * new 类名或者接口名() {
     *      重写方法;
     * };
     * @param args
     */
    public static void main(String[] args) {
        // 要调用学生对象要调用 go 方法
        // 普通方式

        // 创建实现类对象
        Swim student = new Student();
        goSwimming(student);

        // 匿名内部类必须**继承一个父类**或者**实现一个父接口**
        // 传入匿名内部类
        // 结构  new  表示创建对象    Swim/父类  ： 表示这个匿名类要实现的接口  {  } ： 表示这个匿名类的类体
        Swim s3 = new Swim() {
            @Override
            public void swimming() {
                System.out.println("我是学生 我游泳");
            }
        };

        goSwimming(s3);

        Swim sunYang = new SunYang(){  // 这个匿名内部类的父类是 孙杨
            @Override
            public void swimming() {
                super.swimming();
            }
        };

        goSwimming(sunYang);

        // 完美方案 ： 一步到位
        goSwimming(new Swim() {
            @Override
            public void swimming() {
                System.out.println("我是学生1 我游泳");
            }
        });

    }








    // 定义一个方法,模拟请一些人去游泳
    public static void goSwimming(Swim s) {
        s.swimming();
    }
}
