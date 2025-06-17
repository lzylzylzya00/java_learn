package thread;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        for (int i = 0; i < 100; i++) {
            System.out.println(" 线程 = " + Thread.currentThread().getName() + i);
        }
        return "callAble调用完成";
    }
}
