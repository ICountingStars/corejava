package textFile;

/**
 * TODO
 *
 * @author chenjianglin
 * @date 2021/6/26
 * @since 1.0.0
 **/
interface ClothFactory {
    void getCloth();
}

public class ClothFactoryTest {
    public static void main(String[] args) {
        NikeClothFactory nikeClothFactory = new NikeClothFactory();
        MyClothFactory myClothFactory = new MyClothFactory(nikeClothFactory);
        myClothFactory.getCloth();
    }
}

// 代理类
class MyClothFactory implements ClothFactory {

    private ClothFactory clothFactory;

    public MyClothFactory(ClothFactory clothFactory) {
        this.clothFactory = clothFactory;
    }

    @Override
    public void getCloth() {
        clothFactory.getCloth();
    }
}

// 被代理类
class NikeClothFactory implements ClothFactory {

    @Override
    public void getCloth() {
        System.out.println("获取的一件Nike服装");
    }
}
