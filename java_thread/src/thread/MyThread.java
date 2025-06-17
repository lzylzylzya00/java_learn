package thread;

public class MyThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {

            //  Thread.sleep(1000);

            System.out.println("线程名字 = " + Thread.currentThread().getName() + i);

        }
    }
}
