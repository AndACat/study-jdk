package life.test;

import life.pojo.Dog;

/**
 * @author WangZhen
 * @Date 2024/9/21 0:49
 */
public class TestInheritableThreadLocal {
    private InheritableThreadLocal<Dog> inheritableThreadLocal = new InheritableThreadLocal<>();
    /**
     * 在一个方法里可以设置这个值
     * @param dog
     */
    public void setDog(Dog dog){
        inheritableThreadLocal.set(dog);
    }

    /**
     * 在另一个方法（只要是一个线程就可以）里就可以得到这个值，进而实现登录拦截器统一注入用户登录信息等
     * @return
     */
    public Dog getDog(){
        return inheritableThreadLocal.get();
    }

    public static void main(String[] args) {
        TestInheritableThreadLocal testInheritableThreadLocal = new TestInheritableThreadLocal();
        // 在main线程中设置值
        testInheritableThreadLocal.inheritableThreadLocal.set(new Dog("豆豆", 3));
        // 如果使用的是threadLocal，在子线程是无法访问到这个数据的，而使用了inheritableThreadLocal，
        // 就可以在子线程中获取到父线程中设置的值
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "-->" + testInheritableThreadLocal.inheritableThreadLocal.get());
        }, "子线程1").start();
        // 结果表明：在子线程1中可以访问到父线程中的InheritableThreadLocal的值

    }
}
