package life.test.threadlocal;

import com.alibaba.ttl.TransmittableThreadLocal;
import life.pojo.Dog;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author WangZhen
 * @Date 2024/9/21 1:20
 */
public class TestTransmittableThreadLocal {

    private static final TransmittableThreadLocal<Dog> transmittableThreadLocal1 = new TransmittableThreadLocal<>();
    private static final TransmittableThreadLocal<String> transmittableThreadLocal2 = new TransmittableThreadLocal<>();
    private static final Dog dog = new Dog();

    public static void main(String[] args) throws InterruptedException {
        // 创建五个线程的线程池
        Executor executor = Executors.newFixedThreadPool(5);
        // 在主线程main中设置值
        transmittableThreadLocal1.set(new Dog("毛毛", 1));
        transmittableThreadLocal2.set("土豆");

        // 在子线程中访问和修改main线程中的值
        executor.execute(() -> {
            transmittableThreadLocal1.get().setName("狗狗");
            System.out.println(Thread.currentThread().getName() + "--> " +
                    transmittableThreadLocal1.get()); //  狗狗
            transmittableThreadLocal2.set("辣椒");
        });
        // 在main线程中，能访问到子线程中修改后的dog吗？ 狗狗:child？毛毛:mian？  狗狗:√
        System.out.println(Thread.currentThread().getName() + "--> " +
                transmittableThreadLocal1.get()); // 毛毛
        System.out.println(Thread.currentThread().getName() + "--> " +
                transmittableThreadLocal2.get()); // 土豆

        Thread.sleep(1000L);

        System.out.println(Thread.currentThread().getName() + "--> " +
                transmittableThreadLocal1.get());  // 狗狗
        System.out.println(Thread.currentThread().getName() + "--> " +
                transmittableThreadLocal2.get());  // 土豆



        executor.execute(() -> {
//            transmittableThreadLocal1.get().setName("狗狗");
            transmittableThreadLocal1.set(new Dog("666", 555));
            System.out.println(Thread.currentThread().getName() + "--> " +
                    transmittableThreadLocal1.get());
        });

        Thread.sleep(1000L);

        System.out.println(Thread.currentThread().getName() + "--> " +
                transmittableThreadLocal1.get());  // 狗狗
    }
}
