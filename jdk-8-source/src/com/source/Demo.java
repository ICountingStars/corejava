package com.source;

/**
 * TODO
 *
 * @author chenjianglin
 * @date 2022/2/11
 * @since 1.0.0
 **/
public class Demo {
    // 私有的原子可见性的 instance 属性
    private volatile static Demo instance;
    // 私有构造器
    private Demo() {
    }
    // 公有的获取实例的方法
    public static Demo getDemoInstance(){
        if (instance == null) {
            synchronized (Demo.class) {
                if (instance == null) {
                    instance = new Demo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        // 非单例
        Demo instance1 = new Demo();
        Demo instance2 = new Demo();
        System.out.println(instance1);
        System.out.println(instance2);
        // 获取单例对象
        System.out.println("获取单例对象");
        Demo instance3 = Demo.getDemoInstance();
        Demo instance4 = Demo.getDemoInstance();
        System.out.println(instance3);
        System.out.println(instance4);

        String s1 = "a";
        StringBuffer sb = new StringBuffer();
        StringBuilder builder = new StringBuilder();
        String a = "hello";
    }
}

