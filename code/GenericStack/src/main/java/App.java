public class App {

    public static <E> void print(GenericStack<E> stack) {
        while(stack.getSize()>0) System.out.println(stack.pop());
    }


    //We want the E in the stack is a number so we can call doubleValue method
    //It does not work because the concrete class we can use are Integer, Double...
    //A GenericStack<Integer> is NOT a GenericStack<Number>
    public static double max(GenericStack<Number> stack) {
        double max = stack.pop().doubleValue(); // Initialize max

        while (stack.getSize()>0) {
            double value = stack.pop().doubleValue();
            if (value > max)
                max = value;
            }
        return max;
        }


        //we can solve the problem by making the method generic method
    public static <E extends  Number>double max1(GenericStack<E> stack) {
        double max = stack.pop().doubleValue(); // Initialize max

        while (stack.getSize()>0) {
            double value = stack.pop().doubleValue();
            if (value > max)
                max = value;
        }
        return max;
    }

    //Or we can use wildcard.
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
