package thread;

import thread.FileThread;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {
    static int count = 0;
    static String target = ".txt";
    static List<FileThread> threadList = new ArrayList<>();
    //    static ExecutorService service = Executors.newFixedThreadPool(8);
    static ThreadPoolExecutor service = (ThreadPoolExecutor) Executors.newFixedThreadPool(6);

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        String path = "C:\\";
        File file = new File(path);
        FileThread fileThread = new FileThread(file.listFiles());
        service.submit(fileThread);


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        service.shutdownNow();
        while (!service.isTerminated()) {
            System.out.println(count);
        }

        System.out.println("Finished all threads");
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }
}
