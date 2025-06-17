package threadpool;

import java.util.concurrent.*;

public class MyThreadPool {
    //static ExecutorService newCachedThreadPool()   创建一个默认的线程池
    //static newFixedThreadPool(int nThreads)	    创建一个指定最多线程数量的线程池
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

    //  ExecutorService executorService = Executors.newFixedThreadPool(10);

        ThreadPoolExecutor pool = (ThreadPoolExecutor) executorService;

        System.out.println(pool.getPoolSize()); // 0

        //Executors --- 可以帮助我们创建线程池对象
        //ExecutorService --- 可以帮助我们控制线程池
        executorService.submit(()->{
            System.out.println(Thread.currentThread().getName() + "在执行");
        });


        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        executorService.submit(()->{
            System.out.println(Thread.currentThread().getName() + "在执行");
        });
        System.out.println(pool.getPoolSize()); // 2
      //  executorService.shutdown();*/


        /**
         * 创建线程池对象 :**
         *
         * ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(核心线程数量,最大线程数量,空闲线程最大存活时间,任务队列,创建线程工厂,任务的拒绝策略);
         *
         * corePoolSize：   核心线程的最大值，不能小于0
         * maximumPoolSize：最大线程数，不能小于等于0，maximumPoolSize >= corePoolSize
         * keepAliveTime：  空闲线程最大存活时间,不能小于0
         * unit：           时间单位
         * workQueue：      任务队列，不能为null
         * threadFactory：  创建线程工厂,不能为null
         * handler：        任务的拒绝策略,不能为null
         *
         *明确线程池对多可执行的任务数 = 队列容量 + 最大线程数
         *
         * RejectedExecutionHandler是jdk提供的一个任务拒绝策略接口，它下面存在4个子类。
         *
         * ```java
         * ThreadPoolExecutor.AbortPolicy: 		    丢弃任务并抛出RejectedExecutionException异常。是默认的策略。
         * ThreadPoolExecutor.DiscardPolicy： 		   丢弃任务，但是不抛出异常 这是不推荐的做法。
         * ThreadPoolExecutor.DiscardOldestPolicy：    抛弃队列中等待最久的任务 然后把当前任务加入队列中。
         * ThreadPoolExecutor.CallerRunsPolicy:        调用任务的run()方法绕过线程池直接执行。
         * ```
         *
         */

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1,3,2, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1),
                Executors.defaultThreadFactory(),new ThreadPoolExecutor.CallerRunsPolicy());

        //  提交5个任务，而该线程池最多可以处理4个任务，当我们使用AbortPolicy这个任务处理策略的时候，就会抛出异常
       // 控制台报错，仅仅执行了4个任务，有一个任务被丢弃了 -- AbortPolicy()

        // 当我们使用DiscardPolicy这个任务处理策略的时候，控制台不会报错
        // 仅仅执行了4个任务，有一个任务被丢弃了

        // DiscardOldestPolicy 线程池中等待时间最长，丢弃 执行了4个任务

        // CallerRunsPolicy 我们可以看到次策略没有通过线程池中的线程执行任务，而是直接调用任务的run()方法绕过线程池直接执行
        for (int i = 0;i < 5;i++ ){
            // 定义一个变量，来指定指定当前执行的任务;这个变量需要被final修饰
            final int y = i ;
            poolExecutor.submit(() ->{
                        System.out.println(Thread.currentThread().getName() + "---->> 执行了任务" + y);
                    });
        }


    }
}
