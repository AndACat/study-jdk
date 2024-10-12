package life.unit;

import org.junit.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author WangZhen
 * @Date 2024/9/15 16:56
 */
public class MyUnitTest {

    @Test
    public void test1() throws IOException, InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "--> come in" + System.currentTimeMillis());
            // t1 线程先执行，尝试获取许可，而一个线程的许可默认是0，所以获取不到，就会阻塞
            LockSupport.park();
            // 当 t2 线程释放了一个许可，t1 线程就可以继续执行
            System.out.println(Thread.currentThread().getName() + "--> go out" + System.currentTimeMillis());
        }, "t1");
        t1.start();

        TimeUnit.SECONDS.sleep(2);

        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "--> come in" + System.currentTimeMillis());
            // t2 线程给 t1 线程释放了一个许可
            LockSupport.unpark(t1);
            System.out.println(Thread.currentThread().getName() + "--> go out" + System.currentTimeMillis());
        }, "t2");
        t2.start();
    }

    @Test
    public void test(){
        HashMap
    }
}
