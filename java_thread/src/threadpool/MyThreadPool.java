package threadpool;

import java.util.concurrent.*;

public class MyThreadPool {

    //Executors --- 可以帮助我们创建线程池对象
    //ExecutorService --- 可以帮助我们控制线程池
    public static void main(String[] args) {
        // 缓存线程池
        // 特点 ： 核心线程数为 0，最大线程数为 Integer.MAX_VALUE，线程空闲 60 秒后回收，使用 SynchronousQueue
        // 适用场景：适合执行大量短生命周期的异步任务
        // 潜在风险：线程数上限接近无限，在任务量突增时可能创建大量线程，压榨系统资源
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            final int taskId = i;
            executorService.execute(()->{
                System.out.println("缓存线程" + Thread.currentThread().getName()
                        + "开始执行任务" + taskId);
            });
        }

        // 固定线程数的线程池
        // 特点 ： 核心线程数等于最大线程数，不会回收线程，使用无界队列 LinkedBlockingQueue
        // 适用场景 ： 适合处理固定数量的长期任务，保持稳定的并发度
        // 潜在风险：使用无界队列，当任务持续快速提交而处理速度较慢时，可能导致队列过大，引发内存溢出(OOM)
        ExecutorService executorService1 = Executors.newFixedThreadPool(10);
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            final int taskId = i;
            executorService1.execute(()->{
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("固定线程" + Thread.currentThread().getName()
                        + "开始执行任务" + taskId);
            });
        }

        // 单线程池
        // 特点：核心线程数和最大线程数都为 1，使用无界队列 LinkedBlockingQueue
        // 适用场景：适合需要保证任务顺序执行的场景，如日志记录系统
       // 潜在风险：使用无界队列，任务堆积可能导致 OOM；单线程执行效率有限
        ExecutorService executorService2 = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 10; i++) {
            final int taskId = i;
            executorService2.execute(()->{
                System.out.println("单线程" + Thread.currentThread().getName()
                        + "开始执行任务" + taskId);
            });
        }


        // 定时线程
        // 特点：支持定时及周期性任务执行，核心线程数固定，最大线程数为 Integer.MAX_VALUE
        // 适用场景：需要执行定时任务或周期性任务的场景
        // 潜在风险：使用 DelayedWorkQueue 可能堆积大量待执行任务，导致内存压力
        ScheduledExecutorService executorService3 = Executors.newScheduledThreadPool(5);
        for (int i = 0; i < 10; i++) {
            final int taskId = i;
            executorService3.schedule(()->{
                System.out.println("定时线程" + Thread.currentThread().getName()
                        + "开始执行任务" + taskId);
            },3,TimeUnit.SECONDS); // 三秒后执行
        }

        // ArrayBlockingQueue ： 基于数组的有界阻塞队列，必须指定队列大小  适用场景：明确知道任务量的有界场景，可以防止资源耗尽
        // SynchronousQueue 队列 ： take 和 put都阻塞，无容量，直接交付任务  适用场景： 需快速响应的场景
        // LinkedBlockingQueue ：可指定容量的链表队列   需设置容量避免 OOM
        // DelayedWorkQueue ： 延迟获取元素的无界队列  使用场景 ： 延迟任务执行场景

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
