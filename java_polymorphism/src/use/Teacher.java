package use;

public class Teacher extends Person{
    @Override
    public void show() {
        System.out.println("老师的信息为：" + getName() + ", " + getAge());

    }

    public void show2(){
        System.out.println("我是老师特有的方法");

    }
}
