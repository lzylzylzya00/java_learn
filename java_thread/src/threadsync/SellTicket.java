package threadsync;

import java.util.concurrent.locks.ReentrantLock;

public class SellTicket implements Runnable {
    private static int tickets = 100;
    private Object object = new Object();


    @Override
    public void run() {
        while (true){

            if ("窗口1".equals(Thread.currentThread().getName())){
                // 同步代码块格式
                synchronized (SellTicket.class){

                    if (tickets <= 0){
                        break;
                    }else {
                        // 休眠0.1s
                        tickets--;
                        System.out.println(Thread.currentThread().getName() + " tickets = " + tickets); // 这个方法打印有点问题
                    }

                }
            }

            if ("窗口2".equals(Thread.currentThread().getName())){
                boolean result = sellTicketStatic();
                if (result){
                    break;
                }
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }



    }


    /**
     * 同步方法的锁对象是什么呢?
     *
     * this 类对象
     * this 锁是基于当前实例对象的锁。它用于同步当前实例对象的方法或代码块。当一个线程进入 synchronized (this) 块时，它会获取当前实例对象的锁。
     * 作用范围：仅限于当前实例对象
     *
     * @return
     */
    //修饰符 synchronized 返回值类型 方法名(方法参数) {

    private synchronized boolean sellTicket() {
        if (tickets == 0){
            return true;
        }else {
            tickets--;
            System.out.println(Thread.currentThread().getName() + " tickets = " + tickets); // 这个方法打印有点问题
            return false;
        }

    }


    // 静态同步方法
    /**
     * 修饰符 static synchronized 返回值类型 方法名(方法参数) {
     *
     * 同步静态方法的锁对象是什么呢?
     *
     * ​	类名.class
     * 类名.class 锁是基于类的 Class 对象的锁。它用于同步整个类的所有实例对象。当一个线程进入 synchronized (类名.class) 块时，它会获取该类的 Class 对象的锁。
     *作用范围：整个类的所有实例对象。
     * 用途：用于同步整个类的多个线程，确保同一时间只有一个线程可以访问被同步的代码块。常用于静态方法的同步
     */

    private static synchronized boolean sellTicketStatic() {
        if (tickets == 0){
            return true;
        }else {
            tickets--;
            System.out.println(Thread.currentThread().getName() + " tickets = " + tickets); // 这个方法打印有点问题
            return false;
        }

    }


    /**
     * 新的锁对象 lock
     * - 加锁解锁方法
     * Lock是接口不能直接实例化，这里采用它的实现类ReentrantLock来实例化
     *
     *   | 方法名           | 说明   |
     *   | ------------- | ---- |
     *   | void lock()   | 获得锁  |
     *   | void unlock() | 释放锁  |
     */

    private final static ReentrantLock lock = new ReentrantLock();
    private static boolean sellTicket3() {
        lock.lock();
        try {
            if (tickets == 0){
                return true;
            }else {
                tickets--;
                System.out.println(Thread.currentThread().getName() + " tickets = " + tickets); // 这个方法打印有点问题
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            lock.unlock(); // 最终都会释放锁
        }


    }

}

