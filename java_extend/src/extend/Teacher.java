package extend;

public class Teacher extends Human{
    // 工资
    private double salary;

    // 工资
    public void teach(){
        System.out.println("老师教学生");
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
