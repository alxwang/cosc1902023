import java.lang.reflect.Method;
import java.util.ArrayList;

public class App {

    Class definition:

    <T> any type
    <T extends Number> ANy class extends from number

    Method
    <T> any type when define the method as generic method
    <T extends Number> ANy class extends from number

            When the method is not generic: you can use ? in the param list


    public static void main(String[] args) {
        GenericStack<Integer> integerGenericStack = new GenericStack<>();
        integerGenericStack.push(1);
        integerGenericStack.push(2);
        integerGenericStack.push(3);
        integerGenericStack.push(4);
//        System.out.println(max(integerGenericStack));
//        print(integerGenericStack);


        GenericStack<Object> integerGenericStack1 = new GenericStack<>();
        integerGenericStack1.push("Alex");
        integerGenericStack1.push("Joe");
        integerGenericStack1.push("Max");
        integerGenericStack1.push("Kelci");

        add(integerGenericStack,integerGenericStack1);
        print(integerGenericStack1);
    }
    public static <E extends Number> double max(GenericStack<E> stack){
        double max = stack.pop().doubleValue();
        while(stack.getSize()>0)
        {
            double val = stack.pop().doubleValue();
            if(val>max) max=val;
        }
        return max;
    }

//    public static <E> void print(GenericStack<E> stack)
//    {
//        while(stack.getSize()>0)
//        {
//            System.out.println(stack.pop()+" ");
//        }
//    }
    public static void print(GenericStack<?> stack)
    {
        while(stack.getSize()>0)
        {
            System.out.println(stack.pop()+" ");
        }
    }

    public static void print(ArrayList<?> list)
    {

    }

//    public static  <T> void add(GenericStack<T> stack1, GenericStack<T> stack2)
    public static  <T> void add(GenericStack<T> stack1, GenericStack<? super T> stack2)
    {
        while(stack1.getSize()>0)
            stack2.push(stack1.pop());
    }
    public static  <T> void add1(GenericStack<T> stack1, GenericStack<? extends T> stack2)
    {
        while(stack2.getSize()>0)
            stack1.push(stack2.pop());

    }

}
