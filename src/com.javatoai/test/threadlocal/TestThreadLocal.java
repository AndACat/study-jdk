package life.test.threadlocal;

import life.pojo.Dog;

/**
 * @author WangZhen
 * @Date 2024/9/21 0:32
 */
public class TestThreadLocal {
    private ThreadLocal<Dog> threadLocal = new ThreadLocal<>();

    /**
     * ��һ������������������ֵ
     * @param dog
     */
    public void setDog(Dog dog){
        threadLocal.set(dog);
    }

    /**
     * ����һ��������ֻҪ��һ���߳̾Ϳ��ԣ���Ϳ��Եõ����ֵ������ʵ�ֵ�¼������ͳһע���û���¼��Ϣ��
     * @return
     */
    public Dog getDog(){
        return threadLocal.get();
    }
    public static void main(String[] args) throws InterruptedException {
        // ������������
        new Thread(() -> {
            TestThreadLocal testThreadLocal = new TestThreadLocal();
            testThreadLocal.setDog(new Dog("С��", 2));

            System.out.println(Thread.currentThread().getName() + "-->" + testThreadLocal.getDog());
        }, "�߳�1").start();

        // �����ǲ���������
        TestThreadLocal testThreadLocal = new TestThreadLocal(); // ������main�߳���
        new Thread(() -> {
            testThreadLocal.setDog(new Dog("С��", 2));
            System.out.println(Thread.currentThread().getName() + "-->" + testThreadLocal.getDog());
        }, "�߳�2").start();
        // ����main�߳��л�ȡ�߳�1�����õ�dog��
        Thread.sleep(3000L);
        System.out.println(Thread.currentThread().getName() + "-->" + testThreadLocal.getDog());

        Thread.sleep(1000L);
    }
}
