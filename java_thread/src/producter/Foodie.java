package producter;

import java.util.concurrent.ArrayBlockingQueue;

public class Foodie extends Thread{
    private ArrayBlockingQueue<String> arrayBlockingQueue;
    public Foodie(ArrayBlockingQueue arrayBlockingQueue){
        this.arrayBlockingQueue = arrayBlockingQueue;
    }
    @Override
    public void run() {
        //        1，判断桌子上是否有汉堡包。
//        2，如果没有就等待。
//        3，如果有就开吃
//        4，吃完之后，桌子上的汉堡包就没有了
//                叫醒等待的生产者继续生产
//        汉堡包的总数量减一

     /*   while (true){
            synchronized (Desk.lock){
                if (Desk.count == 0){
                    break;
                }else {
                    if (Desk.flag){
                        // 有食物
                        System.out.println("吃面包");
                        Desk.count--;
                        Desk.flag = false;
                        Desk.lock.notifyAll();
                    }else {
                        // 没有就等待
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
                String take = arrayBlockingQueue.take();
                System.out.println("吃货将" + take + "拿出来吃了");
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
