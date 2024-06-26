package notes.jvm.testCode;

public class GrandPa {
    static {
        System.out.println("爷爷在静态代码块");
    }

    public GrandPa() {
        System.out.println("我是爷爷~");
    }

}
