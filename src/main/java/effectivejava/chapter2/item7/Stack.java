package effectivejava.chapter2.item7;
import java.util.*;

// Can you spot the "memory leak"?  (Pages 26-27)
public class Stack {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    /**
     * 即使弹出了对象，但是他在elements[]中任然存在引用
     * 而且这个引用不是我们需要的引用（我们只需要index小于size的部分），
     * 导致了这个对象，以及这个对象引用的对象不能被垃圾回收处理掉，
     * 造成内存泄漏。
     * 内存泄漏（Memory Leak）：是指程序中己动态分配的堆内存由于某种原因程序未释放或无法释放，
     * 造成系统内存的浪费，导致程序运行速度减慢甚至系统崩溃等严重后果。
     * 更形象的可以称为：无意的对象保留(unintentional object retentions)
     */
    public Object pop() {
        if (size == 0)
            throw new EmptyStackException();
        return elements[--size];
    }

    /**
     * Ensure space for at least one more element, roughly
     * doubling the capacity each time the array needs to grow.
     */
    private void ensureCapacity() {
        if (elements.length == size)
            elements = Arrays.copyOf(elements, 2 * size + 1);
    }

//    // Corrected version of pop method (Page 27)
//    public Object pop() {
//        if (size == 0)
//            throw new EmptyStackException()
//    // --在前先减了再使用 --在后先使用了再减
//        Object result = elements[--size];
//        elements[size] = null; // Eliminate obsolete reference
//        return result;
//    }

    public static void main(String[] args) {
        Stack stack = new Stack();
        for (String arg : args)
            stack.push(arg);

        while (true)
            System.err.println(stack.pop());
    }
}
