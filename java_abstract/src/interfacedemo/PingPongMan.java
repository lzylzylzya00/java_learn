package interfacedemo;

public class PingPongMan implements SportMan{
    @Override
    public void run() {
        System.out.println("乒乓球运动员稍微跑一下！！");

    }

    @Override
    public void law() {
        System.out.println("乒乓球运动员稍微跑一下！！");

    }

    @Override
    public String compittion(String project) {
        System.out.println("参加"+project+"得金牌！");
        return "参加"+project+"得金牌！";
    }
}
