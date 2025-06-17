package producter;

import java.util.concurrent.ArrayBlockingQueue;

public class Cooker extends Thread{
    //    生产者步骤：
//            1，判断桌子上是否有汉堡包
//    如果有就等待，如果没有才生产。
//            2，把汉堡包放在桌子上。
//            3，叫醒等待的消费者开吃。
    private ArrayBlockingQueue<String> arrayBlockingQueue;
    public Cooker(ArrayBlockingQueue arrayBlockingQueue){
        this.arrayBlockingQueue = arrayBlockingQueue;
    }

    @Override
    public void run() {
     /*   while (true){
            synchronized (Desk.lock){
                if (Desk.count == 0){
                    break;
                }else {
                    if (!Desk.flag){
                        // 生产
                        System.out.println("厨师正在生产汉堡包");
                        Desk.flag = true;
                        Desk.lock.notifyAll();
                    }else {
                        try {
                            Desk.lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }*/

        while (true){
            try {
                arrayBlockingQueue.put("汉堡包");
                System.out.println("厨师放入一个汉堡");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


}
