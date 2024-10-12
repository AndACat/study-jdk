package life.test;

import life.pojo.Dog;

/**
 * @author WangZhen
 * @Date 2024/9/21 0:32
 */
public class TestThreadLocal {
    private ThreadLocal<Dog> threadLocal = new ThreadLocal<>();

    /**
     * 在一个方法里可以设置这个值
     * @param dog
     */
    public void setDog(Dog dog){
        threadLocal.set(dog);
    }

    /**
     * 在另一个方法（只要是一个线程就可以）里就可以得到这个值，进而实现登录拦截器统一注入用户登录信息等
     * @return
     */
    public Dog getDog(){
        return threadLocal.get();
    }
    public static void main(String[] args) throws InterruptedException {
        // 可以正常访问
        new Thread(() -> {
            TestThreadLocal testThreadLocal = new TestThreadLocal();
            testThreadLocal.setDog(new Dog("小黄", 2));

            System.out.println(Thread.currentThread().getName() + "-->" + testThreadLocal.getDog());
        }, "线程1").start();

        // 以下是不正常访问
        TestThreadLocal testThreadLocal = new TestThreadLocal(); // 定义在main线程上
        new Thread(() -> {
            testThreadLocal.setDog(new Dog("小黄", 2));
            System.out.println(Thread.currentThread().getName() + "-->" + testThreadLocal.getDog());
        }, "线程2").start();
        // 能在main线程中获取线程1中设置的dog吗？
        Thread.sleep(3000L);
        System.out.println(Thread.currentThread().getName() + "-->" + testThreadLocal.getDog());

        Thread.sleep(1000L);
    }
}
