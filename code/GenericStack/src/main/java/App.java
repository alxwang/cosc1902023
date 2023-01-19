public class App {

    public static <E> void print(GenericStack<E> stack) {
        while(stack.getSize()>0) System.out.println(stack.pop());
    }

    public static double max(GenericStack<Number> stack) {
        double max = stack.pop().doubleValue(); // Initialize max

        while (stack.getSize()>0) {
            double value = stack.pop().doubleValue();
            if (value > max)
                max = value;
            }
        return max;
        }

    public static <E extends  Number>double max1(GenericStack<E> stack) {
        double max = stack.pop().doubleValue(); // Initialize max

        while (stack.getSize()>0) {
            double value = stack.pop().doubleValue();
            if (value > max)
                max = value;
        }
        return max;
    }

    public static double max2(GenericStack<? extends Number> stack) {
        double max = stack.pop().doubleValue(); // Initialize max

        while (stack.getSize()>0) {
            double value = stack.pop().doubleValue();
            if (value > max)
                max = value;
        }
        return max;
    }
    public static void main(String[] args) {
//        GenericStack<String> stack=new GenericStack<>();
//        stack.push("alex");
//        stack.push("joe");
//        stack.push("max");
//        stack.push("kelci");
//        //stack.push(1.23);
//        System.out.println(stack);
//        print(stack);
        GenericStack<Integer>stack = new GenericStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        max2(stack);
    }
}
