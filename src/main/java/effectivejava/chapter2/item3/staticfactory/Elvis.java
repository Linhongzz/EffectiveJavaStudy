package effectivejava.chapter2.item3.staticfactory;

// Singleton with static factory (Page 17)
public class Elvis {
    private static final Elvis INSTANCE = new Elvis();
    //私有化构造方法，避免外部调用构造方法
    private Elvis() { }
    public static Elvis getInstance() { return INSTANCE; }

    public void leaveTheBuilding() {
        System.out.println("Whoa baby, I'm outta here!");
    }

    // This code would normally appear outside the class!
    public static void main(String[] args) {
        Elvis elvis = Elvis.getInstance();
        elvis.leaveTheBuilding();
    }
}
