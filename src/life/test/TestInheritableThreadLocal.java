package life.test;

import life.pojo.Dog;

/**
 * @author WangZhen
 * @Date 2024/9/21 0:49
 */
public class TestInheritableThreadLocal {
    private InheritableThreadLocal<Dog> inheritableThreadLocal = new InheritableThreadLocal<>();
    /**
     * ��һ������������������ֵ
     * @param dog
     */
    public void setDog(Dog dog){
        inheritableThreadLocal.set(dog);
    }

    /**
     * ����һ��������ֻҪ��һ���߳̾Ϳ��ԣ���Ϳ��Եõ����ֵ������ʵ�ֵ�¼������ͳһע���û���¼��Ϣ��
     * @return
     */
    public Dog getDog(){
        return inheritableThreadLocal.get();
    }

    public static void main(String[] args) {
        TestInheritableThreadLocal testInheritableThreadLocal = new TestInheritableThreadLocal();
        // ��main�߳�������ֵ
        testInheritableThreadLocal.inheritableThreadLocal.set(new Dog("����", 3));
        // ���ʹ�õ���threadLocal�������߳����޷����ʵ�������ݵģ���ʹ����inheritableThreadLocal��
        // �Ϳ��������߳��л�ȡ�����߳������õ�ֵ
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "-->" + testInheritableThreadLocal.inheritableThreadLocal.get());
        }, "���߳�1").start();
        // ��������������߳�1�п��Է��ʵ����߳��е�InheritableThreadLocal��ֵ

    }
}
