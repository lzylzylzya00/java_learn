import thread.CreatThread;

import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args){
        CreatThread creatThread = new CreatThread();
      //  creatThread.creatThread();

     //   creatThread.creatThreadRunable();

        try {
            creatThread.creatThreadCallable();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
