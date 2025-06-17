package thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class CreatThread {
    /**
     * 线程的创建
     * 1.Thread方式
     */
    public void creatThread(){
        MyThread thread = new MyThread();
        thread.start();
    }


    /**
     * 2.runable
     *
     */

    public void creatThreadRunable(){
        MyRunbale myRunbale = new MyRunbale();
        Thread thread = new Thread(myRunbale);
        thread.start();
    }

    /**
     * 3.callable创建
     */

    public void creatThreadCallable() throws ExecutionException, InterruptedException {
        // 线程开启之后需要执行里面的call方法
        MyCallable myCallable = new MyCallable();
        //可以获取线程执行完毕之后的结果.也可以作为参数传递给Thread对象
        FutureTask<String> stringFutureTask = new FutureTask<>(myCallable);

        Thread thread = new Thread(stringFutureTask);

        // 开启线程
        thread.start();
        String s = stringFutureTask.get(); // 阻塞主线程 一直等待
        System.out.println(s);

    }






}
