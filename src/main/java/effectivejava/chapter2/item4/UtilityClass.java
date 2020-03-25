package effectivejava.chapter2.item4;

// Noninstantiable utility class (Page 19)
public class UtilityClass {
    // 抑制默认构造函数的不稳定性
    // Suppress default constructor for noninstantiability
    private UtilityClass() {
        throw new AssertionError();
    }

    // Remainder omitted
}
