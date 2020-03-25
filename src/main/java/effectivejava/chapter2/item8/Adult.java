package effectivejava.chapter2.item8;

// Well-behaved client of resource with cleaner safety-net (Page 33)

/**
 * 在Java编程过程中，如果打开了外部资源（文件、数据库连接、网络连接等），
 * 我们必须在这些外部资源使用完毕后，手动关闭它们。
 * 因为外部资源不由JVM管理，无法享用JVM的垃圾回收机制，
 * 如果我们不在编程时确保在正确的时机关闭外部资源，就会导致外部资源泄露，
 * 紧接着就会出现文件被异常占用，数据库连接过多导致连接池溢出等诸多很严重的问题。
 *
 *      通常情况下使用try-finally解决这个问题，但是这样使用会让我们的代码变得复杂难懂
 *      因为在finally中关闭资源的时候也会出现异常，这又需要使用一个try-finally。
 *
 *      在jdk1.7之后引入的try-with-resources，可以精简这个写法。
 *      try-with-resource并不是JVM虚拟机的新增功能，只是JDK实现了一个语法糖。
 *      （见item9）
 * 注意：
 *      1、当一个外部资源的句柄对象实现了AutoCloseable接口，
 *         JDK7中便可以利用try-with-resource语法更优雅的关闭资源，消除板式代码。
 *      2、try-with-resource时，如果对外部资源的处理和对外部资源的关闭均遭遇了异常，
 *         “关闭异常”将被抑制，“处理异常”将被抛出，但“关闭异常”并没有丢失，
 *         而是存放在“处理异常”的被抑制的异常列表中。
 *         通过异常的getSuppressed方法，可以提取出被抑制的异常。
 */
public class Adult {
    public static void main(String[] args) {
        try (Room myRoom = new Room(7)) {
            System.out.println("Goodbye");
        }
    }
}
