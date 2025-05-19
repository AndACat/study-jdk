package life.unit.io;

import org.junit.Test;

import java.io.*;
import java.util.concurrent.CompletableFuture;

public class MyUnitTest_IO {
    @Test
    public void test1() throws IOException {
        // 测试文件输入流
        InputStream inputStream = new FileInputStream("Q:\\workspace_idea\\study-jdk\\src\\life\\unit\\io\\test.txt");
        int b = 0;
        while((b = inputStream.read()) != -1){
            System.out.print((char) b);
        }
    }

    @Test
    public void test2() throws IOException, InterruptedException {
        // 测试一个线程写入数据，另一个线程读取数据
        // 定义一个管道输出流
        PipedOutputStream pipedOutputStream = new PipedOutputStream();
        // 定义一个管道输入流，并和管道输出流进行连接
        PipedInputStream pipedInputStream = new PipedInputStream(pipedOutputStream);

        // 启动一个线程，向管道输出流写入数据
        CompletableFuture.runAsync(() -> {
            try {
                System.out.println(Thread.currentThread().getName() +
                        " ===> 开始写入数据" + System.currentTimeMillis());
                pipedOutputStream.write("hello world piped".getBytes());
                // 将流推出去
                pipedOutputStream.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        // 新启一个线程，从管道输入流中，读取其他线程写入的数据
        // 注意：如果读取不到数据时，该线程会被阻塞
        Thread thread = new Thread(() -> {
            int b;
            try {
                while ((b = pipedInputStream.read()) != -1) {
                    System.out.print((char) b);
                }
                System.out.println(Thread.currentThread().getName() +
                        " ===> 已结束" + System.currentTimeMillis());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        thread.start();
        // 等待线程执行完成
        thread.join();
    }
}







