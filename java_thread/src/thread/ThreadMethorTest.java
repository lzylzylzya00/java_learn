package thread;

import java.util.concurrent.FutureTask;

public class ThreadMethorTest {
    /**
     * void  setName(String name)  将此线程的名称更改为等于参数name
     * String  getName() 返回此线程的名称
     *
     * Thread  currentThread() 返回对当前正在执行的线程对象的引用
     *
     * static void sleep(long millis)  + | 使当前正在执行的线程停留（暂停执行）指定的毫秒数 |
     *
     *   //优先级: 1 - 10 默认值:5
     *   final int getPriority() 返回此线程的优先级
     *   final void setPriority(int newPriority)   更改此线程的优先级线程默认优先级是5；线程优先级的范围是：1-10(低 -- 高)
     *
     *   void setDaemon(boolean on)  将此线程标记为守护线程，当运行的线程都是守护线程时，Java虚拟机将退出
     *
     *         //当普通线程执行完之后,那么守护线程也没有继续运行下去的必要了.（eg: 微信聊天）
     * @param args
     */
    public static void main(String[] args){
      /*  MyThread myThread1 = new MyThread();
        MyThread myThread2 = new MyThread();

        myThread1.setName("线程1");
        myThread2.setName("线程2");

        myThread1.start();
        myThread2.start();*/

       /* MyCallable myCallable = new MyCallable();

        FutureTask<String> futureTask = new FutureTask<String>(myCallable);

        Thread thread1 = new Thread(futureTask);
        thread1.setName("飞机");
        thread1.setPriority(10);
        thread1.start();


        MyCallable myCallable2 = new MyCallable();
        FutureTask<String> futureTask1 = new FutureTask<>(myCallable2);
        Thread thread2 = new Thread(futureTask1);
        thread2.setName("坦克");
        thread2.setPriority(1);
        thread2.start();*/

        MyThread1 myThread = new MyThread1();
        MyThread2 myThread1 = new MyThread2();

        myThread.setName("女神"); // 执行很快 执行10次
        myThread1.setName("备胎");  // 也会退出执行

        myThread1.setDaemon(true);

        myThread.start();
        myThread1.start();



    }
}


class MyThread1 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(getName() + "---" + i);
        }
    }
}
class MyThread2 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(getName() + "---" + i);
        }
    }
}