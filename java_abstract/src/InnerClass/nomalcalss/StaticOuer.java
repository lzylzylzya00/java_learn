package InnerClass.nomalcalss;

public class StaticOuer {   // 内部类: StaticOuer
    private static  String sc_name = "张三";
    private String so_name = "李四";


    // 静态内部类: Inner
    public static class Inner{
        private String name;
        public Inner(String name) {
            this.name = name;
        }

        public void showName(){
            System.out.println(this.name);
            // 拓展:静态内部类可以直接访问外部类的静态成员。
            System.out.println(sc_name);
            // System.out.println(so_name); // 静态内部类不可以直接访问外部类的非静态成员，如果要访问需要创建外部类的对象

            // 静态内部类中没有的Outer.this  静态内部类 不持有外部类引用
        }

    }
}
