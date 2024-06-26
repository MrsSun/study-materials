package notes.jvm.testCode;

public class Father extends GrandPa {
    static
    {
        System.out.println("爸爸在静态代码块");
    }

    public Father()
    {
        System.out.println("我是爸爸~");
    }

}
