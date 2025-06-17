package threadsync;

public class SellTicktDemo {
    /**
     * 卖票出现了问题
     *相同的票出现了多次
     * 出现了负数的票
     * 问题产生原因
     *
     * 线程执行的随机性导致的,可能在卖票过程中丢失cpu的执行权,导致出现问题
     *
     *
     * 安全问题出现的条件
     * 是多线程环境
     * 有共享数据
     * 有多条语句操作共享数据
     *
     * 如何解决多线程安全问题呢?
     * 基本思想：让程序没有安全问题的环境
     * 把多条语句操作共享数据的代码给锁起来，让任意时刻只能有一个线程执行即可
     * Java提供了同步代码块的方式来解决
     *
     * synchronized(任意对象) {
     * 	多条语句操作共享数据的代码
     * }
     * @param args
     */
    public static void main(String[] args) {
        SellTicket sellTicket = new SellTicket();

        Thread thread1 = new Thread(sellTicket, "窗口1");
        Thread thread2 = new Thread(sellTicket, "窗口2");
        Thread thread3 = new Thread(sellTicket, "窗口3");

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
