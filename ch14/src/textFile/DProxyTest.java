package textFile;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * TODO
 *
 * @author chenjianglin
 * @date 2021/6/26
 * @since 1.0.0
 **/
public class DProxyTest {
    public static void main(String[] args) {
        Human superMan = new SuperMan();
        Human human = (Human) ProxyFactory.getProxyInstance(superMan);
        human.getBelief();

    }
}
// 代理接口
interface Human {
    void getBelief();
}

// 被代理类
class SuperMan implements Human {

    @Override
    public void getBelief() {
        System.out.println("I believe I can fly!");
    }
}

// 代理工厂
class ProxyFactory{
    public static Object getProxyInstance(Object obj) {
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), new MyInvocationHandler(obj));
    }
}
// 调用处理器
class MyInvocationHandler implements InvocationHandler {

    private Object obj;

    public MyInvocationHandler(Object obj) {
        this.obj = obj;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(obj, args);
    }
}
