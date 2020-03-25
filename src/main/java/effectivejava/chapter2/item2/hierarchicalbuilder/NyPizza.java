package effectivejava.chapter2.item2.hierarchicalbuilder;

import java.util.Objects;

// Subclass with hierarchical builder (Page 15)
public class NyPizza extends Pizza {
    public enum Size { SMALL, MEDIUM, LARGE }
    private final Size size;

    public static class Builder extends Pizza.Builder<Builder> {
        private final Size size;

        public Builder(Size size) {
            this.size = Objects.requireNonNull(size);
        }

        @Override public NyPizza build() {
            return new NyPizza(this);
        }

        @Override protected Builder self() { return this; }
    }

    private NyPizza(Builder builder) {
        //调用父类的构造方法
        //在父类里面设置Toppings
        super(builder);
        //在子类(this)中设置size
        size = builder.size;
    }

    @Override public String toString() {
        return "New York Pizza with " + toppings;
    }
}
