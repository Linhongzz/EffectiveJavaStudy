package effectivejava.chapter2.item2.hierarchicalbuilder;
import java.util.*;

// Builder pattern for class hierarchies (Page 14)

// Note that the underlying "simulated self-type" idiom
// allows for arbitrary fluid hierarchies, not just builders

/**
 * enum枚举类
 *
 */
public abstract class Pizza {
    /**
     * 自定义一个枚举类型
     * 实际上 enum 就是一个 class，
     * 只不过 java 编译器帮我们做了语法的解析和编译而已。
     * public class com.hmw.test.EnumTest extends java.lang.Enum{
     *     public static final com.hmw.test.EnumTest MON;
     *     public static final com.hmw.test.EnumTest TUE;
     *     public static final com.hmw.test.EnumTest WED;
     *     public static final com.hmw.test.EnumTest THU;
     *     public static final com.hmw.test.EnumTest FRI;
     *     public static final com.hmw.test.EnumTest SAT;
     *     public static final com.hmw.test.EnumTest SUN;
     *     static {};
     *     public int getValue();
     *     public boolean isRest();
     *     public static com.hmw.test.EnumTest[] values();
     *     public static com.hmw.test.EnumTest valueOf(java.lang.String);
     *     com.hmw.test.EnumTest(java.lang.String, int, int, com.hmw.test.EnumTest);
     * }
     */
    public enum Topping { HAM, MUSHROOM, ONION, PEPPER, SAUSAGE }
    //Pizza的属性，一个Set类型存放的是Topping类型的枚举
    final Set<Topping> toppings;

    abstract static class Builder<T extends Builder<T>> {
        //使用指定的元素类型创建一个空的枚举集。EnumSet.noneOf(Topping.class)
        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);
        public T addTopping(Topping topping) {
            //EnumSet中不允许使用零元素。
            //尝试插入一个空元素将抛出NullPointerException 。
            toppings.add(Objects.requireNonNull(topping));
            return self();
        }

        abstract Pizza build();

        // Subclasses must override this method to return "this"
        protected abstract T self();
    }
    
    Pizza(Builder<?> builder) {
        toppings = builder.toppings.clone(); // See Item 50
    }
}
