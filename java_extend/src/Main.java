import extend.Student;
import extend.Teacher;

public class Main {
    public static void main(String[] args) {

        /**
         * 1.继承实际上是子类相同的属性和行为可以定义在父类中，子类特有的属性和行为由自己定义，这样就实现了相同属性和行为的重复利用，从而提高了代码复用。
         *
         * 2.子类继承父类，就可以直接得到父类的成员变量和方法
         */
        Teacher teacher = new Teacher();
        teacher.setAge(30); // 父类方法
        teacher.setName("张三"); // 父类方法

        teacher.setSalary(5000); // 自己的方法
        teacher.teach();


        Student student = new Student();
        student.study();
        student.setAge(15); // 父类方法
        student.setName("学生"); // 父类方法
    }
}
