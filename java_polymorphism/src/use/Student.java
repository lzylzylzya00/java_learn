package use;

public class Student extends Person{
    @Override
    public void show() {
        System.out.println("学生的信息为：" + getName() + ", " + getAge());

    }

    public void show1(){
        System.out.println("我是 Student 的特有方法");
    }
}
