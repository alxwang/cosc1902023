public class App {

    public static <E> void print(E[] list) {
        for (int i = 0; i < list.length; i++)
            System.out.print(list[i] + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        GenericStack<String> stack=new GenericStack<>();
        stack.push("alex");
        stack.push("joe");
        stack.push("max");
        stack.push("kelci");
        //stack.push(1.23);
        System.out.println(stack);
    }
}
