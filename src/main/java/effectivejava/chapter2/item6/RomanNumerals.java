package effectivejava.chapter2.item6;
import java.util.regex.Pattern;

// Reusing expensive object for improved performance (Pages 22 and 23)
public class RomanNumerals {
    // Performance can be greatly improved! (Page 22)
    static boolean isRomanNumeralSlow(String s) {
        /**
         * 缓慢原因，每次调用String.matches方法都要把regex生成Pattern对象
         * Pattern又是一个昂贵的对象，这无疑是一个对性能的浪费
         */
        return s.matches("^(?=.)M*(C[MD]|D?C{0,3})"
                + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
    }

    /**
     * Pattern:正则表达式的编译表示。
     * 必须首先将正则表达式（指定为字符串）编译为此类的实例。
     * 然后将所得的图案可以被用来创建一个Matcher对象
     * 可以匹配任意character sequences针对正则表达式。
     * 执行匹配的所有状态都驻留在匹配器中，所以许多匹配者可以共享相同的模式。
     * Pattern 线程安全 Matcher 线程不安全
     */
    // Reusing expensive object for improved performance (Page 23)
    // 重用昂贵的对象以提高性能
    private static final Pattern ROMAN = Pattern.compile(
            "^(?=.)M*(C[MD]|D?C{0,3})"
                    + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");

    static boolean isRomanNumeralFast(String s) {
        return ROMAN.matcher(s).matches();
    }

    /**
     * 经过测试，重用Pattern可以有5倍左右的性能差距
     * @param args
     */
    public static void main(String[] args) {
        int numSets = 100;
        int numReps = 100;
        boolean b = false;

        for (int i = 0; i < numSets; i++) {
            long start = System.nanoTime();
            for (int j = 0; j < numReps; j++) {
                b ^= isRomanNumeralFast("MCMLXXVI");
                // Change Slow to Fast to see performance difference
            }
            long end = System.nanoTime();
            System.out.println(((end - start) / (1_000. * numReps)) + " μs.");
        }

        // Prevents VM from optimizing away everything.
        if (!b)
            System.out.println();
    }
}

