package thread;

public class MyRunbale implements Runnable{
    @Override
    public void run() {
        for (int i = 0;i < 100 ;i++){
            System.out.println("我是runnable" +i);
        }
    }
}
